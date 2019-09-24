package com.fy.instructor.dao;

import java.util.List;
import java.util.Map;

import com.fy.instructor.entity.Room;
import com.fy.lesson.entity.Lesson;

public interface RoomDao {
	

	public Map<String, Object> queryPageInfo(int limit, int offset) ;
	public Room queryById(int id);

	public void deleteById(int id) ;
	public void deleteByList(List<Integer> idList) ;
	
	
	public void update(Room room) ;
	
	
	public void save(Room room) ;
	public List<Room> queryBycourseId(int canteach) ;
	public int queryByLessonForcapacity(Lesson lesson);
}
