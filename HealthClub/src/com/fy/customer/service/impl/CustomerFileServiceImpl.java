package com.fy.customer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fy.customer.dao.CustomerFileDao;
import com.fy.customer.entity.CustomerFile;
import com.fy.customer.service.CustomerFileService;

@Transactional
@Service
public class CustomerFileServiceImpl implements CustomerFileService {

	@Autowired
	private CustomerFileDao customerFileDao;
	
	@Override
	public CustomerFile getCustomerFileById(int id) {
		// TODO Auto-generated method stub
		return customerFileDao.getCustomerFileById(id);
	}

	@Override
	public void addCustomerFile(CustomerFile customerFile) {
		// TODO Auto-generated method stub
		customerFileDao.addCustomerFile(customerFile);
		
	}

	@Override
	public void updateCustomerFile(CustomerFile customerFile) {
		// TODO Auto-generated method stub
		customerFileDao.updateCustomerFile(customerFile);
	}


	@Override
	public List<CustomerFile> getCustomerFiles() {
		// TODO Auto-generated method stub
		return customerFileDao.getCustomerFiles();
	}

	@Override
	public void deleteCustomerFileById(int id) {
		// TODO Auto-generated method stub
		
	}



}
