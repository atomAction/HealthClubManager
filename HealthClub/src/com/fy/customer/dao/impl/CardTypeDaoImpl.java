package com.fy.customer.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fy.customer.dao.CardTypeDao;
import com.fy.customer.entity.CardType;

@Repository
public class CardTypeDaoImpl implements CardTypeDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	/**
	 * 分页查询
	 * @param limit
	 * @param offset
	 * @return
	 */
	public Map<String, Object> queryPageInfo(int limit, int offset) {
		String sql = "from CardType";
		Session session = this.getSession();

		Query query = session.createQuery(sql);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		List<CardType> cardTypeList = query.list();

		String sql2 = "select count(*) from CardType";
		int totalRecord = Integer.parseInt(session.createQuery(sql2).uniqueResult().toString());

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", totalRecord);
		map.put("data", cardTypeList);
		return map;
	}

	
	//根据id查询
	public CardType queryById(int id) {
		Session session = this.getSession();
		CardType cardType = session.get(CardType.class, id);
		return cardType;
	}
	
	//根据id删除
	public void deleteById(int id) {
		Session session = this.getSession();
		CardType cardType = this.queryById(id);
		session.delete(cardType);
	}
	
	//批量删除
	public void deleteByList(List<Integer> idList) {
		Session session = this.getSession();
		for(Integer id : idList) {
			this.deleteById(id);					
		}
	}
	
	//修改
	public void update(CardType cardType) {
		Session session = this.getSession();
		session.update(cardType);	
	}
	
	//新增
	public void save(CardType cardType) {
		Session session = this.getSession();
		session.save(cardType);
	}

	
}
