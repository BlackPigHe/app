package com.logistics.springMVC.controller;

import java.util.HashMap;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.logistics.pojo.Company;
import com.logistics.serve.CompanyServe;
import com.logistics.util.Page;

@Controller
@RequestMapping(value = "/Company")
public class CompanyServlect {

	@Resource(name = "CompanyServe")
	public CompanyServe companyserve;

	@RequestMapping(value = "/compList", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> compList(Company comp, int page, int rows) {
		return null;
      
	}

	

}
