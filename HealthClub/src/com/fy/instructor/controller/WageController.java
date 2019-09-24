package com.fy.instructor.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fy.instructor.service.WageService;

@Controller
public class WageController {
	
	@Autowired
	private WageService wageService;
	
	
	/**
	 * 用于将前端的时期从字符串转为后台的时间类型
	 * @param binder
	 */
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
       binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));//第二个参数是控制是否支持传入的值是空，这个值很关键，如果指定为false，那么如果前台没有传值的话就会报错
    }

	@RequestMapping("/toWage")
	public String gotoWagePage() {
		return "wagePage";
	}
	
	@RequestMapping("/getWageInFo")
	public @ResponseBody Map<String,Object> getWageInFo(int limit,int offset,String searchType,String searchText) throws ParseException{
		Map<String,Object> map = new HashMap<String,Object>();
		map = wageService.getWageInFo(limit,offset,searchType,searchText);
		return map;
	}
}
