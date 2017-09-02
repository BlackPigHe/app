package com.logistics.springMVC.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.logistics.serve.FunctionServe;




@Controller
@RequestMapping(value="/Function")
public class FunctionServlet {
	@Resource(name="funServe")
	FunctionServe serve;
	@RequestMapping(value = "/getAllFunction",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object getFunction(HttpServletRequest request, HttpServletResponse response) {
		return serve.getFunction();
	}
}
