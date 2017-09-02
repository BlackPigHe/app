package com.logistics.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageList {
	//基于内存的分页
	public <T> Map<String, Object> pageStartToEnd(List<T> list,int start,int end){
		List<T> listOut=new ArrayList<T>();
		for(;start<end;start++){
			listOut.add(list.get(start));
		}
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("total", list.size());
		map.put("rows", listOut);
		return map;
	}
}
