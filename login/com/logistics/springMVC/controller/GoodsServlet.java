package com.logistics.springMVC.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.logistics.pojo.Goods;
import com.logistics.pojo.User;
import com.logistics.serve.ComplainServe;
import com.logistics.serve.GoodsServe;
import com.logistics.serve.UtilServer;
import com.logistics.util.Page;

@Controller
@RequestMapping(value="/Goods/")
public class GoodsServlet {

	@Resource(name="GoodsServeIm")
	public GoodsServe GoodsServeIm;
	
	@Resource(name="UtilService")
	public UtilServer utilServer;
	
	@RequestMapping(value="/goodslist",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object selectList(HttpServletRequest req,HttpServletResponse resp,int page, int rows) throws IOException{
		int value=18;
		User user = (User) req.getSession().getAttribute("user");
		Map dataMap = new HashMap();
		Page pagex = null;
		boolean boo = utilServer.haveFun(user.getUserid(), value);
		System.out.println("lalalalallala"+boo);
		if(boo==true){
			
			pagex = GoodsServeIm.selectList(user, page, rows);
			dataMap.put("total", pagex.getRows());
	  		dataMap.put("rows", pagex.getDataList());
	  		 return dataMap;
		}else{
			resp.sendRedirect("/jsp/main.jsp");
				return null;
		}
		
		
		
	}
	
	
	
	@RequestMapping(value="/goodsAdd",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object selectList(HttpServletRequest req,Goods goods){
		int goodsid = goods.getGoodsid();
			int i ;
			if(goodsid==0){
				i = this.GoodsServeIm.saveGoods(goods);	
			}else{
				i = this.GoodsServeIm.updateGoods(goods);
			}
		Map dataMap = new HashMap();
		dataMap.put("status", i);
		return dataMap;
		
	}
	
	@RequestMapping(value="/findgoods")
	public String findgoods(HttpServletRequest req, HttpServletResponse resp,int goodsid){
		Goods goods = this.GoodsServeIm.selectById(goodsid);
		req.setAttribute("goods", goods);
		return "";
	}
	
	
	@RequestMapping(value="/goodsdelete",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object selectList(HttpServletRequest req,String str){
		System.out.println("这次的str的长度是==="+str.length());
		int i = this.GoodsServeIm.deleteGoods(str);
		Map dataMap = new HashMap();
		dataMap.put("status", i);
		return dataMap;
		
	}
	
}
