package com.fy.customer.controller;


import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

import com.fy.customer.entity.Customer;
import com.fy.customer.service.CustomerService;

//@SessionAttributes(value = "uername")
//告诉服务器，这个类是springMVC 的控制层，可以接收和处理用户请求
@Controller    //使用该注解标志它是一个控制器
public class CustomerController {
	
	
	@Autowired
	private CustomerService customerService;

	
	
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
	

	/**
	 * 到显示客户页面
	 * @param map 使用的是map保存回显数据
	 * @return
	 */
	@RequestMapping(value = "/toCustomerList")
	public String gotoCustomerIndex() {
		return "customerPage";
	}
	
	
	/**
	 * 客户table显示
	 * @param limit
	 * @param offset
	 * @return
	 */
	@RequestMapping("/getCustomerInfo")
	public @ResponseBody Map<String,Object> getPageInfo(int limit,int offset,String searchType,String searchText) throws UnsupportedEncodingException {	
		System.out.println("limit is:"+limit);
		System.out.println("offset is:"+offset);
		System.out.println("searchType is"+searchType);
		System.out.println("searchText is"+searchText);
		Map<String,Object> map = customerService.queryPageInfo(limit, offset,searchType,searchText);
		return map;
	}
 
	/**
	 * 根据ID 删除客户  
	 * @param id
	 * @return
	 */

	@RequestMapping("/deleteCustomerById")
	public @ResponseBody Map<String,Object> deleteById(@RequestParam("id")int id){
		customerService.deleteById(id);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status","success");
		return map;
	}
	
	
	/**
	 * 根据idlist 批量删除客户
	 * @param idList
	 * @return
	 */
	@RequestMapping("/deleteCustomerByList")
	public @ResponseBody Map<String,Object> deleteByList(@RequestParam("idList[]") List<Integer> idList){	
		customerService.deleteByList(idList);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status","success");
		return map;
	}
	
	
	/**
	 * 根据ID获取某一客户
	 * @param id
	 * @return
	 */
	@RequestMapping("/queryCustomerById")
	public @ResponseBody Customer queryCustomerById( @RequestParam("id")  int id){
		System.out.println(customerService.queryUserById(id));
		Customer customer = customerService.queryUserById(id);	
		return customer;
	}
	/**
	 * 根据Name获取某一客户
	 * @param id
	 * @return
	 */
	@RequestMapping("/queryCustomerByName")
	public @ResponseBody Customer queryCustomerByName( @RequestParam("name")  String name){
		System.out.println(customerService.queryCustomerByName(name));
		Customer customer = customerService.queryCustomerByName(name);	
		return customer;
	}
	/**
	 * 修改客户信息  
	 * 不能修改客户卡类型  只能由充值系统进行更改  （后面再改成可以修改客户卡的）
	 * @param customer
	 * @return
	 */

	@RequestMapping("/updateCustomer")
	public @ResponseBody Map<String,Object> update(@RequestBody  Customer customer){
		System.out.println(customer);
		customerService.update(customer);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status", "success");
		return map;
	}
	/**
	 * 新增一个客户（后面再详细处理cardtype这个关联类）
	 * @param name
	 * @param balance
	 * @param cardtype
	 * @param phone
	 * @return
	 */
	
	@RequestMapping("/saveCustomer")
	public   @ResponseBody Map<String,Object>  save( @RequestParam(value="name", required=false)  String name ,
			@RequestParam(value="balance", required=false)  double balance ,
			@RequestParam(value="cardtype", required=false)  String cardtype,
			@RequestParam(value= "phone", required=false)  String phone){
		customerService.saveCustomer(name,balance,cardtype,phone);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status", "success");	
		return map;
	}
//	@RequestMapping("save")
//	public @ResponseBody Map<String,Object> save(JSONObject  jsonobject){
//		System.out.println("asdasd");
//		customerService.save(customer);
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("status", "success");
//		return map;
//	}
//	@ModelAttribute
//	public void getCustomerById(@RequestParam(value = "id" , required = false)Integer id,Map<String,Object> map ) {
//		if(id != null) {
//			map.put("customer" ,customerService.getCustomerById(id));
//		}
//	}



