package com.yunfeng.lucky.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.yunfeng.lucky.entity.Email;

@Repository
public class EmailDao extends BaseDao<Email> {

	@Override
	public List<Email> findAll(Session session) {
		// TODO Auto-generated method stub
		return null;
	}

}
