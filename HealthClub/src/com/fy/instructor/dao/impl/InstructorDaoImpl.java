package com.fy.instructor.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fy.customer.entity.Customer;
import com.fy.instructor.dao.InstructorDao;
import com.fy.instructor.entity.Instructor;
import com.mysql.jdbc.StringUtils;

@Repository
public class InstructorDaoImpl implements InstructorDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
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
			if("id".equals(searchType)) {
				String sql = "from Instructor where jobnumber = ?0";
				int id = Integer.parseInt(searchText);
				List<Instructor> instructorList = session.createQuery(sql).setParameter(0,id).getResultList();
				
				String sql2 = "select count(jobnumber) from Instructor where jobnumber = ?0";
				int totalRecord = Integer.parseInt(session.createQuery(sql2).setParameter(0,id).uniqueResult().toString());
				
				map.put("total", totalRecord);
				map.put("data", instructorList);
			}else if("name".equals(searchType)) {
				String sql = "from Instructor where name like ?0";
				Query query = session.createQuery(sql);
				query.setParameter(0,"%"+searchText+"%");
				query.setMaxResults(limit);
				query.setFirstResult(offset);
				List<Instructor> instructorList = query.getResultList();
				
				String sql2 = "select count(*) from Instructor where name like ?0";
				int totalRecord = Integer.parseInt(session.createQuery(sql2).setParameter(0,"%"+searchText+"%").uniqueResult().toString());
				
				map.put("total", totalRecord);
				map.put("data", instructorList);				
				
			}
//			else if("phone".equals(searchType)) {
//				String sql = "from Instructor where phone = ?";
//				Query query = session.createQuery(sql);
//				query.setInteger(0, Integer.parseInt(searchText));
//				query.setMaxResults(limit);
//				query.setFirstResult(offset);
//				List<Instructor> instructorList = query.getResultList();
//				String sql2 = "select count(*) from Instructor where phone = ?";
//				int totalRecord = Integer.parseInt(session.createQuery(sql2).setInteger(0,Integer.parseInt(searchText)).uniqueResult().toString());
//				
//				map.put("total", totalRecord);
//				map.put("data", instructorList);
//			}
//			else if("address".equals(searchType)) {
//				String sql = "from User u where u.address like ?";
//				Query query = session.createQuery(sql);
//				query.setString(0,"%"+searchText+"%");
//				query.setMaxResults(limit);
//				query.setFirstResult(offset);
//				List<User> userList = query.getResultList();
//				
//				String sql2 = "select count(*) from User u where u.address like ?";
//				int totalRecord = Integer.parseInt(session.createQuery(sql2).setString(0,"%"+searchText+"%").uniqueResult().toString());
//				
//				map.put("total", totalRecord);
//				map.put("data", userList);
//			}
		}else {
			String sql = "from Instructor";
			Query query = session.createQuery(sql);
			query.setFirstResult(offset);
			query.setMaxResults(limit);
			List<Instructor> userList = query.list();
			
			String sql2 = "select count(*) from Instructor";
			int totalRecord = Integer.parseInt(session.createQuery(sql2).uniqueResult().toString());
			
			map.put("total", totalRecord);
			map.put("data", userList);			
		}
		return map;
	}
	
	//根据id查询
	public Instructor queryById(int id) {
		Session session = this.getSession();
		Instructor instructor = session.get(Instructor.class, id);
		return instructor;
	}
	
	//根据id删除
	public void deleteById(int id) {
		Session session = this.getSession();
		Instructor instructor = this.queryById(id);
		session.delete(instructor);
	}
	
	//批量删除
	public void deleteByList(List<Integer> idList) {
		Session session = this.getSession();
		for(Integer id : idList) {
			this.deleteById(id);					
		}
	}
	
	//修改
	public void update(Instructor instructor) {
		Session session = this.getSession();
		session.update(instructor);	
	}
	
	//新增
	public void save(Instructor instructor) {
		Session session = this.getSession();
		session.save(instructor);
	}

	//根据条件查询

	@SuppressWarnings("unchecked")
	public List<Instructor> queryBycourses(String canteach) {
		String sql2 = "from Instructor i where i.courses.name like ?0";	
		return  this.getSession().createQuery(sql2).setParameter(0, canteach).list();
	}

	public List<Instructor> getAll() {
		String sql = "from Instructor";
		return this.getSession().createQuery(sql).list();
	}

}
