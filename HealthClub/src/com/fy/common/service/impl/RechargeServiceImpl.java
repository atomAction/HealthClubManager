package com.fy.common.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fy.common.entity.MaterialEnums.RecordTypes;
import com.fy.common.service.RechargeService;
import com.fy.customer.dao.CustomerDao;
import com.fy.customer.dao.RecordDao;
import com.fy.customer.entity.Customer;
import com.fy.customer.entity.Record;

@Service
@Transactional
public class RechargeServiceImpl implements RechargeService {

	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private RecordDao recordDao;
	
	
	public void doRecharge(int id ,double balance) {
		
		Customer customer = customerDao.queryById(id);
		customer.setBalance(balance+customer.getBalance());
		customerDao.update(customer);
		Date date = new Date();
		Record record = new Record(date, balance, RecordTypes.充值, customer);
		recordDao.save(record);
	}


	

}
