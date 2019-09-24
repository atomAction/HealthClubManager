package com.fy.instructor.dao;

import java.util.List;
import java.util.Map;

import com.fy.instructor.entity.Roomtype;

	public interface RoomtypeDao {

		public Map<String, Object> queryPageInfo(int limit, int offset) ;
		
		
		public void deleteById(int id) ;

		public Roomtype queryById(int id) ;
		


		public void updateRoomtype(Roomtype roomtype) ;
		

		public void deleteRoomtypeById(int id) ;


		
		public List<Roomtype> getRoomtypes(); 

		public List<Roomtype> getAllRoomtype(); 


		public void save(Roomtype roomtype) ;

}
