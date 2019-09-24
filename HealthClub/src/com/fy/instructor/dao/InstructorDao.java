package com.fy.instructor.dao;

import java.util.List;
import java.util.Map;

import com.fy.instructor.entity.Instructor;

public interface InstructorDao {

	public Map<String, Object> queryPageInfo(int limit, int offset,String searchType,String searchText);
	public Instructor queryById(int id) ;
	public void deleteById(int id) ;
	public void deleteByList(List<Integer> idList) ;
	public void update(Instructor instructor);
	public void save(Instructor instructor) ;
	public List<Instructor> queryBycourses(String canteach) ;
	public List<Instructor> getAll() ;
	
	
}
