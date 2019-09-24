package com.fy.common.task;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fy.instructor.service.WageService;

@Component
public class WageSettleTask {

	@Resource(name="wageServiceImpl")
	private  WageService wageService;
	
	@Scheduled(cron = " 0 0 0 30 * ? ") // 每月30号执行
//	@Scheduled(cron = " */10 * * * * ? ")
	public  void taskCycle() {
	
		wageService.wagebymonth();
		System.out.println("全部工资结算完毕");

	}
	

//	@Scheduled(cron = " */5 * * * * ? ")
//	public void getinfo() {
//		System.out.println("定时任务触发了");
//	}
	
	
}