	/**
	 * 查询所有人员信息  
	 * @param map 使用的是map保存回显数据
	 * @return
	 */
//	@RequestMapping(value = "/main")
//	public String mian(Map<String, Object> map) {
//		map.put("customerlist", customerService.getCustomers());
//		//	遍历集合，查看查询到的数据
//		 List<Customer> lists = customerService.getCustomers(); 
//		 Iterator<Customer> it = lists.iterator(); 
//		 while(it.hasNext()){ 
//			 Customer p =it.next();
//		 	 	System.out.println(p.toString());
//		 }
//		return "main1";
//	}
	

//	/**
//	 * 更新数据
//	 * @param customer
//	 * @return
//	 */
//	@RequestMapping(value = "/updatecustomer")
//	public String updateCustomer(Customer customer) {
//		System.out.println(customer.toString());
//		customerService.updateCustomer(customer);
//		return "redirect:main";
//	}
//	
//	/**
//	 * 到添加界面
//	 */
//	@RequestMapping(value = "/addCustomer",method = RequestMethod.GET)
//	public String gotoAddCustomer(Model model) {
//		Customer customer = new Customer();
//		model.addAttribute("Custoemr_form", customer);
//		return "addCustomer";
//	}
//	/**
//	 * 执行添加
//	 */
//	@RequestMapping(value="/addCustomer",method = RequestMethod.POST)
//	public String doaddCustomer( Customer customer) { 
//		customerService.addCustomer(customer);
//		return "redirect:main";
//	}
//	
//	/**
//	 * 删除单个客户
//	 */
//	@RequestMapping("/deleteCustomerById")
//	public String deleteCustomerById(@RequestParam(value = "id") int id) {
//		customerService.deleteCustomerById(id);
//		System.out.println("删除单个");
//		return "redirect:main";
//	}
//	
//	/**
//	 * 根据id 编辑客户信息
//	 * 到客户编辑界面
//	 */
//	@RequestMapping(value = "/editCustomer",method = RequestMethod.GET)
//	public String gotoEditCustomerById(Model model,@RequestParam(value = "id") int id) {
//	 	model.addAttribute("customer",customerService.getCustomerById(id));
//			 return "editCustomer";
//	}
//	
//	/**
//	 * 更新客户信息
//	 * @param customer
//	 * @return
//	 */
//	public String doEditCustomerById(Customer customer) {
//		customerService.updateCustomer(customer); 
//		return "redirect:main";
//	}
	
	
	/**
	 * 根据ID 编辑和添加客户基本信息
	 * 到编辑界面
	 */
//	@RequestMapping(value = "/editCustomerFile" ,method = RequestMethod.GET)
//	public String gotoEditCustomerFileById(Model model,@RequestParam(value = "id")String id ) {
//	//	System.out.println(id);
//		if( customerService.getCustomerFileById(id)==null)
//		{
//			CustomerFile customerFile = new CustomerFile();
//			customerFile.setF_id(id);
//			System.out.println(customerFile);
//			model.addAttribute("customerFile_form",customerFile );
//		}
//		else
//			model.addAttribute("customerFile_form",customerService.getCustomerFileById(id) );
//		
//		
//		return "editCustomerFile";
//	}
//	
//	/**
//	 * 保存更新修改的客户基本信息
//	 * @param customerFile
//	 * @return
//	 */
//	@RequestMapping(value = "/editCustomerFile",method = RequestMethod.POST)
//	public String doEditCustomerFileById(CustomerFile customerFile) {
//		customerService.updateCustomerFile(customerFile);
//		return "redirect:main";
//	}
	
	
	
}
