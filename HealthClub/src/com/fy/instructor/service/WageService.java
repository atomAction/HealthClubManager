package com.fy.instructor.service;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import com.fy.instructor.entity.Wage;

public interface WageService {

	public Map<String, Object> getWageInFo(int limit,int offset,String searchType,String searchText) throws ParseException ;

	public void save(Wage wage) ;

	public  void wagebymonth() ;
	
	public  Date getBeforeMonth(); 
}
