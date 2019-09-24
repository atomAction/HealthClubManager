package com.fy.common.controller;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fy.common.entity.User;
import com.fy.common.service.ChartService;
import com.fy.common.service.PurchaseService;
import com.fy.common.service.RechargeService;
import com.fy.common.service.UserService;
import com.fy.instructor.entity.Course;
import com.fy.instructor.entity.Instructor;


/**
 * 
 * @author Administrator
 *
 */
@Controller
public class MainOperateController {
	
//	@Autowired//@Autowired注解声明了，下面@Qualifier注解所表示的变量需要自动注入
//	private LoginService loginService;
	@Autowired
	private RechargeService rechargeService;
	@Autowired
	private PurchaseService purchaseService;
	@Autowired
	private ChartService chartService;
	@Autowired
	private UserService userService;
	
	/**
	 * 从欢迎界面到登录界面
	 */
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String gotologin() {
		return "login";
	}
	/**
	 * 到主页
	 * @return
	 */
	@RequestMapping(value="/toindex",method = RequestMethod.GET)
	public String gotoindex() {
		return "myindex";
	}
	/**
	 * 到系统管理员密码更改界面
	 * @return
	 */
	@RequestMapping(value="/update",method = RequestMethod.GET)
	public String updateUser() {
		return "updateUser";
	}
	/**
	 * 提交修改的密码修改密码
	 * @param Username
	 * @param Password
	 * @return
	 */
	@RequestMapping(value="/updateUser")
	public String doupdateUser(String Username,String Password) {
		User user = new User(Username, Password);
		userService.updateUser(user);
		return "login";
	}

	/**
	 *  登录界面到系统主页
	 * @param username
	 * @param password
	 * @param map
	 * @return
	 */
	@RequestMapping("/login")
	public String dologin(String username, String password) {
		if (userService.userlogin(username, password)) {
			return "index";
		}
		return "error";
	}

	/**
	 * 充值界面
	 * @return
	 */
	@RequestMapping("/toRecharge")
	public String gotoRecharge() {
		return "rechargePage";
	}
	
	/**
	 * 获取充值的用户和金额
	 * 进行充值
	 * @param c_id
	 * @param balance
	 * @return
	 */
	@RequestMapping("/rechargeBalance")
	public @ResponseBody Map<String,Object> doRecharge(@RequestParam("c_id")int c_id ,@RequestParam("balance") double balance) {
		rechargeService.doRecharge(c_id, balance);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status","success");
		return map;
	}
	
	/**
	 * 购卡界面
	 * @return
	 */
	
	@RequestMapping("/toPurchaseCard")
	public String gotoPurchase() {
		return "purchaseCardPage";
	}
	
	@RequestMapping("/purchaseCard")
	public @ResponseBody Map<String,Object> doPurchase(@RequestParam("c_id")int c_id ,@RequestParam("cardtypeId") int cardtypeId) {		
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("status","success");
		return purchaseService.doPurchase(c_id, cardtypeId);
	}
	
	//课程预定
	
	
	@RequestMapping("/toReserveLesson")
	public String gotoLesson() {
		return "lessonBookPage";
	}
	
	//画图
	@RequestMapping("/toChart")
	public String gotoChart() {
		return "chartPage";
	}
	
	//获得所有教练或者所有课程的信息
	@RequestMapping("/getInsInfo")
	public @ResponseBody List<Instructor> getInsInfo(){

		return chartService.getInsInfo();
	}
	/**
	 * 根据
	 * @param type
	 * @return
	 */
	@RequestMapping("/getCouInfo")
	public @ResponseBody List<Course> getCouInfo(){

		return chartService.getCouInfo();
	}
	
	/**
	 * 获得画图所需要的数组 即横坐标和纵坐标
	 * @param starttime
	 * @param endtime
	 * @param texttype
	 * @param search
	 * @return
	 */
	@RequestMapping("/getChartInfo")
	public @ResponseBody Map<String,Object> getChartInfo(@RequestParam("starttime")
			@DateTimeFormat(pattern = "yyyy-MM")Date starttime,
			@RequestParam("endtime") @DateTimeFormat(pattern = "yyyy-MM")Date endtime,
			@RequestParam ("texttype")int texttype,@RequestParam ("search")int search){
		
		return chartService.getChartInfo(starttime,endtime,texttype,search);
		
	}
	
	
}
