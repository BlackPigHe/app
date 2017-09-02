package com.logistics.springMVC.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.logistics.pojo.Function;
import com.logistics.pojo.Role;
import com.logistics.pojo.User;
import com.logistics.serve.UserServe;
import com.logistics.serve.UtilServer;

@Controller
@RequestMapping(value="/Role/")
public class RoleServlet {
	@Resource(name="userService")
	public UserServe userserve;
	@Resource(name="UtilService")
	public UtilServer util;
	
	
	@RequestMapping(value="/getRole",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object login(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		User user=(User) req.getSession().getAttribute("user");
		List<Role> roleList=util.fromCompanynoGetRole(user.getCompanyno());
		
		return roleList;
	}
	@RequestMapping(value="/roleFun",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object roleFun(HttpServletRequest req, HttpServletResponse resp,int roleid) throws Exception {
		List<Function> funList= util.getFun(roleid);
		return funList;
		
	}
}
