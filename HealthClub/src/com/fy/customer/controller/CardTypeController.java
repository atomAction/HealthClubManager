package com.fy.customer.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fy.customer.entity.CardType;
import com.fy.customer.service.CardTypeService;

@Controller
public class CardTypeController {
	
	@Autowired
	private CardTypeService cardTypeService;
	
	
	@RequestMapping("/toCardTypeList")
	public String gotoCourse() {
		return "cardTypePage";
	}

	@RequestMapping("/getCardTypeInfo")
	public @ResponseBody Map<String,Object> getPageInfo(int limit,int offset) throws UnsupportedEncodingException {
		System.out.println("limit is:"+limit);
		System.out.println("offset is:"+offset);
		Map<String,Object> map = cardTypeService.queryPageInfo(limit, offset);
		return map;
	}
 
	/**
	 * 根据ID 删除客户  
	 * @param id
	 * @return
	 */

	@RequestMapping("/deleteCardTypeById")
	public @ResponseBody Map<String,Object> deleteById(@RequestParam("id")int id){
		cardTypeService.deleteById(id);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status","success");
		return map;
	}
	
	
	/**
	 * 根据idlist 批量删除客户
	 * @param idList
	 * @return
	 */
	@RequestMapping("/deleteCardTypeByList")
	public @ResponseBody Map<String,Object> deleteByList(@RequestParam("idList[]") List<Integer> idList){	
		cardTypeService.deleteByList(idList);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status","success");
		return map;
	}
	
	
	/**
	 * 根据ID获取某一客户
	 * @param id
	 * @return
	 */
	@RequestMapping("/queryCardTypeById")
	public @ResponseBody CardType queryUserById( @RequestParam("id") int id){
		System.out.println(cardTypeService.queryUserById(id));
		CardType cardType = cardTypeService.queryUserById(id);	
		return cardType;
	}
	
	/**
	 * 修改客户信息  
	 * 不能修改客户卡类型  只能由充值系统进行更改  （后面再改成可以修改客户卡的）
	 * @param cardType
	 * @return
	 */

	@RequestMapping("/updateCardType")
	public @ResponseBody Map<String,Object> updateCardType( CardType cardType){
		System.out.println(cardType);
		cardTypeService.update(cardType);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status", "success");
		return map;
	}
	
	
	@RequestMapping("/saveCardType")
	public @ResponseBody Map<String,Object> saveCardType( CardType cardType){
		System.out.println(cardType);
		cardTypeService.save(cardType);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status", "success");
		return map;
	}

}
