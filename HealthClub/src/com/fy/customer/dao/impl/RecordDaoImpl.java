package com.fy.customer.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fy.common.entity.MaterialEnums.RecordTypes;
import com.fy.customer.dao.CustomerDao;
import com.fy.customer.dao.RecordDao;
import com.fy.customer.entity.Record;
import com.fy.lesson.entity.Lesson;
import com.mysql.jdbc.StringUtils;

@Repository
public class RecordDaoImpl implements RecordDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private CustomerDao  customerDao;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public Map<String, Object> getRecords(int limit, int offset, String searchType, String searchText,Date starttime,Date endtime) {
		Session session = this.getSession();
		Map<String, Object> map = new HashMap<String, Object>();
		if(!StringUtils.isNullOrEmpty(searchText)) {
			if("id".equals(searchType)) {
				String sql = " select r from Record r where r.customer.c_id =?0 and r.date between ?1 and ?2 ";
				int id = Integer.parseInt(searchText);
				List<Record> recordList = session.createQuery(sql).setParameter(0, id).setParameter(1, starttime).setParameter(2, endtime).getResultList();
				
				String sql2 = "select count(*) from Record r where r.customer.c_id =?0 and r.date between ?1 and ?2";
				int totalRecord = Integer.parseInt(session.createQuery(sql2).setParameter(0, id).setParameter(1, starttime).setParameter(2, endtime).uniqueResult().toString());
				
				map.put("total", totalRecord);
				map.put("data", recordList);
			}else if("name".equals(searchType)) {
				
				int id = customerDao.queryByName(searchText).getC_id();
				String sql = "select r from Record r where r.customer.c_id =?0 and r.date between ?1 and ?2 ";
				
				Query query = session.createQuery(sql);
				query.setParameter(0, id).setParameter(1, starttime).setParameter(2, endtime);
				query.setMaxResults(limit);
				query.setFirstResult(offset);
				List<Lesson> lessonList = query.getResultList();
				
				String sql2 = "select count(*) from Record r where r.customer.c_id =?0 and r.date between ?1 and ?2";
				int totalRecord = Integer.parseInt(session.createQuery(sql2).setParameter(0, id).setParameter(1, starttime).setParameter(2, endtime).uniqueResult().toString());
				
				map.put("total", totalRecord);
				map.put("data", lessonList);				

			}
		}else {
			String sql = "from Record";
			Query query = session.createQuery(sql);
			query.setFirstResult(offset);
			query.setMaxResults(limit);
			List<Record> recordList = query.list();
			
			String sql2 = "select count(*) from Record";
			int totalRecord = Integer.parseInt(session.createQuery(sql2).uniqueResult().toString());
			
			map.put("total", totalRecord);
			map.put("data", recordList);			
		}
		return map;
	}

	
	//新增
		public void save(Record record) {
			Session session = this.getSession();
			session.save(record);
		}

		public Map<String,Object> getChartBytime(Date starttime, Date endtime, int texttype, int search) {
			Session session = this.getSession();
			Map<String,Object> map = new HashMap<String,Object>();
			Calendar cl = Calendar.getInstance();
			cl.setTime(starttime);
			if(texttype == -1) {
				List<Double> amountList = new ArrayList<Double>();
				List<Double> amountList1 = new ArrayList<Double>();
				String sql = "select amount from Record where recordType = ?0 and date between ?1 and ?2";
				while(cl.getTime().before(endtime)){//判断是否到结束日期
					double n = 0;
					Date date = cl.getTime();
					cl.add(Calendar.MONTH, 1);
					Date date1 = cl.getTime();
					amountList1 = session.createQuery(sql).setParameter(0, RecordTypes.充值).setParameter(1, date).setParameter(2, date1).list();
					for(Double d:amountList1) {
						n = n + d;
						System.out.println(n);
					}	
					amountList.add(n);
					}
						double n = 0;
						Date date = cl.getTime();
						cl.add(Calendar.MONTH, 1);
						Date date1 = cl.getTime();
						amountList1 = session.createQuery(sql).setParameter(0, RecordTypes.充值).setParameter(1, date).setParameter(2, date1).list();
						for(Double d:amountList1) {
							n = n + d;
						}	
						amountList.add(n);
						map.put("amountList", amountList);
				return map;
			}else if(texttype == 1) {
				List<Double> amountList = new ArrayList<Double>();
				List<Lesson> amountList1 = new ArrayList<Lesson>();
				String sql = " from Lesson l  where l.instructor.jobnumber = ?0 and l.starttime between ?1 and ?2";
				while(cl.getTime().before(endtime)){//判断是否到结束日期
					double n = 0;
					Date date = cl.getTime();
					cl.add(Calendar.MONTH, 1);
					Date date1 = cl.getTime();
					amountList1 = session.createQuery(sql).setParameter(0, search).setParameter(1, date).setParameter(2, date1).list();
					for(Lesson l:amountList1) {
						n = n + l.getStudents().size();
						System.out.println(n);
					}	
					amountList.add(n);
					}
						double n = 0;
						Date date = cl.getTime();
						cl.add(Calendar.MONTH, 1);
						Date date1 = cl.getTime();
						amountList1 = session.createQuery(sql).setParameter(0, search).setParameter(1, date).setParameter(2, date1).list();
						for(Lesson l:amountList1) {
							n = n + l.getStudents().size();
							System.out.println(n);
						}	
						amountList.add(n);
						map.put("amountList", amountList);
				return map;
				
			}else if(texttype == 2) {
				List<Double> amountList = new ArrayList<Double>();
				List<Integer> amountList1 = new ArrayList<Integer>();
				List<Lesson> amountList2 = new ArrayList<Lesson>();
				String sql = "select count(*) from Lesson where courseType.number = ?0 and starttime between ?1 and ?2";
				String sql2 = " from Lesson l  where l.courseType.number = ?0 and l.starttime between ?1 and ?2";
				while(cl.getTime().before(endtime)){//判断是否到结束日期
					double n = 0;
					Date date = cl.getTime();
					cl.add(Calendar.MONTH, 1);
					Date date1 = cl.getTime();
					int h =Integer.parseInt(session.createQuery(sql).setParameter(0, search).setParameter(1, date).setParameter(2, date1).uniqueResult().toString()) ;
					amountList2 =  session.createQuery(sql2).setParameter(0, search).setParameter(1, date).setParameter(2, date1).list();
					
					for(Lesson l:amountList2) {
						n = n + l.getStudents().size();
						System.out.println(n);
					}	
					amountList1.add(h);
					amountList.add(n);
					
					}
						double n = 0;
						Date date = cl.getTime();
						cl.add(Calendar.MONTH, 1);
						Date date1 = cl.getTime();
						int h =Integer.parseInt(session.createQuery(sql).setParameter(0, search).setParameter(1, date).setParameter(2, date1).uniqueResult().toString()) ;
						amountList2 =session.createQuery(sql2).setParameter(0, search).setParameter(1, date).setParameter(2, date1).list();

						for(Lesson l:amountList2) {
							n = n + l.getStudents().size();
							System.out.println(n);
						}	
						amountList.add(n);
						amountList1.add(h);
						
						
						map.put("amountList", amountList);
						map.put("amountList1", amountList1);
						return map;
				
			}
			
			
			return map;
			
		}
	
	
}
