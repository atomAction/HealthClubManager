package com.fy.customer.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fy.customer.dao.CardTypeDao;
import com.fy.customer.entity.CardType;
import com.fy.customer.service.CardTypeService;


@Transactional
@Service
public class CardTypeServiceImpl implements CardTypeService {
	
	@Autowired
	private CardTypeDao cardTypeDao;
	
	public Map<String, Object> queryPageInfo(int limit,int offset){
		 Map<String, Object> map = new HashMap<String, Object>();
		 map = cardTypeDao.queryPageInfo(limit, offset);		
		 return map;
	 }
	 
	public  void deleteById(int id){
		cardTypeDao.deleteById(id);
	}
		
	public void deleteByList( List<Integer> idList) {
		cardTypeDao.deleteByList(idList);
	}
		

	public  CardType queryUserById(int id){
		return cardTypeDao.queryById(id);
	}
		
	public  void update(CardType cardType){
		cardTypeDao.update(cardType);
	}
		
	public  void save(CardType cardType){
		cardTypeDao.save(cardType);
	}

	
	
}
