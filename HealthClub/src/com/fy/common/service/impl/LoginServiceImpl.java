package com.fy.common.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fy.common.service.LoginService;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

	public boolean login(String uName, String uPass) {
		// TODO Auto-generated method stub
		if("admin".equals(uName)&&"admin".equals(uPass)){
			return true;
		}else{
			return false;
		}
	}
}
