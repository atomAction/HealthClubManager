package com.fy.customer.dao;

import java.util.List;
import java.util.Map;

import com.fy.customer.entity.CardType;

public interface CardTypeDao {


	public Map<String, Object> queryPageInfo(int limit, int offset) ;
	public CardType queryById(int id) ;
	public void deleteById(int id) ;
	public void deleteByList(List<Integer> idList) ;
	
	public void update(CardType cardType) ;
	

	public void save(CardType cardType);
}
