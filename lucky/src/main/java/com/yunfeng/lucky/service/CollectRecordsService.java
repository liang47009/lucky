package com.yunfeng.lucky.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yunfeng.lucky.dao.CollectRecordsDao;
import com.yunfeng.lucky.entity.CollectRecords;

@Service
public class CollectRecordsService extends BaseService<CollectRecords> {
	@Resource
	private CollectRecordsDao collectRecordsDao;

	public CollectRecords findByUidAndPid(int uid, int pid) {
		return collectRecordsDao.findByUidAndPid(uid, pid,
				this.sessionFactory.getCurrentSession());
	}

	/**
	 * 已领取的总份数
	 * 
	 * @param pid
	 *            道具id
	 * @return
	 */
	public short findTotalCountByPid(int pid) {
		short tc = collectRecordsDao.findTotalCountByPid(pid,
				this.sessionFactory.getCurrentSession());
		return tc;
	}

	/**
	 * 获取所有记录(根据领取数量降序排列)
	 * 
	 * @param pid
	 * @return
	 */
	public List<CollectRecords> findAllByPid(int pid) {
		List<CollectRecords> list = collectRecordsDao.findAllByPid(pid,
				this.sessionFactory.getCurrentSession());
		return list;
	}

	/**
	 * 获取所有记录(根据领取时间降序排列)
	 * 
	 * @param pid
	 * @return
	 */
	public List<CollectRecords> findAllUserByPid(int pid) {
		List<CollectRecords> list = collectRecordsDao.findAllUserByPid(pid,
				this.sessionFactory.getCurrentSession());
		return list;
	}

	/**
	 * 统计领取的总人数
	 * 
	 * @return
	 */
	public short findUserCount(int pid) {
		return collectRecordsDao.findUserCount(pid,
				this.sessionFactory.getCurrentSession());
	}

	@Override
	@PostConstruct
	public void init() {
		// TODO Auto-generated method stub
		this.baseDao = collectRecordsDao;
	}

}
