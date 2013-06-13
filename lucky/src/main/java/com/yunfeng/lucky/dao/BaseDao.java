package com.yunfeng.lucky.dao;

import java.util.List;

import org.hibernate.Session;

public abstract class BaseDao<T> {

	public T find(Class<T> clazz, int id, Session session) {
		T o = (T) session.get(clazz, id);
		return o;
	}

	public abstract List<T> findAll(Session session);

	public int save(Object object, Session session) {
		return (Integer) session.save(object);
	}

	public void update(Object object, Session session) {
		session.update(object);
	}

	public void saveOrUpdate(Object object, Session session) {
		session.saveOrUpdate(object);
	}

	public void delete(Object object, Session session) throws Exception {
		session.delete(object);
	}

}