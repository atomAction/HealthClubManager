package com.fy.customer.service;

import java.util.List;
import java.util.Map;

import com.fy.customer.entity.Customer;

public interface CustomerService {


    public void saveCustomer(String name, double balance, String cardtype, String phone);
    
	public Map<String, Object> queryPageInfo(int limit,int offset,String searchType,String searchText);
	 
	public  void deleteById(int id);
		
	public void deleteByList( List<Integer> idList) ;

	public  Customer queryUserById(int id);
		
	public  void update(Customer customer);
		
	public  void save(Customer customer);


	public Customer queryCustomerByName(String name);
   
}
