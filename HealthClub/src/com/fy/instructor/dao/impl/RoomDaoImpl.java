package com.fy.instructor.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fy.instructor.dao.RoomDao;
import com.fy.instructor.entity.ProfessionRank;
import com.fy.instructor.entity.Room;
import com.fy.lesson.dao.impl.LessonDaoImpl;
import com.fy.lesson.entity.Lesson;
import com.mysql.jdbc.StringUtils;

@Repository
public class RoomDaoImpl implements RoomDao {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private LessonDaoImpl lessonDao;
	
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
	public Map<String, Object> queryPageInfo(int limit, int offset) {
		Session session = this.getSession();
		Map<String, Object> map = new HashMap<String, Object>();
		
			String sql = "from Room";
			Query query = session.createQuery(sql);
			query.setFirstResult(offset);
			query.setMaxResults(limit);
			List<Room> userList = query.list();
			
			String sql2 = "select count(*) from Room";
			int totalRecord = Integer.parseInt(session.createQuery(sql2).uniqueResult().toString());
			
			map.put("total", totalRecord);
			map.put("data", userList);			
		
		return map;
	}
	
	//根据id查询
	public Room queryById(int id) {
		Session session = this.getSession();
		Room room = session.get(Room.class, id);
		return room;
	}
	
	//根据id删除
	public void deleteById(int id) {
		Session session = this.getSession();
		Room room = this.queryById(id);
		session.delete(room);
	}
	
	//批量删除
	public void deleteByList(List<Integer> idList) {
		Session session = this.getSession();
		for(Integer id : idList) {
			this.deleteById(id);					
		}
	}
	
	//修改
	public void update(Room room) {
		Session session = this.getSession();
		session.update(room);	
	}
	
	//新增
	public void save(Room room) {
		Session session = this.getSession();
		session.save(room);
	}

	public List<Room> queryBycourseId(int canteach) {
		String sql = "from Room r where r.roomtype.number = ?0 ";
		return  this.getSession().createQuery(sql).setParameter(0, canteach).list();
	}

	public int queryByLessonForcapacity(Lesson lesson) {
		int capacity = lesson.getRoom().getCapacity() ;
		return capacity;
	}

}
