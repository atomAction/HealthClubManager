package com.fy.instructor.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fy.instructor.dao.CourseDao;
import com.fy.instructor.entity.Course;
import com.fy.instructor.service.CourseService;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	private CourseDao courseDao;

	/**
	 * 分页获取
	 */
	public Map<String, Object> queryPageInfo(int limit,int offset,String searchType,String searchText){
		 Map<String, Object> map = new HashMap<String, Object>();
		 map = courseDao.queryPageInfo(limit, offset,searchType,searchText);		
		 return map;
	 }
	 
	public  void deleteById(int id){
		courseDao.deleteById(id);
	}
		
	public void deleteByList( List<Integer> idList) {
		courseDao.deleteByList(idList);
	}
		

	public  Course queryUserById(int id){
		return courseDao.queryById(id);
	}
		
	public  void update(Course course){

		courseDao.update(course);
	}
		
	public  void save(Course course){
		courseDao.save(course);
	}

}
