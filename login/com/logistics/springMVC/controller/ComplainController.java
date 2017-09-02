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

import com.logistics.pojo.Company;
import com.logistics.pojo.Complain;
import com.logistics.pojo.User;
import com.logistics.serve.ComplainServe;
import com.logistics.serve.UtilServer;
import com.logistics.util.Page;




@Controller
@RequestMapping(value="/Complain/")
public class ComplainController {
	
	@Resource(name="Complainservice")
	public ComplainServe ComplainServe;
	
	@Resource(name="UtilService")
	public UtilServer utilServer;
	
	
	
	
	
	@RequestMapping(value="/complist",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object selectList(HttpServletRequest req,HttpServletResponse resp,int page, int rows) throws Exception {
		int value=26;

		User user = (User) req.getSession().getAttribute("user");
		Map dataMap = new HashMap();
		Page pagex = null;
		boolean boo = utilServer.haveFun(user.getUserid(), value);
		if(boo==true){
			pagex = ComplainServe.selectPage(user,page,rows);
			dataMap.put("total", pagex.getRows());
	  		dataMap.put("rows", pagex.getDataList());
	  		 return dataMap;
		}else{
			resp.sendRedirect("/jsp/main.jsp");
			return null;
		}

	}
	
	
	@RequestMapping(value="/compAdd",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object compAdd(HttpServletRequest req,HttpServletResponse resp,Complain complain) throws Exception {
		User user = (User) req.getSession().getAttribute("user");
		complain.setCompanyno(user.getCompanyno());
		int i;
		if(complain.getComplainid()==0){
			 i = ComplainServe.saveComp(complain);
		}else{
			 i = this.ComplainServe.updateComp(complain);
		}
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("status", i);
		return map;
		
	}
	
	@RequestMapping(value="/comptoselect")
	public String compToSelect(HttpServletRequest req, HttpServletResponse resp,int complainid) throws Exception {
		Complain comp = this.ComplainServe.selectById(complainid);
		req.setAttribute("complain", comp);
		return "";
	}
	
	

	
	@RequestMapping(value="/compdelete",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object compDelete(HttpServletRequest req,HttpServletResponse resp,String str){
	System.out.println("str====="+str);
		int i = this.ComplainServe.deleteComp(str);
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("status", i);
		return map;
	}
}
