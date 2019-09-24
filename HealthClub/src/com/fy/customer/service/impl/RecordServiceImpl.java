package com.fy.customer.service.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fy.customer.dao.RecordDao;
import com.fy.customer.service.RecordService;

@Transactional
@Service
public class RecordServiceImpl implements RecordService {
	
	@Autowired
	private RecordDao recordDao;

	public Map<String, Object> getRecords(int limit, int offset, String searchType, String searchText,Date starttime,Date endtime) {
		
		return recordDao.getRecords(limit, offset,searchType,searchText,starttime,endtime);
	}

	

}
