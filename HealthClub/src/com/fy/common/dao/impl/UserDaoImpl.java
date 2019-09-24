package com.fy.common.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fy.common.dao.UserDao;
import com.fy.common.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	//修改
		public void update(User user) {
			Session session = this.getSession();
			session.update(user);	
		}
		
		//根据id查询
		public User queryById(String username) {
			Session session = this.getSession();
			User user = session.get(User.class, username);
			return user;
		}
}
