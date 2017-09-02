package com.logistics.springMVC.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.logistics.pojo.Function;
import com.logistics.pojo.User;
import com.logistics.serve.UtilServer;


@Controller
@RequestMapping(value="/Menu/")
public class MenuServlet {

	@Resource(name="UtilService")
	UtilServer util;

	
	@RequestMapping(value = "/menu",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object login(HttpServletRequest request, HttpServletResponse response) {
		User user=(User) request.getSession().getAttribute("user");
		System.out.println(user.getAccount());
		List<Function> n=util.getFun(util.getRole(user.getUserid()).getRoleid());
		System.out.println(n.size());
		return n;
	}

}
