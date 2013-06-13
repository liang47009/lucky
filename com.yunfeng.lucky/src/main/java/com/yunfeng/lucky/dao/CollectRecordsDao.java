package com.yunfeng.lucky.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.yunfeng.lucky.entity.CollectRecords;

@Repository
public class CollectRecordsDao extends BaseDao<CollectRecords> {

	@Override
	public List<CollectRecords> findAll(Session session) {
		String sql = "from CollectRecords";
		Query q = session.createQuery(sql);
		return q.list();
	}

	/**
	 * 根据用户id和道具id查找领卡数
	 * 
	 * @param uid
	 * @param pid
	 * @param currentSession
	 * @return
	 */
	public CollectRecords findByUidAndPid(int uid, int pid, Session session) {
		String sql = "from CollectRecords where ";
		String condition1 = "uid=" + uid + " and pid=" + pid;
		Query q = session.createQuery(sql + condition1);
		Object o = q.uniqueResult();
		return (CollectRecords) o;
	}

	/**
	 * 已领取的总份数
	 * 
	 * @param pid
	 * @param session
	 * @return
	 */
	public short findTotalCountByPid(int pid, Session session) {
		String sql = "select sum(count) from collectrecords where pid=" + pid;
		Query q = session.createSQLQuery(sql);
		Object o = q.uniqueResult();
		if (null == o) {
			return 0;
		}
		BigDecimal bd = (BigDecimal) o;
		int count = bd.intValue();
		return (short) count;
	}

	/**
	 * 获取所有记录(根据领取数量降序排列)
	 * 
	 * @param pid
	 * @param session
	 * @return
	 */
	public List<CollectRecords> findAllByPid(int pid, Session session) {
		String sql = "from CollectRecords as cr where cr.pid=" + pid
				+ " order by cr.count desc";
		Query q = session.createQuery(sql);
		return q.list();
	}

	/**
	 * 获取所有记录(根据领取时间降序排列)
	 * 
	 * @param pid
	 * @param session
	 * @return
	 */
	public List<CollectRecords> findAllUserByPid(int pid, Session session) {
		String sql = "from CollectRecords as cr where cr.pid=" + pid
				+ " order by cr.lasttime desc";
		Query q = session.createQuery(sql);
		return q.list();
	}

	/**
	 * 所有已经成功领取的
	 * 
	 * @param session
	 * @return
	 */
	public List<CollectRecords> findAllSuccess(Session session) {
		String sql = "from CollectRecords as cr where cr.success=true";
		Query q = session.createQuery(sql);
		return q.list();
	}

	/**
	 * 根据pid统计领取的总人数
	 * 
	 * @param session
	 * @return
	 */
	public short findUserCount(int pid, Session session) {
		String sql = "select count(*) from collectrecords where pid=" + pid;
		Query q = session.createSQLQuery(sql);
		Object o = q.uniqueResult();
		if (null == o) {
			return 0;
		}
		BigInteger bi = (BigInteger) o;
		int count = bi.intValue();
		return (short) count;
	}

}