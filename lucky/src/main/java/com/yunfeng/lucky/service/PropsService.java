package com.yunfeng.lucky.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import com.yunfeng.lucky.dao.CollectRecordsDao;
import com.yunfeng.lucky.dao.PropsDao;
import com.yunfeng.lucky.dto.CollectDto;
import com.yunfeng.lucky.dto.DtailDto;
import com.yunfeng.lucky.dto.Duration;
import com.yunfeng.lucky.dto.PropsDto;
import com.yunfeng.lucky.dto.PropsForm;
import com.yunfeng.lucky.entity.CollectRecords;
import com.yunfeng.lucky.entity.Email;
import com.yunfeng.lucky.entity.Props;
import com.yunfeng.lucky.entity.User;
import com.yunfeng.lucky.manager.DtoManager;
import com.yunfeng.lucky.timer.TaskScheduler;
import com.yunfeng.util.DateUtil;
import com.yunfeng.util.Log;
import com.yunfeng.util.MailUtil;

@Service
public class PropsService extends BaseService<Props> {

	@Resource
	private PropsDao propsDao;
	@Resource
	private DtoManager dtoManager;
	@Resource
	private UserService userService;
	@Resource
	private EmailService emailService;
	@Resource
	private CollectRecordsDao collectRecordsDao;
	@Resource
	private CollectRecordsService collectRecordsService;

	/**
	 * 即将开始的
	 * 
	 * @return
	 */
	public List<Props> findAllStart() {
		return propsDao.findAllStart(this.sessionFactory.getCurrentSession());
	}

	/**
	 * 更新开始结束时间
	 * 
	 * @param p
	 */
	public void updateStartEnd(Props p) {
		this.update(p);
	}

	/**
	 * 如果过期，重新启用
	 * 
	 * @param p
	 */
	public void reValid(Props p) {
		long now = System.currentTimeMillis();
		if (p.getEnd() < now) {
			long duration = p.getEnd() - p.getStart();
			p.setStart(now);
			p.setEnd(now + duration);
			updateStartEnd(p);
			Map<Integer, User> users = this.map.get(p.getId());
			if (null != users) {
				users.clear();
			}
		}
	}

	/**
	 * 所有火热进行中的
	 * 
	 * @return
	 */
	public List<Props> findAllHot() {
		return propsDao.findAllHot(this.sessionFactory.getCurrentSession());
	}

	/**
	 * 所有已经成功领取的
	 * 
	 * @return
	 */
	public List<PropsDto> findAllSuccess() {
		Session session = this.sessionFactory.getCurrentSession();
		List<CollectRecords> crs = collectRecordsDao.findAllSuccess(session);
		List<PropsDto> temp = new ArrayList<>();
		for (CollectRecords cr : crs) {
			Props p = propsDao.find(Props.class, cr.getPid(), session);
			if (null == p) {
				continue;
			}
			PropsDto pd = dtoManager.createSusPropsDto(p, cr);
			temp.add(pd);
		}
		return temp;
	}

	/**
	 * key-pid, key-uid
	 */
	private Map<Integer, Map<Integer, User>> map = new ConcurrentHashMap<>();

