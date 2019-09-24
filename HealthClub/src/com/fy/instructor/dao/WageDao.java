package com.fy.instructor.dao;

import java.text.ParseException;
import java.util.Map;

import com.fy.instructor.entity.Wage;

public interface WageDao {

	public Map<String, Object> getWageInFo(int limit,int offset,String searchType,String searchText) throws ParseException ;

	//根据id查询
	public Wage queryById(int id);
	
	//根据id删除
	public void deleteById(int id) ;
	
	
	//修改
	public void update(Wage wage);
	
	//新增
	public void save(Wage wage) ;
}
