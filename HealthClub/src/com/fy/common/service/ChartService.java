package com.fy.common.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fy.instructor.entity.Course;
import com.fy.instructor.entity.Instructor;


public interface ChartService {
	
	public List<Instructor> getInsInfo() ;

	public List<Course> getCouInfo();


	public Map<String, Object> getChartInfo(Date starttime, Date endtime, int texttype, int search) ;

	

}
