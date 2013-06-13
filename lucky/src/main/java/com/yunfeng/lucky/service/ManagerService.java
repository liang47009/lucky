package com.yunfeng.lucky.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yunfeng.lucky.dao.ManagerDao;
import com.yunfeng.lucky.dto.Duration;
import com.yunfeng.lucky.dto.ManageDto;
import com.yunfeng.lucky.dto.PropsForm;
import com.yunfeng.lucky.entity.Manager;
import com.yunfeng.lucky.entity.Props;
import com.yunfeng.util.DateUtil;

@Service
public class ManagerService extends BaseService<Manager> {

	@Resource
	private ManagerDao managerDao;
	@Resource
	private PropsService propsService;
	@Resource
	private CollectRecordsService collectRecordsService;

	/**
	 * 根据用户名密码查找管理者
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public Manager findByNamePass(String username, String password) {
		return managerDao.findByNamePass(username, password,
				this.sessionFactory.getCurrentSession());
	}

	/**
	 * 获取所有激活码
	 * 
	 * @return
	 */
	public List<ManageDto> getAllProps() {
		List<Props> props = propsService.findAll();
		List<ManageDto> temp = new ArrayList<>();
		long now = System.currentTimeMillis();
		for (Props p : props) {
			ManageDto md = new ManageDto();
			short count = collectRecordsService.findUserCount(p.getId());
			md.setCount(count);
			md.setCt(DateUtil.f(p.getCreateTime()));
			md.setId(p.getId());
			md.setName(p.getName());
			md.setStart(DateUtil.f(p.getStart()));
			int state = -1;
			if (p.isFinish()) {
				state = 2;
			} else {
				if (p.getStart() > now) {
					state = 0;
				} else {
					state = 1;
				}
			}
			md.setState(state);
			temp.add(md);
		}
		return temp;
	}

	@Override
	@PostConstruct
	public void init() {
		this.baseDao = managerDao;
	}

	/**
	 * 编辑激活码
	 * 
	 * @param pid
	 * @return
	 */
	public PropsForm editProps(int pid) {
		Props p = propsService.find(Props.class, pid);
		if (p.isFinish() || (p.getStart() < System.currentTimeMillis())) {
			// 如果结束或者已经开始， 不能编辑
			return null;
		}
		PropsForm pf = new PropsForm();
		pf.setId(p.getId());
		pf.setImgUrl(p.getImgUrl());
		pf.setActivation(p.getActivation());
		pf.setCost(String.valueOf(p.getCast()));
		pf.setDescription(p.getDescription());
		Duration d = DateUtil.format(p.getStart(), p.getEnd());
		pf.setHour((short) d.getHour());
		pf.setMinute((short) d.getMint());
		pf.setLink(p.getLink());
		pf.setName(p.getName());
		pf.setPrice(p.getPrice());
		pf.setStart(DateUtil.f(p.getStart()));
		pf.setTotalCount(p.getTotalCount());
		return pf;
	}

	/**
	 * 删除激活码
	 * 
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	public void delProps(int pid) throws Exception {
		propsService.delProps(pid);
	}

}
