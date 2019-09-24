package com.fy.instructor.service;

import java.util.List;
import java.util.Map;

import com.fy.instructor.entity.Room;

public interface RoomService {

	public Map<String, Object> queryPageInfo(int limit,int offset);
	 
	public  void deleteById(int id);
		
	public void deleteByList( List<Integer> idList); 
		

	public  Map<String,Object> queryUserById(int id);
	
	
	public  Map<String,Object> queryRoomInfoById();
	
		
	public  void update(Room room);
		
	public  void save(Room room);

}
