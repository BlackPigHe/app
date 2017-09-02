package com.logistics.springMVC.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.logistics.pojo.Dept;
import com.logistics.pojo.User;

import com.logistics.serve.DeptSever;
import com.logistics.serve.UtilServer;
import com.logistics.util.Page;
import com.logistics.util.UserUtil;

@Controller
@RequestMapping(value = "/Dept")
public class DeptServlet {
	@Resource(name ="DeptServe")
	public DeptSever deptServe;
	
	@Resource(name="UtilService")
	public UtilServer utilServer;
	
	
	@RequestMapping(value = "/deptList", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    public Map selectList(HttpServletRequest req,Dept dept,int page,int rows ) throws Exception {
		int value=7;
	
		Page pag=new Page();
		User user = UserUtil.getLoginUtil(req);
		System.out.println("user======="+user.getCompanyno());
		pag.setParams(user.getCompanyno());
		
		boolean boo = utilServer.haveFun(user.getUserid(), value);
		System.out.println("boo====="+boo);
		if(boo==true){
			Map dataMap = new HashMap();
			Page pagex = deptServe.selectlistDept(dept,page, rows);
			dataMap.put("total", pagex.getRows());
	  		dataMap.put("rows", pagex.getDataList());
	  		return dataMap;
		}else{
			return null;
		}
	}
}
