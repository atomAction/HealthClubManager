package com.fy.lesson.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fy.common.entity.MaterialEnums.Status;
import com.fy.customer.dao.CustomerDao;
import com.fy.customer.entity.Customer;
import com.fy.instructor.dao.CourseDao;
import com.fy.instructor.dao.InstructorDao;
import com.fy.instructor.dao.RoomDao;
import com.fy.instructor.entity.Course;
import com.fy.instructor.entity.Instructor;
import com.fy.instructor.entity.Room;
import com.fy.lesson.dao.LessonDao;
import com.fy.lesson.entity.Lesson;
import com.fy.lesson.service.LessonService;

@Transactional
@Service("lessonService")
public class LessonServiceImpl implements LessonService {

	@Autowired
	private LessonDao lessonDao;
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private InstructorDao instructorDao;
	@Autowired
	private RoomDao roomDao;
	@Autowired
	private CustomerDao customerDao;
	
	
	/**
	 * 获得全部课程
	 */
	public List<Course> queryCourses() {
		List<Course> courseList = courseDao.queryAllCourse();
		return courseList;	
	}

	/**
	 * 排课
	 * 
	 * select中的值动态获取
	 */
	public List<Instructor> queryInstructors( int CourseId ,Date starttime) {
		String canteach = courseDao.queryById(CourseId).getName();
		List<Instructor> instructorList = new ArrayList<Instructor>();
		instructorList = instructorDao.queryBycourses(canteach);
		
		List<Integer> jobnumberList = lessonDao.queryByStartTimeforIn(starttime);
		//对已经有安排的教练进行删除
		Iterator<Instructor> it = instructorList.iterator();
		while(it.hasNext()){
			Instructor x = it.next();
			for(Integer i:jobnumberList) {
				 if(x.getJobnumber() == i.intValue()){
				        it.remove();
				    }	
			}   
		}	
		return instructorList;
	}

	public List<Room> queryRooms(int courseId,Date stattime) {
		int canteach1 = courseDao.queryById(courseId).getRoomtype_number();
		List<Room> roomList = new ArrayList<Room>();
		roomList = roomDao.queryBycourseId(canteach1);
		
		List<Integer> room_numberList = lessonDao.queryByStartTimeforRoom(stattime);
		//对已经有安排的教室进行删除
		Iterator<Room> ro = roomList.iterator();
		while(ro.hasNext()){
			Room x = ro.next();
			for(Integer i:room_numberList) {
				 if(x.getNumber() == i.intValue()){
					 ro.remove();
				    }	
			}   
		}	
		return roomList;
	}




	public Lesson getLessonById(int id) {
		return lessonDao.getLessonById(id);
	}

	public void addLesson(Lesson lesson) {
		lessonDao.addLesson(lesson);
	}

	public void updateLesson(Lesson lesson) {
		lessonDao.updateLesson(lesson);
	}

	public void deleteLessonById(int id) {
		lessonDao.deleteLessonById(id);

	}

	public List<Lesson> getLessons() {	
		return lessonDao.getLessons();
	}

	
	/**
	 * 用于添加课程时，endtime用到的函数
	 */
	public Date addDateMinut(Date date, int x) {  
       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 24小时制  
    //  Date date1 = null;  
    //    System.out.println("front:" + format.format(date));  
        Calendar cal = Calendar.getInstance();  
        cal.setTime(date);  
        cal.add(Calendar.MINUTE, x);// 24小时制  
        date = cal.getTime();  
        format.format(date);  
        cal = null;  
        return date;  
  
    }  
	

	public void saveLessonByN(int number, int jobnumber, int room_number,Date starttime) {
		Lesson lesson = new Lesson(courseDao.queryById(number),
				instructorDao.queryById(jobnumber),
				roomDao.queryById(room_number),starttime);
		lesson.setEndtime(addDateMinut(starttime, lesson.getCourseType().getCoursetime()));
		lesson.setStatus(Status.正在报名);
		lessonDao.addLesson(lesson);
	}
	

	public void updateLessonByN(int number, int jobnumber, int room_number,Date starttime) {
		Lesson lesson = new Lesson(courseDao.queryById(number),
				instructorDao.queryById(jobnumber),
				roomDao.queryById(room_number),starttime);
		lesson.setEndtime(addDateMinut(starttime, lesson.getCourseType().getCoursetime()));
		lesson.setStatus(Status.正在报名);
		lessonDao.addLesson(lesson);
		lessonDao.updateLesson(lesson);
	}

	public Map<String, Object> queryPageInfo(int limit,int offset,String searchType,String searchText){
		 Map<String, Object> map = new HashMap<String, Object>();
		 map = lessonDao.queryPageInfo(limit, offset,searchType,searchText);		
		 return map;
	 }

	public Map<String, Object> getStudentInfo(int id) {
		
		return lessonDao.getStudentInfo(id);
	}

	public Map<String, Object> queryLessonsForCoustomer(int limit, int offset, String searchType, String searchText) {
		return lessonDao.queryLessonsForCoustomer(limit, offset, searchType, searchText);
	}

	public void bookLessonById(int id, int c_id) {
		Customer customer = customerDao.queryById(c_id);
		Lesson lesson = lessonDao.getLessonById(id);
		Set<Customer> students = new HashSet<Customer>();
		students = lesson.getStudents();
		students.add(customer);
		lesson.setStudents(students);
		lessonDao.updateLesson(lesson);
	}


	public List<Lesson> queryByMonthForLess(Date beforedate, Date nowdate) {
		
		return lessonDao.queryByMonthForLess(beforedate, nowdate);
	}

}
