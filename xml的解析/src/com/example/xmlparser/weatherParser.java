package com.example.xmlparser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

public class weatherParser {
	public static List<channel>  parserXml(InputStream ins) throws Exception{
		//申明一个集合对象
		List<channel> weatherList=null;
		//申明一个java数据类型对象
		channel ch=null;
		//实例化
		XmlPullParser parser = Xml.newPullParser();
		
		//拿到PullParser解析器的参数
		parser.setInput(ins, "utf-8");
		//获取事件类型
		int type = parser.getEventType();
		while(type!=XmlPullParser.END_DOCUMENT){
			switch(type){
			case XmlPullParser.START_TAG://解析开始标签
				if("weather".equals(parser.getName())){//取到weather的标签
					//使用集合对象放数据
					weatherList=new ArrayList<channel>();
				}
				else if("channel".equals(parser.getName())){
					ch=new channel();
					ch.setId(parser.getAttributeValue(0));
				}else if("city".equals(parser.getName())){
					ch.setCity(parser.nextText());
				}else if("temp".equals(parser.getName())){
					ch.setTemp(parser.nextText());
				}else if("wind".equals(parser.getName())){
					ch.setWind(parser.nextText());
				}else if("pm250".equals(parser.getName())){
					ch.setPm250(parser.nextText());
				}
				
			break;
			case XmlPullParser.END_TAG://解析结束标签
				
				break;
			}
			
			type=parser.next();
			
		}
		
		
		return null;
		
	}

}
