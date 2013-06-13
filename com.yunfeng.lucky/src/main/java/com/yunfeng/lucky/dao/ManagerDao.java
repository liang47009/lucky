package com.yunfeng.lucky.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.yunfeng.lucky.entity.Manager;

@Repository
public class ManagerDao extends BaseDao<Manager> {

	@Override
	public List<Manager> findAll(Session session) {
		return null;
	}

	/**
	 * 
	 * @param username
	 * @param password
	 * @param session
	 * @return
	 */
	public Manager findByNamePass(String username, String password,
			Session session) {
		String sql = "from Manager as m where m.name='" + username
				+ "' and m.pass='" + password + "'";
		Query q = session.createQuery(sql);
		Object o = q.uniqueResult();
		return (Manager) o;
	}

}