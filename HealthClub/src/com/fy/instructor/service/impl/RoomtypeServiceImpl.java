package com.fy.instructor.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fy.instructor.dao.RoomtypeDao;
import com.fy.instructor.entity.Roomtype;
import com.fy.instructor.service.RoomtypeService;

@Service
@Transactional
public class RoomtypeServiceImpl implements RoomtypeService {

	
	@Autowired
	private RoomtypeDao roomtypeDao;

	public Roomtype queryById(int roomtype) {
		
		return roomtypeDao.queryById(roomtype);
	}


	public Map<String, Object> queryPageInfo(int limit,int offset){
		 Map<String, Object> map = new HashMap<String, Object>();
		 map = roomtypeDao.queryPageInfo(limit, offset);		
		 return map;
	 }
	public  void deleteById(int id){
		roomtypeDao.deleteById(id);;
	}


	public void save(Roomtype roomtype) {
		roomtypeDao.save(roomtype);
		
	}


	public void update(Roomtype roomtype) {
		roomtypeDao.updateRoomtype(roomtype);
		
	}
}
