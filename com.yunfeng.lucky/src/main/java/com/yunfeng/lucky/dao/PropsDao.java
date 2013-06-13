package com.yunfeng.lucky.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.yunfeng.lucky.entity.Props;

@Repository
public class PropsDao extends BaseDao<Props> {

	@Override
	public List<Props> findAll(Session session) {
		Query q = session.createQuery("from Props");
		return q.list();
	}

	/**
	 * 24小时的毫秒数
	 */
	// private static final long HOUR24 = 24 * 60 * 60 * 1000;

	/**
	 * 即将开始的
	 * 
	 * @param session
	 * @return
	 */
	public List<Props> findAllStart(Session session) {
		// long now = System.currentTimeMillis() + HOUR24;
		// String condition1 = " and p.start<" + now;
		// String condition2 = " and p.start>" + System.currentTimeMillis();
		String sql = "from Props as p where p.finish=false";
		Query q = session.createQuery(sql);
		return q.list();
	}

	/**
	 * 火热进行中
	 * 
	 * @param session
	 * @return
	 */
	public List<Props> findAllHot(Session session) {
		// long now = System.currentTimeMillis();
		// String condition1 = " and p.end>" + now;
		// String condition2 = " and p.start<" + now;
		String sql = "from Props as p where p.finish=false";
		Query q = session.createQuery(sql);
		return q.list();
	}

}