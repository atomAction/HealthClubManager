package com.fy.instructor.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fy.instructor.entity.Course;
import com.fy.instructor.service.CourseService;

@Controller
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@RequestMapping("/toCourseList")
	public String gotoCourse() {
		return "coursePage";
	}
	
	@RequestMapping("/getCourseInfo")
	public @ResponseBody Map<String,Object> getPageInfo(int limit,int offset,String searchType,String searchText) throws UnsupportedEncodingException {
		searchText = new String(searchText.getBytes("ISO-8859-1"),"UTF-8"); 
		System.out.println("limit is:"+limit);
		System.out.println("offset is:"+offset);
		System.out.println("searchType is"+searchType);
		System.out.println("searchText is"+searchText);
		Map<String,Object> map = courseService.queryPageInfo(limit, offset,searchType,searchText);
		return map;
	}
	
	/**
	 * 根据ID 删除客户  
	 * @param id
	 * @return
	 */

	@RequestMapping("/deleteCourseById")
	public @ResponseBody Map<String,Object> deleteById(@RequestParam("id")int id){
		courseService.deleteById(id);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status","success");
		return map;
	}
	
	
	/**
	 * 根据idlist 批量删除客户
	 * @param idList
	 * @return
	 */
	@RequestMapping("/deleteCourseByList")
	public @ResponseBody Map<String,Object> deleteByList(@RequestParam("idList[]") List<Integer> idList){	
		courseService.deleteByList(idList);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status","success");
		return map;
	}
	
	
	/**
	 * 根据ID获取某一客户
	 * @param id
	 * @return
	 */
	@RequestMapping("/queryCourseById")
	public @ResponseBody Course queryUserById( @RequestParam("jobnumber") int id){
		System.out.println(courseService.queryUserById(id));
		Course course = courseService.queryUserById(id);	
		return course;
	}
	
	/**
	 * 修改客户信息  
	 * 不能修改客户卡类型  只能由充值系统进行更改  （后面再改成可以修改客户卡的）
	 * @param customer
	 * @return
	 */

	@RequestMapping("/updateCourse")
	public @ResponseBody Map<String,Object> update( Course course){
		System.out.println(course);
		courseService.update(course);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status", "success");
		return map;
	}
	
	
	@RequestMapping("/saveCourse")
	public @ResponseBody Map<String,Object> saveCourse( Course course){
		System.out.println(course);
		courseService.save(course);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status", "success");
		return map;
	}
	
}
