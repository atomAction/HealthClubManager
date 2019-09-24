package com.fy.instructor.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fy.instructor.dao.RoomDao;
import com.fy.instructor.dao.RoomtypeDao;
import com.fy.instructor.entity.Room;
import com.fy.instructor.entity.Roomtype;
import com.fy.instructor.service.RoomService;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomDao roomDao;
	@Autowired
	private RoomtypeDao roomtypeDao;
	 
	/**
	 * 分页获取
	 */
	public Map<String, Object> queryPageInfo(int limit,int offset){
		 Map<String, Object> map = new HashMap<String, Object>();
		 map = roomDao.queryPageInfo(limit, offset);		
		 return map;
	 }
	 
	public  void deleteById(int id){
		roomDao.deleteById(id);;
	}
		
	public void deleteByList( List<Integer> idList) {
		roomDao.deleteByList(idList);
	}
		

	public  Map<String,Object> queryUserById(int id){
		Map<String,Object> map = new HashMap<String,Object>();
		Room room = roomDao.queryById(id);
		List<Roomtype> roomtypeList =  roomtypeDao.getAllRoomtype();
		map.put("room", room);
		map.put("roomtypeList", roomtypeList);
		return map;
	}
	
	
	public  Map<String,Object> queryRoomInfoById(){
		Map<String,Object> map = new HashMap<String,Object>();
		List<Roomtype> roomtypeList =  roomtypeDao.getAllRoomtype();
		map.put("roomtypeList", roomtypeList);
		return map;
	}
	
		
	public  void update(Room room){

		roomDao.update(room);
	}
		
	public  void save(Room room){
		roomDao.save(room);
	}

}
