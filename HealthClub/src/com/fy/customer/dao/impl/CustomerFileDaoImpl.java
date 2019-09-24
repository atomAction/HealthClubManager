package com.fy.customer.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fy.customer.dao.CustomerFileDao;
import com.fy.customer.entity.CustomerFile;

@Repository
public class CustomerFileDaoImpl implements CustomerFileDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * 查
	 */

	public CustomerFile getCustomerFileById(int id) {
		return (CustomerFile) this.getSession().createQuery("from CustomerFile where f_id = :id").setParameter("id", id).uniqueResult();
	}

	/**
	 * 增
	 */

	public void addCustomerFile(CustomerFile customerFile) {
		
		this.getSession().save(customerFile);
	}

	/**
	 * 改
	 */

	public void updateCustomerFile(CustomerFile customerFile) {
		
		this.getSession().update(customerFile);
	}

	

	/**
	 * 查所有
	 */
	@SuppressWarnings("unchecked")
	public List<CustomerFile> getCustomerFiles() {
		
		return this.getSession().createQuery("form CustomerFile").list();
	}


}
