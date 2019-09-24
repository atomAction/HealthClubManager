package com.fy.customer.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fy.customer.entity.CustomerFile;
import com.fy.customer.service.CustomerFileService;

@Controller
public class CustomerFileController {

	@Autowired
	private CustomerFileService customerFileService; 
	
	
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
	

	@RequestMapping("/updateFile")
	public @ResponseBody Map<String,Object> update(@RequestBody  CustomerFile customerFile){
		System.out.println(customerFile);
		customerFileService.updateCustomerFile(customerFile);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status", "success");
		return map;
	}
	
	
	
	@RequestMapping("/queryCustomerFileById")
	public @ResponseBody CustomerFile queryUserById( @RequestParam("f_id")  int id){
		System.out.println(customerFileService.getCustomerFileById(id));
		CustomerFile customerFile = customerFileService.getCustomerFileById(id);	
		return customerFile;
	}
}
