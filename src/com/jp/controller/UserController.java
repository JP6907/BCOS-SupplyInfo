package com.jp.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
//import com.formssi.entity.ReturnJson;
//import com.formssi.entity.User;
//import com.formssi.service.UserService;
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;

//import utils.MD5Util;
//import utils.MySessionContext;
//import utils.Token;
//import utils.Utils;

@Controller
//@RequestMapping("/login")
public class UserController {
	
	
	@RequestMapping(value = "/login", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String login(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");//跨域访问
		String orgID=request.getParameter("orgID");
		String orgName=request.getParameter("orgName");
		String password=request.getParameter("password");
		
		//登录操作
		
		HttpSession session=request.getSession();
		session.setAttribute("orgID", orgID);
		
		JSONObject res=new JSONObject();
		res.put("status", "1");
		return res.toJSONString();
	}
	
	@RequestMapping(value = "/register", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String register(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");//跨域访问
		String orgID=request.getParameter("orgID");
		String orgName=request.getParameter("orgName");
		String password=request.getParameter("password");
		String iouLimt=request.getParameter("iouLimit");
		
		
		//注册操作
		
		HttpSession session=request.getSession();
		
		JSONObject res=new JSONObject();
		res.put("status", "1");
		return res.toJSONString();
	}
	
	@RequestMapping(value = "/logout", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");//跨域访问
		
		HttpSession session=request.getSession();
		session.invalidate();
		
		JSONObject res=new JSONObject();
		res.put("status", "1");
		return res.toJSONString();
	}
	
	@RequestMapping(value = "/ioulist", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String ioulist(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");//跨域访问
		String pageNum=request.getParameter("pageNum");
		String pageSize=request.getParameter("pageSize");
		
		HttpSession session=request.getSession();
		String orgID=(String) session.getAttribute("orgID");
		
		//iou列表操作
		
		JSONArray res=new JSONArray();
		for (int i=0;i<2;i++) {
			//JSONObject xxx = new JSONObject;
			// 各种put
			//res.add(xxx);
		}
		return res.toJSONString();
	}
	
	@RequestMapping(value = "/get_ioulist_num", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String get_ioulist_num(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");//跨域访问
		
		HttpSession session=request.getSession();
		String orgID=(String) session.getAttribute("orgID");
		
		//获取ioulist数目
		
		JSONObject res=new JSONObject();
		res.put("amount", "99");
		return res.toJSONString();
	}
	
	@RequestMapping(value = "/recycle_iou", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String recycle_iou(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");//跨域访问
		String iouId=request.getParameter("iouId");
		String amount=request.getParameter("amount");
		
		HttpSession session=request.getSession();
		String orgID = (String) session.getAttribute("orgID");
		
		JSONObject res=new JSONObject();
		res.put("status", "1");
		return res.toJSONString();
	}
	
	@RequestMapping(value = "/update_iou_limit", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String update_iou_limit(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");//跨域访问
		String amount=request.getParameter("amount");
		
		HttpSession session=request.getSession();
		String orgID = (String) session.getAttribute("orgID");
		
		//更新白条额度操作
		
		JSONObject res=new JSONObject();
		res.put("status", "1");
		return res.toJSONString();
	}
	
	@RequestMapping(value = "/transactionlist", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String transactionlist(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");//跨域访问
		String pageNum=request.getParameter("pageNum");
		String pageSize=request.getParameter("pageSize");
		
		HttpSession session=request.getSession();
		String orgID=(String) session.getAttribute("orgID");
		
		//交易列表操作
		
		JSONArray res=new JSONArray();
		for (int i=0;i<2;i++) {
			//JSONObject xxx = new JSONObject;
			// 各种put
			//res.add(xxx);
		}
		return res.toJSONString();
	}
	
	@RequestMapping(value = "/get_transaction_num", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String get_transaction_num(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");//跨域访问
		
		HttpSession session=request.getSession();
		String orgID=(String) session.getAttribute("orgID");
		
		//获取get_transaction_num数目
		
		JSONObject res=new JSONObject();
		res.put("amount", "99");
		return res.toJSONString();
	}
	
	@RequestMapping(value = "/add_transaction", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String add_transaction(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");//跨域访问
		String saleOrg=request.getParameter("saleOrg");
		String buyOrg=request.getParameter("buyOrg");
		String transType=request.getParameter("transType");
		String amount=request.getParameter("amount");
		String conFile=request.getParameter("conFile");
		
		HttpSession session=request.getSession();
		String orgID = (String) session.getAttribute("orgID");
		
		//添加交易操作
		
		JSONObject res=new JSONObject();
		res.put("status", "1");
		return res.toJSONString();
	}
	
	@RequestMapping(value = "/get_transaction/{con_id}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String get_transaction(@PathVariable("con_id") String con_id, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		response.setHeader("Access-Control-Allow-Origin", "*");//跨域访问
		System.out.println("Product Id  ff : " + con_id); 
		
		//根据合同获得交易信息
		
		
		JSONObject res=new JSONObject();
		res.put("zzz", "xxxx");
		//te.fluentAdd(zz);
		return res.toJSONString();
	}
	
//	@RequestMapping(value="/product/{productId}", produces = "application/json;charset=UTF-8") 
//	public String getProduct(@PathVariable("productId") String productId, HttpServletResponse response, HttpSession session){ 
//	    System.out.println("Product Id : " + productId); 
//	    return "hello"; 
//	} 
	
	@RequestMapping(value = "/test", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String test(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		response.setHeader("Access-Control-Allow-Origin", "*");//跨域访问
		System.out.println(request.getParameter("username")+"   @@@@@");
		return "666";
	}
	
	@RequestMapping(value = "/product/{productId}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String test(@PathVariable("productId") String productId, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		response.setHeader("Access-Control-Allow-Origin", "*");//跨域访问
		System.out.println("Product Id  ff : " + productId); 
		JSONObject zz=new JSONObject();
		zz.put("zzz", "xxxx");
		System.out.println(zz.toJSONString()); 
		JSONArray te=new JSONArray();
		te.add(zz);
		for(int i=0;i<2;i++) {
		JSONObject zzz =new JSONObject();
		zzz.put("ddd", "ddsss");
		te.add(zzz);
		}
		//te.fluentAdd(zz);
		return te.toJSONString();
	}
}
