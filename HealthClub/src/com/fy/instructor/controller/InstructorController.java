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

import com.fy.instructor.entity.Instructor;
import com.fy.instructor.service.CourseService;
import com.fy.instructor.service.InstructorService;
import com.fy.instructor.service.ProfessionRankService;

@Controller
public class InstructorController {

	@Autowired
	private InstructorService instructorService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private ProfessionRankService professionRankService;
	
	/**
	 * 到显示教练页面
	 * @param map 使用的是map保存回显数据
	 * @return
	 */
	@RequestMapping(value = "/toInstructorList")
	public String gotoInstructorIndex() {
		return "instructorPage";
	}
	
	/**
	 * 教练table显示
	 * @param limit
	 * @param offset
	 * @return
	 */
	@RequestMapping(value = "/getInstructorInfo")
	public @ResponseBody Map<String,Object> getPageInfo(int limit,int offset,String searchType,String searchText) throws UnsupportedEncodingException {
		
		System.out.println("limit is:"+limit);
		System.out.println("offset is:"+offset);
		System.out.println("searchType is"+searchType);
		System.out.println("searchText is"+searchText);
		Map<String,Object> map = instructorService.queryPageInfo(limit, offset,searchType,searchText);
		return map;
	}
 
	/**
	 * 根据ID 删除教练  
	 * @param id
	 * @return
	 */

	@RequestMapping("/deleteInstructorById")
	public @ResponseBody Map<String,Object> deleteById(@RequestParam("jobnumber")int id){
		instructorService.deleteById(id);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status","success");
		return map;
	}
	
	
	/**
	 * 根据idlist 批量删除教练
	 * @param idList
	 * @return
	 */
	@RequestMapping("deleteInstructorByList")
	public @ResponseBody Map<String,Object> deleteByList(@RequestParam("idList[]") List<Integer> idList){	
		instructorService.deleteByList(idList);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status","success");
		return map;
	}
	
	
	/**
	 * 根据ID获取某一教练和全部的课程和职级
	 * @param id
	 * @return
	 */
	@RequestMapping("queryInstructorById")
	public @ResponseBody Map<String,Object> queryInstructorById( @RequestParam("jobnumber") int id){
		System.out.println(instructorService.queryUserById(id));
		Map<String,Object> map = new HashMap<String,Object>();
		map = instructorService.queryInstructorById(id);
		return map;
	}
	
	
	@RequestMapping("queryInstructorForInfoById")
	public @ResponseBody Map<String,Object> queryInstructorForInfoById(){
		
		Map<String,Object> map = new HashMap<String,Object>();
		map = instructorService.queryInstructorForInfoById();
		return map;
	}
	/**
	 * 修改教练信息  
	 * 不能修改教练卡类型  只能由充值系统进行更改  （后面再改成可以修改教练卡的）
	 * @param instructor
	 * @return
	 */

	@RequestMapping("updateInstructor")
	public @ResponseBody Map<String,Object> update( int jobnumber,String name,
			String sex,String photo,int coursesId,int professionranksId,double basicsalary){
		
		Instructor instructor = new Instructor
				(jobnumber, name, sex, photo, basicsalary,courseService.queryUserById(coursesId) ,
						professionRankService.queryUserById(professionranksId));
		instructorService.update(instructor);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status", "success");
		return map;
	}
	/**
	 * 新增一个教练（后面再详细处理cardtype这个关联类）
	 * @param instructor
	 * @return
	 */
	@RequestMapping("saveInstructor")
	public @ResponseBody Map<String,Object> save(int jobnumber,String name,
			String sex,String photo,int coursesId,int professionranksId,double basicsalary){
		Instructor instructor = new Instructor
				(jobnumber, name, sex, photo, basicsalary,courseService.queryUserById(coursesId) ,
						professionRankService.queryUserById(professionranksId));
		instructorService.save(instructor);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status", "success");
		return map;
	}

	

//	@RequestMapping("save")
//	public   @ResponseBody Map<String,Object>  save( @RequestParam(value="name", required=false)  String name ,
//			@RequestParam(value="balance", required=false)  double balance ,
//			@RequestParam(value="cardtype", required=false)  String cardtype,
//			@RequestParam(value= "phone", required=false)  String phone){
//		instructorService.saveInstructor(name,balance,cardtype,phone);
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("status", "success");	
//		return map;
//	}
}
