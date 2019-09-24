package com.fy.instructor.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fy.instructor.dao.ProfessionRankDao;
import com.fy.instructor.entity.Course;
import com.fy.instructor.entity.ProfessionRank;
import com.mysql.jdbc.StringUtils;

@Repository
public class ProfessionRankDaoImpl implements ProfessionRankDao {

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
				String sql = "from ProfessionRank where jobnumber = ?0";
				int id = Integer.parseInt(searchText);
				List<ProfessionRank> professionRankList = session.createQuery(sql).setParameter(0,id).getResultList();
				
				String sql2 = "select count(jobnumber) from ProfessionRank where jobnumber = ?0";
				int totalRecord = Integer.parseInt(session.createQuery(sql2).setParameter(0,id).uniqueResult().toString());
				
				map.put("total", totalRecord);
				map.put("data", professionRankList);
			}else if("name".equals(searchType)) {
				String sql = "from ProfessionRank where name like ?0";
				Query query = session.createQuery(sql);
				query.setParameter(0,"%"+searchText+"%");
				query.setMaxResults(limit);
				query.setFirstResult(offset);
				List<ProfessionRank> professionRankList = query.getResultList();
				
				String sql2 = "select count(*) from Cutomer where name like ?0";
				int totalRecord = Integer.parseInt(session.createQuery(sql2).setParameter(0,"%"+searchText+"%").uniqueResult().toString());
				
				map.put("total", totalRecord);
				map.put("data", professionRankList);				
				
			}
		}else {
			String sql = "from ProfessionRank";
			Query query = session.createQuery(sql);
			query.setFirstResult(offset);
			query.setMaxResults(limit);
			List<ProfessionRank> userList = query.list();
			
			String sql2 = "select count(*) from ProfessionRank";
			int totalRecord = Integer.parseInt(session.createQuery(sql2).uniqueResult().toString());
			
			map.put("total", totalRecord);
			map.put("data", userList);			
		}
		return map;
	}
	
	//根据id查询
	public ProfessionRank queryById(int id) {
		Session session = this.getSession();
		ProfessionRank professionRank = session.get(ProfessionRank.class, id);
		return professionRank;
	}
	
	//根据id删除
	public void deleteById(int id) {
		Session session = this.getSession();
		ProfessionRank professionRank = this.queryById(id);
		session.delete(professionRank);
	}
	
	//批量删除
	public void deleteByList(List<Integer> idList) {
		Session session = this.getSession();
		for(Integer id : idList) {
			this.deleteById(id);					
		}
	}
	
	//修改
	public void update(ProfessionRank professionRank) {
		Session session = this.getSession();
		session.update(professionRank);	
	}
	
	//新增
	public void save(ProfessionRank professionRank) {
		Session session = this.getSession();
		session.save(professionRank);
	}

	public List<ProfessionRank> queryAllProfessionRank() {
		String sql = "from ProfessionRank";
		Query query = this.getSession().createQuery(sql);
		List<ProfessionRank> PRList = query.list();
		return PRList;
	}



}
