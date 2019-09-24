package com.fy.customer.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fy.customer.dao.CardTypeDao;
import com.fy.customer.dao.CustomerDao;
import com.fy.customer.entity.Customer;
import com.fy.customer.service.CustomerService;


@Transactional
@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private CardTypeDao cardTypeDao;
	
	
	
	/**
	 * 这里由于新加客户  不用输入会员卡有效期  而是根据所办的会员卡类型来进行计算以当前时间加上会员卡所对应的时间的有效期
	 * @param phone 
	 * @param cardtype 
	 * @param balance 
	 * @param name 
	 */
    public void saveCustomer(String name, double balance, String cardtype, String phone) {
    	Date date = new Date();
    	SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
    	dateFormat.format(date);
    	int t_id = 0;
	    if("ji".equals(cardtype)) {
	        Calendar ca = Calendar.getInstance();
	        ca.add(Calendar.DATE, 90);// num为增加的天数，可以改变的
	        date = ca.getTime();
	        t_id =1;    
	    }else if("yue".equals(cardtype)) {
	    	 Calendar ca = Calendar.getInstance();
	         ca.add(Calendar.DATE, 30);// num为增加的天数，可以改变的
	         date = ca.getTime();
	         t_id =2;
	    }
    	
    	Customer customer = new Customer(phone,name,balance,date);
    	customer.setCardtype(cardTypeDao.queryById(t_id));
    	
    	customerDao.save(customer);
    }
    
     
	/**
	 * 分页获取
	 */
	public Map<String, Object> queryPageInfo(int limit,int offset,String searchType,String searchText){
		 Map<String, Object> map = new HashMap<String, Object>();
		 map = customerDao.queryPageInfo(limit, offset,searchType,searchText);		
		 return map;
	 }
	 
	public  void deleteById(int id){
		customerDao.deleteById(id);;
	}
		
	public void deleteByList( List<Integer> idList) {
		customerDao.deleteByList(idList);
	}
		

	public  Customer queryUserById(int id){
		return customerDao.queryById(id);
	}
		
	public  void update(Customer customer){


		customerDao.update(customer);
	}
		
	public  void save(Customer customer){
		customerDao.save(customer);
	}


	public Customer queryCustomerByName(String name) {
		
		return customerDao.queryByName(name);
	}
   



	
}
