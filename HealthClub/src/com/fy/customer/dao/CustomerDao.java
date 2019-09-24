package com.fy.customer.dao;

import java.util.List;
import java.util.Map;

import com.fy.customer.entity.Customer;

public interface CustomerDao {


	public Map<String, Object> queryPageInfo(int limit, int offset,String searchType,String searchText);
	public Customer queryById(int id) ;

	public void deleteById(int id) ;
	public void deleteByList(List<Integer> idList) ;
	public void update(Customer customer);
	public void save(Customer customer);




	public Customer queryByName(String searchText) ;

	

}
