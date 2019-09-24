package com.fy.lesson.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fy.instructor.entity.Course;
import com.fy.instructor.entity.Instructor;
import com.fy.instructor.entity.Room;
import com.fy.lesson.entity.Lesson;

public interface LessonService {

	public List<Course> queryCourses();

	public List<Instructor> queryInstructors( int CourseId ,Date starttime);

	public List<Room> queryRooms(int courseId,Date stattime) ;



	public Lesson getLessonById(int id);
	public void addLesson(Lesson lesson);

	public void updateLesson(Lesson lesson) ;
	public void deleteLessonById(int id);
	public List<Lesson> getLessons() ;

	public Date addDateMinut(Date date, int x);
	public void saveLessonByN(int number, int jobnumber, int room_number,Date starttime);

	public void updateLessonByN(int number, int jobnumber, int room_number,Date starttime);

	public Map<String, Object> queryPageInfo(int limit,int offset,String searchType,String searchText);

	public Map<String, Object> getStudentInfo(int id);

	public Map<String, Object> queryLessonsForCoustomer(int limit, int offset, String searchType, String searchText) ;

	public void bookLessonById(int id, int c_id) ;
	public List<Lesson> queryByMonthForLess(Date beforedate, Date nowdate);
}
