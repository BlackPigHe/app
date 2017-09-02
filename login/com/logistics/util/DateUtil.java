package com.logistics.util;

import java.text.SimpleDateFormat;
import java.util.Date;
//日期工具
public class DateUtil {
	private String format;
	//传入时间，返回一个可用于sql的字符串
	public String getDate(Date date){
		format="yyyy/MM/dd HH:mm:ss";	
		return getDate(date,format);
	}
	//返回指定格式的日期
	public String getDate(Date date,String format){
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		return sdf.format(date);
	}
	//返回当前日期
	public String getNowDate(){
		return getDate(new Date());
	}
	//返回指定格式的当前日期
	public String getNowDate(String format){
		return getDate(new Date(),format);
	}
}
