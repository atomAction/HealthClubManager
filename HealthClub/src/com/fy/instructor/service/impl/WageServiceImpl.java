package com.fy.instructor.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fy.instructor.dao.InstructorDao;
import com.fy.instructor.dao.WageDao;
import com.fy.instructor.entity.Instructor;
import com.fy.instructor.entity.Wage;
import com.fy.instructor.service.WageService;
import com.fy.lesson.dao.impl.LessonDaoImpl;
import com.fy.lesson.entity.Lesson;

@Service("wageServiceImpl")
@Transactional
public class WageServiceImpl implements WageService {

	@Autowired
	private WageDao wageDao;
	@Autowired
	private LessonDaoImpl lessonDao;
	@Autowired
	private InstructorDao instructorDao;

	
	public Map<String, Object> getWageInFo(int limit,int offset,String searchType,String searchText) throws ParseException {
		return wageDao.getWageInFo(limit,offset,searchType,searchText);
	}

	public void save(Wage wage) {
		
		wageDao.save(wage);
	}

	public  void wagebymonth() {
		
		Date nowdate = new Date();
		Date beforedate = this.getBeforeMonth();
			
		
		List<Lesson> lessonList = new ArrayList<Lesson>();
		List<Instructor> allInstructor = new ArrayList<Instructor>();
		allInstructor = instructorDao.getAll();
		lessonList = lessonDao.queryByMonthForLess(beforedate,nowdate);
		
		Iterator<Instructor> all = allInstructor.iterator();
		while(all.hasNext()){
			Instructor instructor = all.next();
			double i = instructor.getBasicsalary();
			 for(Lesson lesson: lessonList) {
				 if(lesson.getInstructor() .equals(instructor)) {
					 i = i+ instructor.getProfessionranks().getGroup_classfee();
				 }
			 }
			 Wage wage = new Wage(instructor,i,nowdate);
			 wageDao.save(wage);
			 System.out.println(instructor.getName()+"工资已结算");
		}

	}
	
	public  Date getBeforeMonth() {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, -1);
		return calendar.getTime();
	}
	
	
	
}
