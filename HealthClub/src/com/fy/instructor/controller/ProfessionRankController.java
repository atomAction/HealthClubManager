package com.fy.instructor.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fy.instructor.entity.ProfessionRank;
import com.fy.instructor.service.ProfessionRankService;

@Controller
public class ProfessionRankController {

	@Autowired
	private ProfessionRankService professionRankService;
	
	@RequestMapping(value = "/toProfessionRankList")
	public String gotoInstructorIndex() {
		return "professionRankPage";
	}
	
	@RequestMapping("/getProfessionRankInfo")
	public @ResponseBody Map<String,Object> getPageInfo(int limit,int offset,String searchType,String searchText) throws UnsupportedEncodingException {

		System.out.println("limit is:"+limit);
		System.out.println("offset is:"+offset);
		System.out.println("searchType is"+searchType);
		System.out.println("searchText is"+searchText);
		Map<String,Object> map = professionRankService.queryPageInfo(limit, offset,searchType,searchText);
		return map;
	}
 
	/**
	 * 根据ID 删除客户  
	 * @param id
	 * @return
	 */

	@RequestMapping("/deleteProfessionRankById")
	public @ResponseBody Map<String,Object> deleteById(@RequestParam("profession_number")int id){
		professionRankService.deleteById(id);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status","success");
		return map;
	}
	
	
	/**
	 * 根据idlist 批量删除客户
	 * @param idList
	 * @return
	 */
	@RequestMapping("/deleteProfessionRankByList")
	public @ResponseBody Map<String,Object> deleteByList(@RequestParam("idList[]") List<Integer> idList){	
		professionRankService.deleteByList(idList);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status","success");
		return map;
	}
	
	
	/**
	 * 根据ID获取某一客户
	 * @param id
	 * @return
	 */
	@RequestMapping("/queryProfessionRankById")
	public @ResponseBody ProfessionRank queryUserById( @RequestParam("profession_number") int id){
		System.out.println(professionRankService.queryUserById(id));
		ProfessionRank professionRank = professionRankService.queryUserById(id);	
		return professionRank;
	}
	
	/**
	 * 修改客户信息  
	 * 不能修改客户卡类型  只能由充值系统进行更改  （后面再改成可以修改客户卡的）
	 * @param customer
	 * @return
	 */

	@RequestMapping("/updateProfessionRank")
	public @ResponseBody Map<String,Object> updateProfessionRank(  ProfessionRank professionRank){
		System.out.println(professionRank);
		professionRankService.update(professionRank);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status", "success");
		return map;
	}
	
	
	@RequestMapping("/saveProfessionRank")
	public @ResponseBody Map<String,Object> saveProfessionRank(  ProfessionRank professionRank){
		System.out.println(professionRank);
		professionRankService.save(professionRank);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status", "success");
		return map;
	}
	
}
