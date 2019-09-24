package com.fy.instructor.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fy.instructor.dao.CourseDao;
import com.fy.instructor.entity.Course;
import com.mysql.jdbc.StringUtils;

@Repository
public class CourseDaoImpl implements CourseDao {
	
	
	@Autowired
	private SessionFactory sessionFactory;
	

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	
	public List<Course> queryAllCourse(){
		String sql = "from Course";
		Query query = this.getSession().createQuery(sql);
		List<Course> courseList = query.list();
		return courseList;
	};
	

	//根据id查询
	public Course queryById(int id) {
		Session session = this.getSession();
		Course course = session.get(Course.class, id);
		return course;
	}
	
	//根据id删除
	public void deleteById(int id) {
		Session session = this.getSession();
		Course course = this.queryById(id);
		session.delete(course);
	}
	
	//批量删除
	public void deleteByList(List<Integer> idList) {
		Session session = this.getSession();
		for(Integer id : idList) {
			this.deleteById(id);					
		}
	}
	
	//修改
	public void update(Course course) {
		Session session = this.getSession();
		session.update(course);	
	}
	
	//新增
	public void save(Course course) {
		Session session = this.getSession();
		session.save(course);
	}

	/**
	 * 分页查询
	 * @param limit
	 * @param offset
	 * @param searchType
	 * @param searchText
	 * @return
	 */
	public Map<String, Object> queryPageInfo(int limit, int offset,String searchType,String searchText) {
		Session session = this.getSession();
		Map<String, Object> map = new HashMap<String, Object>();
		if(!StringUtils.isNullOrEmpty(searchText)) {
			if("number".equals(searchType)) {
				String sql = "from Course where number = ?0";
				int id = Integer.parseInt(searchText);
				List<Course> cutomerList = session.createQuery(sql).setParameter(0, id).getResultList();
				
				String sql2 = "select count(number) from Course where number = ?0";
				int totalRecord = Integer.parseInt(session.createQuery(sql2).setParameter(0,id).uniqueResult().toString());
				
				map.put("total", totalRecord);
				map.put("data", cutomerList);
			}else if("name".equals(searchType)) {
				String sql = "from Course where name like ?0";
				Query query = session.createQuery(sql);
				query.setParameter(0,"%"+searchText+"%");
				query.setMaxResults(limit);
				query.setFirstResult(offset);
				List<Course> courseList = query.getResultList();
				
				String sql2 = "select count(*) from Course where name like ?0";
				int totalRecord = Integer.parseInt(session.createQuery(sql2).setParameter(0,"%"+searchText+"%").uniqueResult().toString());
				
				map.put("total", totalRecord);
				map.put("data", courseList);				
				}
		}else {
			String sql = "from Course";
			Query query = session.createQuery(sql);
			String sql2 = "select * From";
			query.setFirstResult(offset);
			query.setMaxResults(limit);
			List<Course> courseList = query.list();
			
     
			String sql21  = "select count(*) from Course";
			int totalRecord = Integer.parseInt(session.createQuery(sql21).uniqueResult().toString());
			map.put("total", totalRecord);
			map.put("data", courseList);			
		}
		return map;
	}


	public List<Course> getAll() {
		String sql = "from Course";
		return this.getSession().createQuery(sql).list();
	}


	

	
}
