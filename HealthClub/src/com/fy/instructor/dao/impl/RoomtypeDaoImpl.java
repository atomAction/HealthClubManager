package com.fy.instructor.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fy.instructor.dao.RoomtypeDao;
import com.fy.instructor.entity.Room;
import com.fy.instructor.entity.Roomtype;

@Repository
public class RoomtypeDaoImpl implements RoomtypeDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	
	
	
	public Map<String, Object> queryPageInfo(int limit, int offset) {
		Session session = this.getSession();
		Map<String, Object> map = new HashMap<String, Object>();
		
			String sql = "from Roomtype";
			Query query = session.createQuery(sql);
			query.setFirstResult(offset);
			query.setMaxResults(limit);
			List<Roomtype> userList = query.list();
			
			String sql2 = "select count(*) from Roomtype";
			int totalRecord = Integer.parseInt(session.createQuery(sql2).uniqueResult().toString());
			
			map.put("total", totalRecord);
			map.put("data", userList);			
		
		return map;
	}
	
	//根据id删除
	public void deleteById(int id) {
		Session session = this.getSession();
		Roomtype roomtype = this.queryById(id);
		session.delete(roomtype);
	}

	//根据id查询
		public Roomtype queryById(int id) {
			Session session = this.getSession();
			Roomtype roomtype = session.get(Roomtype.class, id);
			return roomtype;
		}
		
	

	

	/**
	 * 改
	 */
	public void updateRoomtype(Roomtype roomtype) {
		
		this.getSession().update(roomtype);
	}

	
	/**
	 * 删
	 */
	public void deleteRoomtypeById(int id) {
		
		this.getSession().createQuery("delete roomtype form roomtypes where number= :id").setParameter("id", id).executeUpdate();
	}

	/**
	 * 查所有
	 */
	@SuppressWarnings("unchecked")
	public List<Roomtype> getRoomtypes() {
		
		return this.getSession().createQuery("form roomtypes").list();
	}

	public List<Roomtype> getAllRoomtype() {
		List<Roomtype> roomtypeList = new ArrayList<Roomtype>();
		String sql = "from Roomtype";
		roomtypeList = this.getSession().createQuery(sql).list();
		return roomtypeList;
	}




	public void save(Roomtype roomtype) {
		this.getSession().save(roomtype);
		
	}




	

	
}
