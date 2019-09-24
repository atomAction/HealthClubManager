package com.fy.instructor.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fy.instructor.entity.Roomtype;
import com.fy.instructor.service.RoomtypeService;

@Controller
public class RoomtypeController {

	@Autowired
	private RoomtypeService roomtypeService;
	
	
	@RequestMapping(value = "/toRoomtypeList")
	public String gotoRoomtypeIndex() {
		return "roomtypePage";
	}
	
	@RequestMapping("/getRoomtypeInfo")
	public @ResponseBody Map<String,Object> getPageInfo(int limit,int offset) throws UnsupportedEncodingException {
		System.out.println("limit is:"+limit);
		System.out.println("offset is:"+ offset);
		Map<String,Object> map = roomtypeService.queryPageInfo(limit, offset);
		return map;
	}
 
	/**
	 * 根据ID 删除客户  
	 * @param id
	 * @return
	 */

	@RequestMapping("/deleteRoomtypeById")
	public @ResponseBody Map<String,Object> deleteById(@RequestParam("id")int id){
		roomtypeService.deleteById(id);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status","success");
		return map;
	}
	
	
	@RequestMapping("/saveRoomtype")
	public @ResponseBody Map<String,Object> saveRoomtype( Roomtype roomtype){
		roomtypeService.save(roomtype);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status","success");
		return map;
	}
	
	
	@RequestMapping("/updateRoomtype")
	public @ResponseBody Map<String,Object> updateRoomtype(Roomtype roomtype){
		roomtypeService.update(roomtype);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status","success");
		return map;
	}
	
	
	@RequestMapping("/queryRoomtypeById")
	public @ResponseBody Map<String,Object> queryRoomtypeById(int number){
		Roomtype roomtype = roomtypeService.queryById(number);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status","success");
		map.put("roomtype",roomtype);
		return map;
	}
}
