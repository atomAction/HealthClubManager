package com.fy.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fy.common.dao.UserDao;
import com.fy.common.entity.User;
import com.fy.common.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	public boolean userlogin(String username, String password) {
		String un =userDao.queryById(username).getUserName();
		String up = userDao.queryById(username).getUserPass();
		
		if (username.equals(un) && password.equals(up)) {
			return true;
		}
		return false;
		
	}

	public void updateUser(User user) {
	userDao.update(user);
		
	}
	
		
}
	
