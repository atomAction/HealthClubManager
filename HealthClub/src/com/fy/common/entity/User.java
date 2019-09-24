package com.fy.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/*
 * 管理员用户实体类
 */
@Entity
public class User {

	private String userName;
	private String userPass;
	
	
	@Id  
	public String getUserName() {
		return userName;
	}
	@Column
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPass() {
		return userPass;
	}
	@Column
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public User() {
		super();
	}
	public User(String userName, String userPass) {
		super();
		this.userName = userName;
		this.userPass = userPass;
	}
	
	
}
