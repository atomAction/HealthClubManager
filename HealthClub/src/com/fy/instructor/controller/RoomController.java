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

import com.fy.instructor.entity.Room;
import com.fy.instructor.service.RoomService;
import com.fy.instructor.service.RoomtypeService;

@Controller
public class RoomController {

	@Autowired
	private RoomService roomService;
	@Autowired
	private RoomtypeService roomtypeService;
	
	
	@RequestMapping(value = "/toRoomList")
	public String gotoRoomIndex() {
		return "roomPage";
	}
	
	@RequestMapping("/getRoomInfo")
	public @ResponseBody Map<String,Object> getPageInfo(int limit,int offset) throws UnsupportedEncodingException {
		System.out.println("limit is:"+limit);
		System.out.println("offset is:"+ offset);
		Map<String,Object> map = roomService.queryPageInfo(limit, offset);
		return map;
	}
 
	/**
	 * 根据ID 删除客户  
	 * @param id
	 * @return
	 */

	@RequestMapping("/deleteRoomById")
	public @ResponseBody Map<String,Object> deleteById(@RequestParam("id")int id){
		roomService.deleteById(id);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status","success");
		return map;
	}
	
	
	/**
	 * 根据idlist 批量删除客户
	 * @param idList
	 * @return
	 */
	@RequestMapping("/deleteRoomByList")
	public @ResponseBody Map<String,Object> deleteByList(@RequestParam("idList[]") List<Integer> idList){	
		roomService.deleteByList(idList);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status","success");
		return map;
	}
	
	
	/**
	 * 根据ID获取某一客户
	 * @param id
	 * @return
	 */
	@RequestMapping("/queryRoomById")
	public @ResponseBody Map<String,Object> queryUserById( @RequestParam("id") int id){
		Map<String,Object> map = new HashMap<String,Object>();
		map= roomService.queryUserById(id);	
		return map;
	}
	
	@RequestMapping("/queryRoomInfoById")
	public @ResponseBody Map<String,Object> queryRoomInfoById( ){
		Map<String,Object> map = new HashMap<String,Object>();
		map= roomService.queryRoomInfoById();	
		return map;
	}
	/**
	 * 修改客户信息  
	 * 不能修改客户卡类型  只能由充值系统进行更改  （后面再改成可以修改客户卡的）
	 * @param customer
	 * @return
	 */

	@RequestMapping("/updateRoom")
	public @ResponseBody Map<String,Object> update(int number,int roomtype,int capacity,String roomname){
		Room room = new Room(number, roomtypeService.queryById(roomtype), capacity, roomname);
		roomService.update(room);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status", "success");
		return map;
	}
	
	@RequestMapping("/saveRoom")
	public @ResponseBody Map<String,Object> save(int roomtype,int capacity,String roomname){
		Room room = new Room(roomtypeService.queryById(roomtype), capacity, roomname);
		roomService.save(room);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status", "success");
		return map;
	}
	
}
