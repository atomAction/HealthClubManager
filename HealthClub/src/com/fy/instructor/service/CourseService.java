package com.fy.instructor.service;

import java.util.List;
import java.util.Map;

import com.fy.instructor.entity.Course;

public interface CourseService {

	public Map<String, Object> queryPageInfo(int limit,int offset,String searchType,String searchText);
	 
	public  void deleteById(int id);
		
	public void deleteByList( List<Integer> idList) ;
		

	public  Course queryUserById(int id);
		
	public  void update(Course course);
		
	public  void save(Course course);
	
}
