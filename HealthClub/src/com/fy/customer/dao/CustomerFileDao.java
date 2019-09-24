package com.fy.customer.dao;

import java.util.List;

import com.fy.customer.entity.CustomerFile;

public interface CustomerFileDao {
	
	public CustomerFile getCustomerFileById(int id) ;

	public void addCustomerFile(CustomerFile customerFile);

	public void updateCustomerFile(CustomerFile customerFile);
	

;
	public List<CustomerFile> getCustomerFiles() ;
}
