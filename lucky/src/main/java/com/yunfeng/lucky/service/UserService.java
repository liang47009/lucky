package com.yunfeng.lucky.service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yunfeng.lucky.dao.UserDao;
import com.yunfeng.lucky.entity.User;

@Service
public class UserService extends BaseService<User> {
	@Resource
	private UserDao userDao;

	/**
	 * 根据id获取用户信息
	 * 
	 * @param uid
	 * @return
	 */
	public User getUserById(int uid) {
		User user = userDao.find(User.class, uid,
				this.sessionFactory.getCurrentSession());
		return user;
	}

	@Override
	@PostConstruct
	public void init() {
		this.baseDao = userDao;
	}

}