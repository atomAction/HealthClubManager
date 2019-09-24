package com.fy.customer.dao;

import java.util.Date;
import java.util.Map;

import com.fy.customer.entity.Record;

public interface RecordDao {

	public Map<String, Object> getRecords(int limit, int offset, String searchType, String searchText,Date starttime,Date endtime);
		public void save(Record record) ;

		public Map<String,Object> getChartBytime(Date starttime, Date endtime, int texttype, int search) ;
}
