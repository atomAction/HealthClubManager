package com.fy.instructor.service;

import java.util.List;
import java.util.Map;

import com.fy.instructor.entity.Instructor;

public interface InstructorService {

	public Map<String, Object> queryPageInfo(int limit,int offset,String searchType,String searchText);
	 
	public  void deleteById(int id);
		
	public void deleteByList( List<Integer> idList) ;

	public  Instructor queryUserById(int id);
		
	public  void update(Instructor instructor);
		
	public  void save(Instructor instructor);

	public Map<String, Object> queryInstructorById(int id) ;
	public Map<String, Object> queryInstructorForInfoById();
	
}
