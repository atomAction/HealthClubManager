package com.fy.common.service;

import com.fy.common.entity.User;

public interface UserService {
	

	
	public boolean userlogin(String username, String password);
	
		
	public void updateUser(User user);
}
	
