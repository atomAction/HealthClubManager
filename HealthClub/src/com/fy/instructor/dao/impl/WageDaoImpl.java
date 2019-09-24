package com.fy.instructor.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fy.instructor.dao.WageDao;
import com.fy.instructor.entity.Instructor;
import com.fy.instructor.entity.Wage;
import com.fy.instructor.service.WageService;
import com.mysql.jdbc.StringUtils;

@Repository
public class WageDaoImpl implements WageDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public Map<String, Object> getWageInFo(int limit,int offset,String searchType,String searchText) throws ParseException {
		Session session = this.getSession();
		Map<String, Object> map = new HashMap<String, Object>();
		if(!StringUtils.isNullOrEmpty(searchText)) {
			if("id".equals(searchType)) {
				String sql = "from Wage where instructor.jobnumber = ?0";
				int id = Integer.parseInt(searchText);
				List<Wage> wageList = session.createQuery(sql).setParameter(0,id).getResultList();
				
				String sql2 = "select count(*) from Wage where instructor.jobnumber = ?0";
				int totalRecord = Integer.parseInt(session.createQuery(sql2).setParameter(0,id).uniqueResult().toString());
				
				map.put("total", totalRecord);
				map.put("data", wageList);
			}else if("month".equals(searchType)) {
				String sql = "from Wage where month between ?0 and ?1";
				int a = Integer.parseInt(searchText);
				Calendar calendar = new GregorianCalendar();
				Calendar calendar2 = new GregorianCalendar();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
				calendar.set(2019, a-1, 1,0,0,0);
				Date date1 =	calendar.getTime();
				calendar2.set(2019, a, 1,0,0,0);
				Date date2 = calendar2.getTime();
				Query query = session.createQuery(sql);
				query.setParameter(0, date1).setParameter(1, date2);
				query.setMaxResults(limit);
				query.setFirstResult(offset);
				List<Wage> wageList = query.getResultList();
				
				String sql2 = "select count(*) from Wage where month between ?0 and ?1";
				int totalRecord = Integer.parseInt(session.createQuery(sql2)
						.setParameter(0, date1).setParameter(1, date2).uniqueResult().toString());
				
				map.put("total", totalRecord);
				map.put("data", wageList);				
				
			}
		}else {
			String sql = "from Wage";
			Query query = session.createQuery(sql);
			query.setFirstResult(offset);
			query.setMaxResults(limit);
			List<Wage> wageList = query.list();
			
			String sql2 = "select count(*) from Wage";
			int totalRecord = Integer.parseInt(session.createQuery(sql2).uniqueResult().toString());
			
			map.put("total", totalRecord);
			map.put("data", wageList);			
		}
		return map;
	}

	//根据id查询
	public Wage queryById(int id) {
		Session session = this.getSession();
		Wage wage = session.get(Wage.class, id);
		return wage;
	}
	
	//根据id删除
	public void deleteById(int id) {
		Session session = this.getSession();
		Wage wage = this.queryById(id);
		session.delete(wage);
	}
	
	
	//修改
	public void update(Wage wage) {
		Session session = this.getSession();
		session.update(wage);	
	}
	
	//新增
	public void save(Wage wage) {
		Session session = this.getSession();
		session.save(wage);
	}

}
