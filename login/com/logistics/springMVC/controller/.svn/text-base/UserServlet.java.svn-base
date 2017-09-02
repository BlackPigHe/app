package com.logistics.springMVC.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.logistics.pojo.User;
import com.logistics.serve.UserServe;

@Controller
@RequestMapping(value="/User/")
public class UserServlet {

	@Resource(name="userService")
	public UserServe userserve;
	
	
	
	@RequestMapping(value="/login",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object login(HttpServletRequest req, HttpServletResponse resp,User user) throws Exception {
		int i = userserve.login(user,req);
		System.out.println("iiiii="+i);
		Map<String, Integer> map =new HashMap<String, Integer>();
		map.put("status", i);
		return map;
	}
	
	@RequestMapping(value="/resetpwd",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object resetpwd(HttpServletRequest req, HttpServletResponse resp,String oldPwd,String newPwd1) throws Exception {
		User user =(User) req.getSession().getAttribute("user");
		
		System.out.println("---oldPwd====="+oldPwd);
		System.out.println("---newPwd1====="+newPwd1);
	
		System.out.println("account="+user.getAccount()+"------------------pwd="+user.getPwd()+"-----------id="+user.getUserid());
		int i = userserve.updatePwd(user,oldPwd,newPwd1);
		System.out.println("iiiii="+i);
		Map<String, Integer> map =new HashMap<String, Integer>();
		map.put("status", i);
		return map;
	}
	
	
}
