package com.yunfeng.lucky.service;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.yunfeng.lucky.dao.BaseDao;

@Service
public abstract class BaseService<T> {

	protected BaseDao<T> baseDao;

	@Resource
	protected SessionFactory sessionFactory;

	public T find(Class<T> clazz, int id) {
		T o = baseDao.find(clazz, id, sessionFactory.getCurrentSession());
		return o;
	}

	public List<T> findAll() {
		List<T> list = baseDao.findAll(sessionFactory.getCurrentSession());
		return list;
	}

	public int save(Object obj) {
		return baseDao.save(obj, sessionFactory.getCurrentSession());
	}

	public void update(Object obj) {
		baseDao.update(obj, sessionFactory.getCurrentSession());
	}

	public void saveOrUpdate(Object obj) {
		baseDao.saveOrUpdate(obj, sessionFactory.getCurrentSession());
	}

	public abstract void init();

}