package com.fy.customer.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fy.customer.service.RecordService;

@Controller
public class RecordController {
	
	@Autowired
	private RecordService recordService;
	
	@RequestMapping("/toRecord")
	public String gotoRecord() {
		return "recordPage";
	}

	@RequestMapping("/getRecords")
	public @ResponseBody Map<String,Object> getRecords(int limit,int offset,String searchType,String searchText,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date starttime,@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")Date endtime) throws UnsupportedEncodingException {
		System.out.println("limit is:"+limit);
		System.out.println("offset is:"+offset);
		System.out.println("searchType is"+searchType);
		System.out.println("searchText is"+searchText);
		System.out.println("starttime is"+starttime);
		System.out.println("endtime is"+endtime);
		Map<String,Object> map = recordService.getRecords(limit, offset,searchType,searchText,starttime,endtime);
		return map;
	}
	
}
