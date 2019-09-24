package com.fy.customer.service;

import java.util.List;

import com.fy.customer.entity.CustomerFile;

public interface CustomerFileService {

	public CustomerFile getCustomerFileById(int id);
	
	public void addCustomerFile(CustomerFile customerFile);
	
	public void updateCustomerFile(CustomerFile customerFile);
	
	public void deleteCustomerFileById(int id);
	
	public List<CustomerFile> getCustomerFiles();
}
