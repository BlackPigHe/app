package com.logistics.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AopUtil {
	//将web环境封装到一个map，需要传入参数数组
	// 关键字  response，request
	public Map<String, Object> getWeb(Object[] args){
		Map<String, Object> map=new HashMap<String, Object>();
		for (Object o : args) {
			if (o instanceof HttpServletResponse) {
				map.put("response", o);
			}
			if (o instanceof HttpServletRequest) {
				map.put("request", o);
			}
		}
		return map;
	}
}
