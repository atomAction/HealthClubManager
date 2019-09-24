package com.fy.common.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.fy.common.dao.BaseDao;

@Repository
public class BaseDaoImpl implements BaseDao {
	@Autowired
	@Qualifier(value="sessionFactory")
	private SessionFactory sessionFactory;

	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public void save(Object o) {//保存数据
		
//		Session session=getSession();
//		session.save(o);
		this.getSession().save(o);
	}

	public Object getObj(Class objClass, Serializable id) {//根据主键找数据
	
//		Session session=getSession();
//		Object obj=session.get(objClass, id);
//		return obj;
		
		return this.getSession().get(objClass, id);
	}

	public List findObjsByHql(String hql, List params,int firResult,int maxResults) {//参数hql和params有什么作用？
		Session session=getSession();
		Query query=session.createQuery(hql);//query对象的作用是查询数据库
		//
		for(int i=0;i<params.size();i++){
			Object currParam=params.get(i);
			if(currParam instanceof Date){
				query.setTimestamp(i, (Date)currParam);
			}else{
				query.setParameter(i, currParam);
			}
		}
		if(firResult>=0&&maxResults>0){
			query.setFirstResult(firResult);
			query.setMaxResults(maxResults);
		}
		
		
		List objList=query.list();//query对象的list方法的作用是执行查询
	
		
		return objList;
	}

	public Object getUniqueObj(String hql, List params) {
		
		Session session=getSession();
		Query query=session.createQuery(hql);//query对象的作用是查询数据库
		//
		for(int i=0;i<params.size();i++){
			Object currParam=params.get(i);
			if(currParam instanceof Date){
				query.setTimestamp(i, (Date)currParam);
			}else{
				query.setParameter(i, currParam);
			}
		}
		Object resultObj=query.uniqueResult();
		
		return resultObj;
	}

	public void delete(Object o) {
	
//		Session session=getSession();
//	
//		session.delete(o);
		
		this.getSession().delete(o);
	
	}

	public void update(Object o) {
		
//		Session session=getSession();
//
//		session.update(o);
		
		this.getSession().update(o);
	}
	
	

}
