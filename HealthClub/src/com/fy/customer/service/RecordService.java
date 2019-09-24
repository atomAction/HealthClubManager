package com.fy.customer.service;

import java.util.Date;
import java.util.Map;


public interface RecordService {

public Map<String, Object> getRecords(int limit, int offset, String searchType, String searchText,Date starttime,Date endtime);
}
