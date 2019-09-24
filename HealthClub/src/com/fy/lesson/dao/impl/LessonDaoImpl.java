package com.fy.lesson.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fy.customer.dao.CustomerDao;
import com.fy.customer.entity.Customer;
import com.fy.instructor.dao.RoomDao;
import com.fy.lesson.dao.LessonDao;
import com.fy.lesson.entity.Lesson;
import com.mysql.jdbc.StringUtils;

/**
 * 
 * @author Administrator
 *
 */

@Repository
public class LessonDaoImpl implements LessonDao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private RoomDao roomDao;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	
	public Map<String, Object> queryPageInfo(int limit, int offset,String searchType,String searchText) {
		Session session = this.getSession();
		Map<String, Object> map = new HashMap<String, Object>();
			String sql = "from Lesson";
			Query query = session.createQuery(sql);
			query.setFirstResult(offset);
			query.setMaxResults(limit);
			List<Lesson> lessonList = query.list();
			
			String sql2 = "select count(*) from Lesson";
			int totalRecord = Integer.parseInt(session.createQuery(sql2).uniqueResult().toString());
			
			map.put("total", totalRecord);
			map.put("data", lessonList);			
		return map;
	}
	
	
	
	
	
	/**
	 * 查
	 */

	public Lesson getLessonById(int id) {
		Session session = this.getSession();
		Lesson lesson = session.get(Lesson.class, id);
		return lesson;
	}

	/**
	 * 增
	 */

	public void addLesson(Lesson lesson) {

		this.getSession().save(lesson);
	}

	/**
	 * 改
	 */
	public void updateLesson(Lesson lesson) {

		this.getSession().update(lesson);
	}

	/**
	 * 删
	 */
	public void deleteLessonById(int id) {

		this.getSession().createQuery("delete Lesson l form Lesson where number= :id").setParameter("id", id)
				.executeUpdate();
	}

	/**
	 * 查所有
	 */
	@SuppressWarnings("unchecked")
	public List<Lesson> getLessons() {

		return this.getSession().createQuery("form lessons").list();
	}


	/**
	 * 排课
	 * 获得所有课程在开始时间里的课的教练的集合
	 */
	public List<Integer> queryByStartTimeforIn(Date starttime) {
		List<Integer> lessonListInId = new ArrayList<Integer>();
		String sql = "select l.instructor.jobnumber from Lesson l where l.starttime <= ?0 and l.endtime > ?1" ;
		lessonListInId = this.getSession().createQuery(sql).setParameter(0, starttime).setParameter(1, starttime).list();
		return lessonListInId;
	}


	/**
	 * 排课
	 * 获得所有课程在开始时间里的房间的集合
	 */
	public List<Integer> queryByStartTimeforRoom(Date starttime) {
		List<Integer> lessonListRoomId = new ArrayList<Integer>();
		String sql = "select l.room.number from Lesson l where l.starttime <= ?0 and l.endtime > ?1" ;
		lessonListRoomId = this.getSession().createQuery(sql).setParameter(0, starttime).setParameter(1, starttime).list();
		return lessonListRoomId;
	}


	/**
	 * 根据课程ID获得课程的所有学生
	 */
	public Map<String, Object> getStudentInfo(int id) {
		Map<String,Object> map= new HashMap<String,Object>();
	//	Set<Customer> studentSet =  new HashSet<Customer>();
		List<Customer> studentSet = new ArrayList<Customer>();
		String sql = "select l.students from Lesson l where l.id = ?0";
		studentSet =  this.getSession().createQuery(sql).setParameter(0, id).list();
		map.put("data", studentSet);
		return map;
	}

	
	
	/**
	 * 课程预定
	 * 列表信息
	 */

	public Map<String, Object> queryLessonsForCoustomer(int limit, int offset, String searchType, String searchText) {
		Session session = this.getSession();
		Map<String, Object> map = new HashMap<String, Object>();
		if(!StringUtils.isNullOrEmpty(searchText)) {
			if("id".equals(searchType)) {
				String sql = " select l from Lesson l  where l. starttime < ?0 ";
				int id = Integer.parseInt(searchText);
				Date effective_deadline = customerDao.queryById(id).getEffectivedeadline();
				@SuppressWarnings("unchecked")
				List<Lesson> lessonList = session.createQuery(sql).setParameter(0, effective_deadline).getResultList();
				
				//对已经报名的课程迭代出去
//				while(le.hasNext()){
//					Lesson lesson = le.next();
//					lesson.getStudents();
//					for(Customer cus :lesson.getStudents()) {
//						 if(cus.getC_id() == id){
//							 le.remove();
//						    }	
//					}   
//				}	
				//把已经报名的和容量满了的迭代出去
				Iterator<Lesson> le = lessonList.iterator();
				while(le.hasNext()){
					Lesson lesson = le.next();
					if(lesson.getStudents().contains(customerDao.queryById(id)) == true || 
							lesson.getStudents().size() >= roomDao.queryByLessonForcapacity(lesson) ) {
						le.remove();
					}
				}
				
				String sql2 = "select count(*) from Lesson l where l.starttime < ?0";
				int totalRecord = Integer.parseInt(session.createQuery(sql2).setParameter(0,effective_deadline).uniqueResult().toString());
				
				map.put("total", totalRecord);
				map.put("data", lessonList);
			}else if("name".equals(searchType)) {
				System.out.println(searchText);
				Date effective_deadline = customerDao.queryByName(searchText).getEffectivedeadline();
				int id = customerDao.queryByName(searchText).getC_id();
				String sql = "from Lesson l  where l. starttime < ?0 ";
				
				Query query = session.createQuery(sql);
				query.setParameter(0,effective_deadline);
				query.setMaxResults(limit);
				query.setFirstResult(offset);
				List<Lesson> lessonList = query.getResultList();
				
				//把已经报名的和容量满了的迭代出去
				Iterator<Lesson> le = lessonList.iterator();
				while(le.hasNext()){
					Lesson lesson = le.next();
					if(lesson.getStudents().contains(customerDao.queryById(id)) == true || 
							lesson.getStudents().size() >= roomDao.queryByLessonForcapacity(lesson) ) {
						le.remove();
					}
				}
				
				String sql2 = "select count(*) from Lesson l  where l. starttime < ?0";
				int totalRecord = Integer.parseInt(session.createQuery(sql2).setParameter(0,effective_deadline).uniqueResult().toString());
				
				map.put("total", totalRecord);
				map.put("data", lessonList);				

			}
		}else {
			String sql = "from Lesson";
			Query query = session.createQuery(sql);
			query.setFirstResult(offset);
			query.setMaxResults(limit);
			List<Lesson> lessonList = query.list();
			
			String sql2 = "select count(*) from Lesson";
			int totalRecord = Integer.parseInt(session.createQuery(sql2).uniqueResult().toString());
			
			map.put("total", totalRecord);
			map.put("data", lessonList);			
		}
		return map;
	}


	public List<Lesson> queryByMonthForLess(Date beforedate, Date nowdate) {
		String sql = "from Lesson where starttime between ?0 and ?1";
		List<Lesson> lessonList = this.getSession().createQuery(sql).setParameter(0, beforedate).setParameter(1, nowdate).list();
		return lessonList;
	}


}
