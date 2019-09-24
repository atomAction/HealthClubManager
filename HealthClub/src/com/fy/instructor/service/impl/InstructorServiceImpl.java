package com.fy.instructor.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fy.instructor.dao.CourseDao;
import com.fy.instructor.dao.InstructorDao;
import com.fy.instructor.dao.ProfessionRankDao;
import com.fy.instructor.entity.Course;
import com.fy.instructor.entity.Instructor;
import com.fy.instructor.entity.ProfessionRank;
import com.fy.instructor.service.InstructorService;

@Service
@Transactional
public class InstructorServiceImpl implements InstructorService {
	
	@Autowired
	private InstructorDao instructorDao;
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private ProfessionRankDao professionRankDao;
	/**
	 * 分页获取
	 */
	public Map<String, Object> queryPageInfo(int limit,int offset,String searchType,String searchText){
		 Map<String, Object> map = new HashMap<String, Object>();
		 map = instructorDao.queryPageInfo(limit, offset,searchType,searchText);		
		 return map;
	 }
	 
	public  void deleteById(int id){
		instructorDao.deleteById(id);;
	}
		
	public void deleteByList( List<Integer> idList) {
		instructorDao.deleteByList(idList);
	}
		

	public  Instructor queryUserById(int id){
		return instructorDao.queryById(id);
	}
		
	public  void update(Instructor instructor){

		instructorDao.update(instructor);
	}
		
	public  void save(Instructor instructor){
		instructorDao.save(instructor);
	}

	public Map<String, Object> queryInstructorById(int id) {
		Map<String,Object> map = new HashMap<String,Object>();
		map = this.queryInstructorForInfoById();
		Instructor instructor = instructorDao.queryById(id);
		map.put("instructor", instructor);
		return map;
	}

	public Map<String, Object> queryInstructorForInfoById() {
		Map<String,Object> map = new HashMap<String,Object>();
		List<Course> courseList = courseDao.queryAllCourse();
		List<ProfessionRank> PRList = professionRankDao.queryAllProfessionRank();
		map.put("courseList", courseList);
		map.put("PRList", PRList);
		return map;
	}
   



}
