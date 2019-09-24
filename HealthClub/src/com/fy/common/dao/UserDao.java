package com.fy.common.dao;

import org.hibernate.Session;

import com.fy.common.entity.User;

public interface UserDao {


	public void update(User user);
	public User queryById(String username);
}
