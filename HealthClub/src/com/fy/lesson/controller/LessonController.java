package com.fy.lesson.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fy.instructor.entity.Course;
import com.fy.instructor.entity.Instructor;
import com.fy.instructor.entity.Room;
import com.fy.lesson.service.LessonService;

@Controller
public class LessonController {

	@Autowired
	private LessonService lessonService;
	

	/**
	 * 用于将前端的时期从字符串转为后台的时间类型
	 * @param binder
	 */
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
       binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));//第二个参数是控制是否支持传入的值是空，这个值很关键，如果指定为false，那么如果前台没有传值的话就会报错
    }
	
	@RequestMapping("/toSchedulLesson")
	public String gotoSchedulPage() {	
		return "schedulPage";
	}
	
	@RequestMapping("/queryCourse")
	public @ResponseBody List<Course> queryCourses() {	
		return lessonService.queryCourses();	
	}
	
	@RequestMapping("/queryLessonIns")
	public @ResponseBody List<Instructor> queryInstructors(@RequestParam("number")int courseId,
			@RequestParam("starttime") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date starttime){
		System.out.println(starttime);
//		DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
	//	Date date = Date.parse(starttime,format);
		return lessonService.queryInstructors(courseId,starttime);
	}
	

	@RequestMapping("/queryLessonRoom")
	public @ResponseBody List<Room> queryRooms(@RequestParam("number") int courseId,
			@RequestParam("starttime")@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date starttime){
		return lessonService.queryRooms(courseId,starttime);
	}
	
	@RequestMapping("/saveLesson")
	public @ResponseBody Map<String,Object> saveLesson(@RequestParam(value = "number")int number,
			@RequestParam(value = "jobnumber")int jobnumber,
			@RequestParam(value = "room_number")int room_number,
			@RequestParam("starttime")@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date starttime){
		Map<String,Object> map = new HashMap<String,Object>();
		lessonService.saveLessonByN(number,jobnumber,room_number,starttime);
		map.put("status", "success");
		return map;
	}
	
	@RequestMapping("/updeteLesson")
	public @ResponseBody Map<String,Object> updateLesson(@RequestParam(value = "number")int number,
			@RequestParam(value = "jobnumber")int jobnumber,
			@RequestParam(value = "room_number")int room_number,
			@RequestParam("starttime")@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date starttime){
		Map<String,Object> map = new HashMap<String,Object>();
		lessonService.updateLessonByN(number,jobnumber,room_number,starttime);
		map.put("status", "success");
		return map;
	}
	
	@RequestMapping("/getLessonInfo")
	public @ResponseBody Map<String,Object> getPageInfo(int limit,int offset,String searchType,@RequestParam("searchText")String searchText) throws UnsupportedEncodingException {
		//searchText = new String(searchText.getBytes("ISO-8859-1"),"UTF-8"); 
		System.out.println("limit is:"+limit);
		System.out.println("offset is:"+offset);
		System.out.println("searchType is"+searchType);
		System.out.println("searchText is"+searchText);
		Map<String,Object> map = lessonService.queryPageInfo(limit, offset,searchType,searchText);
		return map;
	}
	
	@RequestMapping("/getLessonStudentInfo")
	public @ResponseBody Map<String,Object> getLessonStudentInfo( @RequestParam("parentid")int id)  {
	//	System.out.println(id);
		Map<String,Object> map = lessonService.getStudentInfo(id);
		return map;
	}
	@RequestMapping("/deleteLessonById")
	public @ResponseBody Map<String,Object> deleteLessonById( @RequestParam("number")int id)  {
	//	System.out.println(id);
		lessonService.deleteLessonById(id);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status","success");
		return map;
	}
	
	@RequestMapping("/getLessonsForCoustomer")
	public @ResponseBody Map<String,Object> getLessonsForCoustomer(int limit,int offset,String searchType,String searchText) throws UnsupportedEncodingException {
		
		System.out.println("limit is:"+limit);
		System.out.println("offset is:"+offset);
		System.out.println("searchType is"+searchType);
		System.out.println("searchText is"+searchText);
		Map<String,Object> map = lessonService.queryLessonsForCoustomer(limit, offset,searchType,searchText);
		return map;
	}
	
	@RequestMapping("/bookLessonById")
	public @ResponseBody Map<String,Object> bookLessonById( @RequestParam("id")int id,@RequestParam("c_id") int c_id)  {
		lessonService.bookLessonById(id,c_id);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status","success");
		return map;
	}
	
}
