package com.logistics.util;

import javax.servlet.http.HttpServletRequest;

import com.logistics.pojo.User;

public class UserUtil {
	//获取登陆的用户
	public static User getLoginUtil(HttpServletRequest req){
		return (User)req.getSession().getAttribute("user");
	}
}
