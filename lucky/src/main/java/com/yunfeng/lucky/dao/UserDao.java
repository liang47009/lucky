package com.yunfeng.lucky.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.yunfeng.lucky.entity.User;

@Repository
public class UserDao extends BaseDao<User> {

	@Override
	public List<User> findAll(Session session) {
		return null;
	}

}