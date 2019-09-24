package com.fy.customer.service;

import java.util.List;
import java.util.Map;

import com.fy.customer.entity.CardType;

public interface CardTypeService {

	public Map<String, Object> queryPageInfo(int limit,int offset);
	 
	public  void deleteById(int id);
		
	public void deleteByList( List<Integer> idList);	

	public  CardType queryUserById(int id);
		
	public  void update(CardType cardType);
		
	public  void save(CardType cardType);
}
