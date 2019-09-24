package com.fy.customer.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fy.customer.dao.CustomerDao;
import com.fy.customer.entity.Customer;
import com.mysql.jdbc.StringUtils;


@Repository
public class CustomerDaoImpl implements CustomerDao {

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
				String sql = "from Customer where c_id = ?0";
				int id = Integer.parseInt(searchText);
				List<Customer> cutomerList = session.createQuery(sql).setParameter(0, id).getResultList();
				
				String sql2 = "select count(c_id) from Customer where c_id = ?0";
				int totalRecord = Integer.parseInt(session.createQuery(sql2).setParameter(0,id).uniqueResult().toString());
				
				map.put("total", totalRecord);
				map.put("data", cutomerList);
			}else if("name".equals(searchType)) {
				String sql = "from Customer where name like ?0";
				Query query = session.createQuery(sql);
				query.setParameter(0,"%"+searchText+"%");
				query.setMaxResults(limit);
				query.setFirstResult(offset);
				List<Customer> customerList = query.getResultList();
				
				String sql2 = "select count(*) from Customer where name like ?0";
				int totalRecord = Integer.parseInt(session.createQuery(sql2).setParameter(0,"%"+searchText+"%").uniqueResult().toString());
				
				map.put("total", totalRecord);
				map.put("data", customerList);				
				
			}else if("phone".equals(searchType)) {
				String sql = "from Customer where phone like ?0";
				Query query = session.createQuery(sql);
				query.setParameter(0, "%"+searchText+"%");
				query.setMaxResults(limit);
				query.setFirstResult(offset);
				List<Customer> customerList = query.getResultList();
				String sql2 = "select count(*) from Customer where phone like ?0";
				int totalRecord = Integer.parseInt(session.createQuery(sql2).setParameter(0,"%"+searchText+"%").uniqueResult().toString());
				
				map.put("total", totalRecord);
				map.put("data", customerList);
			}
		}else {
			String sql = "from Customer";
			Query query = session.createQuery(sql);
			query.setFirstResult(offset);
			query.setMaxResults(limit);
			List<Customer> userList = query.list();
			
			String sql2 = "select count(*) from Customer";
			int totalRecord = Integer.parseInt(session.createQuery(sql2).uniqueResult().toString());
			
			map.put("total", totalRecord);
			map.put("data", userList);			
		}
		return map;
	}
	
	//根据id查询
	public Customer queryById(int id) {
		Session session = this.getSession();
		Customer customer = session.get(Customer.class, id);
		return customer;
	}
	
	//根据id删除
	public void deleteById(int id) {
		Session session = this.getSession();
		Customer customer = this.queryById(id);
		session.delete(customer);
	}
	
	//批量删除
	public void deleteByList(List<Integer> idList) {
		Session session = this.getSession();
		for(Integer id : idList) {
			this.deleteById(id);					
		}
	}
	
	//修改
	public void update(Customer customer) {
		Session session = this.getSession();
		session.update(customer);	
	}
	
	//新增
	public void save(Customer customer) {
		Session session = this.getSession();
		session.save(customer);
	}




	public Customer queryByName(String searchText) {
		String sql = "from Customer c where c.name = ?0";
		
		return (Customer) this.getSession().createQuery(sql).setParameter(0, searchText).uniqueResult();
	}
	

	
	
	
}

	

