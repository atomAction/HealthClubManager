package com.fy.instructor.dao;

import java.util.List;
import java.util.Map;

import com.fy.instructor.entity.Course;

public interface CourseDao {


	public List<Course> queryAllCourse();
	public Course queryById(int id) ;
	
	public void deleteById(int id) ;
	public void deleteByList(List<Integer> idList);
	public void update(Course course) ;
	public void save(Course course) ;
	public Map<String, Object> queryPageInfo(int limit, int offset,String searchType,String searchText);


	public List<Course> getAll() ;
	
	
}
