package com.fy.common.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao {

	/**
	 * 保存实体对象
	 * @param o 要保存的实体对象
	 */
	public void save(Object o);
	
	/**
	 * 根据主键查询实体对象
	 * @param objClass 要查询的实体类
	 * @param id 主键的值
	 * @return
	 */
	public Object getObj(Class objClass,Serializable id);
	
	/**
	 * 执行HQL查询
	 * @param hql hql语句
	 * @param params 填入hql语句中各占位符的参数
	 * @param firResult 其实查询位置
	 * @param maxResults 本次最大查询记录数
	 * @return
	 */
	public List findObjsByHql(String hql,List params,int firResult,int maxResults);
	
	/**
	 * 执行唯一结果的HQL查询，例如统计（count）查询
	 * @param hql hql语句
	 * @param params 填入hql语句中各占位符的参数
	 * @return
	 */
	public Object getUniqueObj(String hql,List params);
	
	/**
	 * 删除
	 * @param o 要删除的对象
	 */
	public void delete(Object o);
	
	/**
	 * 修改
	 * @param o 要修改的对象
	 */
	public void update(Object o);
}
