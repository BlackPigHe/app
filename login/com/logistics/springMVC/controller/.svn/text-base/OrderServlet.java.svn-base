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
import org.springframework.web.util.NestedServletException;

import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.logistics.pojo.Order;
import com.logistics.pojo.User;
import com.logistics.serve.OrderServe;
import com.logistics.serve.UtilServer;
import com.logistics.util.UserUtil;

@Controller
@RequestMapping(value = "OrderAction")
public class OrderServlet {

	@Resource(name = "orderServe")
	OrderServe serve;
	@Resource(name = "UtilService")
	private UtilServer util;

	@RequestMapping(value = "/entering", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object entering(HttpServletRequest request,
			HttpServletResponse response, Order order) {
		int value = 20;
		Map<String, Object> map = new HashMap<String, Object>();
		int status = 0;
		User user = UserUtil.getLoginUtil(request);
		if (util.haveFun(user.getUserid(), value)) {
			try {
				status = serve.insertOrder(order, user);
				status = (status == 0) ? status : 1;
			} catch (Exception e) {
				status = 3;
				e.printStackTrace();
			}
			map.put("status", status);
			map.put("order", order);
			return map;
		} else {
			map.put("status", 2);
			return map;
		}
	}

	@RequestMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object updateOrder(HttpServletRequest request,
			HttpServletResponse response, Order order) {
		User user = UserUtil.getLoginUtil(request);
		Map<String, Integer> map = null;
		if (util.haveFun(user.getUserid(), 19)) {
			try {
				map = serve.updateOrder(order, user);

			} catch (Exception e) {
				map = new HashMap<String, Integer>();
				map.put("status", 0);
			}

		} else {
			map = new HashMap<String, Integer>();
			map.put("status", 2);// 权限不足
		}
		return map;
	}

	@RequestMapping(value = "/select", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object selectOrder(int page, int rows, HttpServletRequest request,
			HttpServletResponse response, Order order) {
		int value = 19;
		Map<String, Integer> map = null;
		User user = UserUtil.getLoginUtil(request);
		System.out.println(util + "-----" + user);
		if (util.haveFun(user.getUserid(), value)) {
			map = serve.selectOrder(order, user, page, rows);
			map.put("status", 1);
		} else {
			map = new HashMap<String, Integer>();
			map.put("status", 2);// 权限不足
		}

		return map;
	}

	@RequestMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object delete(HttpServletRequest request,
			HttpServletResponse response, Order order) {
		int value = 19;
		Map<String, Integer> map = new HashMap<String, Integer>();
		User user = UserUtil.getLoginUtil(request);
		if (util.haveFun(user.getUserid(), value)) {
			if (order.getOrderno() != 0) {
				order.setCompanyno(user.getCompanyno());
				int n=serve.deleteOrder(order);
				map.put("status", n==0?0:1);
			}else {
				map.put("status", 4);
			}
		}else{
			map.put("status", 2);
		}

		return map;
	}
}
