package com.fy.common.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fy.common.entity.MaterialEnums.RecordTypes;
import com.fy.common.service.PurchaseService;
import com.fy.customer.dao.CardTypeDao;
import com.fy.customer.dao.CustomerDao;
import com.fy.customer.dao.RecordDao;
import com.fy.customer.entity.CardType;
import com.fy.customer.entity.Customer;
import com.fy.customer.entity.Record;

@Service
@Transactional
public class PurchaseServiceImpl implements PurchaseService {

	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private CardTypeDao cardTypeDao;
	@Autowired
	private RecordDao recordDao;
	
	
	/**
	 * 执行购买  并且判断是否足够
	 * @param id
	 * @param cardtypeId
	 * @return
	 */
	public Map<String,Object> doPurchase(int id ,int cardtypeId) {
		
		Customer customer = customerDao.queryById(id);
		CardType cardtype = cardTypeDao.queryById(cardtypeId);
		customer.setCardtype(cardtype);
		double overage = customer.getBalance()-cardtype.getPrice();
		Map<String,Object> map = new HashMap<String,Object>();
		if( overage< 0) {
			 map.put("isenough", "no");
		 return map;
				
		}
		customer.setBalance(customer.getBalance()-cardtype.getPrice());
		Calendar ca = Calendar.getInstance();
		ca.setTime(customer.getEffectivedeadline());
		ca.add(Calendar.DATE, cardtype.getEffectivetime());
		Date date1 = ca.getTime();
		customer.setEffectivedeadline(date1);
		customerDao.update(customer);
		map.put("isenough", "yes");
		map.put("statu", "success");
		
		Date date = new Date();
		Record record = new Record(date, cardtype.getPrice(), RecordTypes.购卡,customer);
		recordDao.save(record);
		
		return map;
	}
}
