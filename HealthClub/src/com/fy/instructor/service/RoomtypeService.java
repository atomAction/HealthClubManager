package com.fy.instructor.service;

import java.util.Map;

import com.fy.instructor.entity.Roomtype;

public interface RoomtypeService {

	public Roomtype queryById(int roomtype); 

	public Map<String, Object> queryPageInfo(int limit,int offset);
	
	public  void deleteById(int id);

	public void save(Roomtype roomtype);


	public void update(Roomtype roomtype);
}
