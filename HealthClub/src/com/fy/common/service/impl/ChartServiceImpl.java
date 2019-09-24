package com.fy.common.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fy.common.service.ChartService;
import com.fy.customer.dao.RecordDao;
import com.fy.instructor.dao.CourseDao;
import com.fy.instructor.dao.InstructorDao;
import com.fy.instructor.entity.Course;
import com.fy.instructor.entity.Instructor;

/**
 * 图标服务层
 * @author Administrator
 *
 */

@Service
@Transactional
public class ChartServiceImpl implements ChartService {
	@Autowired
	private InstructorDao instructorDao;
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private RecordDao recordDao;
	
	/**
	 * 获取全部的教练
	 * @return
	 */
	public List<Instructor> getInsInfo() {
			List<Instructor> instructorList = new ArrayList<Instructor>();
			instructorList = instructorDao.getAll();
		return instructorList;
	}

	/**
	 * 获取全部的客户
	 * @return
	 */
	public List<Course> getCouInfo() {
		List<Course> courseList = new ArrayList<Course>();
		courseList = courseDao.getAll();
	return courseList;
	}

	/**
	 * 根据时间内容获得画图表所需要的横纵坐标list
	 * @param starttime
	 * @param endtime
	 * @param texttype
	 * @param search
	 * @return
	 */
	public Map<String, Object> getChartInfo(Date starttime, Date endtime, int texttype, int search) {
		List<String> monthList = new ArrayList<String>();
		Map<String,Object> map = new HashMap<String,Object>();
		monthList = this.getMonthList(starttime,endtime);
		map = recordDao.getChartBytime(starttime,endtime,texttype,search);
		
		map.put("monthList", monthList);
		return map;
	}

	/**
	 * 获取给的时间的全部月份的String 集合
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	private List<String> getMonthList(Date starttime, Date endtime) {
		List<String> monthList = new ArrayList<String>();
		Calendar cl = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		cl.setTime(starttime);
		while(cl.getTime().before(endtime)){//判断是否到结束日期
			String str = sdf.format(cl.getTime());
			monthList.add(str);
			cl.add(Calendar.MONTH, 1);
			}
		String str = sdf.format(cl.getTime());
		monthList.add(str);
		return monthList;
		
	}
	
}
