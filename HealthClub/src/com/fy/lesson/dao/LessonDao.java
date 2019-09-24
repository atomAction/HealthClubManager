package com.fy.lesson.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fy.lesson.entity.Lesson;

public interface LessonDao {

	public Map<String, Object> queryPageInfo(int limit, int offset,String searchType,String searchText) ;

	public Lesson getLessonById(int id);

	public void addLesson(Lesson lesson) ;
	public void updateLesson(Lesson lesson);
	public void deleteLessonById(int id) ;
	public List<Lesson> getLessons() ;


	public List<Integer> queryByStartTimeforIn(Date starttime);


	public List<Integer> queryByStartTimeforRoom(Date starttime) ;

	public Map<String, Object> getStudentInfo(int id);


	public Map<String, Object> queryLessonsForCoustomer(int limit, int offset, String searchType, String searchText) ;

	public List<Lesson> queryByMonthForLess(Date beforedate, Date nowdate) ;
}