	public synchronized boolean buyProps(User user, int pid) {
		Props p = this.find(Props.class, pid);
		if (null != p && !p.isFinish()) {// 领取是否结束
			if (p.getStart() > System.currentTimeMillis()) {
				// 尚未开始
				return false;
			}
			Map<Integer, User> users = map.get(pid);
			if (null == users) {
				users = new ConcurrentHashMap<>();
				map.put(pid, users);
			}
			User temp = users.get(user.getId());
			if (null != temp) {// 本轮是否领过
				return false;
			}
			int uid = user.getId();
			CollectRecords cr = collectRecordsService.findByUidAndPid(uid,
					p.getId());
			users.put(uid, user);
			if (null == cr) {// 是否已有记录
				cr = new CollectRecords();
				cr.setLasttime(System.currentTimeMillis());
				cr.setName(user.getUsername());
				cr.setCount((short) 1);
				cr.setSuccess(false);
				cr.setPid(p.getId());
				cr.setUid(uid);
				save(cr);
			} else {
				short total = collectRecordsService.findTotalCountByPid(pid);
				short count = cr.getCount();
				count++;
				total++;
				cr.setCount(count);
				cr.setLasttime(System.currentTimeMillis());
				update(cr);
				if (total >= p.getTotalCount()) {
					p.setFinish(true);
					settlement(p);
					update(p);
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * 结算(领取最多的得到)
	 * 
	 * @param p
	 */
	public void settlement(final Props p) {
		List<CollectRecords> list = collectRecordsService.findAllByPid(p
				.getId());
		if (list.isEmpty()) {
			return;
		}
		CollectRecords win = list.get(0);
		win.setSuccess(true);
		User user = userService.getUserById(win.getUid());
		if (null == user) {
			Log.error("user not exist:" + win.getUid());
		} else {
			final String temp = user.getEmail();
			// 是否有效的email地址
			if (null != temp && !"".equals(temp) && MailUtil.validate(temp)) {
				final Email email = emailService.find(Email.class, 1);

				taskScheduler.schedule(new Runnable() {
					@Override
					public void run() {
						try {
							emailService.sendMail(p, email, temp);
						} catch (Exception e) {
							Log.error("", e);
						}
					}
				}, 0, TimeUnit.SECONDS);
			}
		}
		for (CollectRecords cr : list) {
			update(cr);
		}
	}

	@Resource
	private TaskScheduler taskScheduler;

	/**
	 * 道具详细情况
	 * 
	 * @param pid
	 * @return
	 */
	public DtailDto findDetail(int pid) {
		Props p = this.find(Props.class, pid);
		DtailDto dd = new DtailDto();
		int alreadyCount = collectRecordsService.findTotalCountByPid(pid);
		List<CollectRecords> list = collectRecordsService.findAllUserByPid(pid);
		List<CollectDto> temp = new ArrayList<>();
		for (CollectRecords cr : list) {
			CollectDto cd = new CollectDto();
			cd.setLastTime(DateUtil.f(cr.getLasttime()));
			cd.setUsername(cr.getName());
			temp.add(cd);
		}
		if (!temp.isEmpty()) {
			dd.setLastUserName(temp.get(0).getUsername());
			dd.setSize(temp.size());
		}
		if (p.isFinish()) {
			dd.setTime("00:00:00");
		} else {
			Duration duration = DateUtil.format(p.getEnd());
			dd.setTime(duration.toString());
		}
		dd.setPid(pid);
		dd.setUrl(p.getImgUrl());
		dd.setAlreadyCount(alreadyCount);
		dd.setTotalCount(p.getTotalCount());
		dd.setCost(p.getCast());
		dd.setName(p.getName());
		dd.setTotalUser(temp);
		return dd;
	}

	/**
	 * 增加/修改 激活码
	 * 
	 * @param propsForm
	 * @throws ParseException
	 */
	public void addOrEditProps(PropsForm propsForm) throws ParseException {
		Props p = new Props();
		if (propsForm.getId() != 0) {
			p.setId(propsForm.getId());
		}
		p.setActivation(propsForm.getActivation());
		p.setDescription(propsForm.getDescription());
		p.setFinish(false);
		p.setImgUrl(propsForm.getImgUrl());
		p.setLink(propsForm.getLink());
		p.setName(propsForm.getName());
		long start = DateUtil.format(propsForm.getStart() + ":00");
		p.setStart(start);
		long d = (Integer.valueOf(propsForm.getHour()) * 3600 + Integer
				.valueOf(propsForm.getMinute()) * 60) * 1000;
		p.setEnd(start + d);
		p.setCast(Integer.valueOf(propsForm.getCost()));
		p.setPrice(Integer.valueOf(propsForm.getPrice()));
		p.setTotalCount(Short.valueOf(propsForm.getTotalCount()));
		p.setCreateTime(System.currentTimeMillis());
		this.saveOrUpdate(p);
	}

	@Override
	@PostConstruct
	public void init() {
		this.baseDao = propsDao;
	}

	/**
	 * 删除激活码
	 * 
	 * @param pid
	 * @throws Exception
	 */
	public void delProps(int pid) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		Props p = this.baseDao.find(Props.class, pid, session);
		if (p.getStart() > System.currentTimeMillis()) {
			// 如果已经开始, 则不能删除
			throw new RuntimeException("if started, can't delete!");
		}
		this.baseDao.delete(p, session);
	}

}