package com.example.xmlparser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

public class weatherParser {
	public static List<channel>  parserXml(InputStream ins) throws Exception{
		//����һ�����϶���
		List<channel> weatherList=null;
		//����һ��java�������Ͷ���
		channel ch=null;
		//ʵ����
		XmlPullParser parser = Xml.newPullParser();
		
		//�õ�PullParser�������Ĳ���
		parser.setInput(ins, "utf-8");
		//��ȡ�¼�����
		int type = parser.getEventType();
		while(type!=XmlPullParser.END_DOCUMENT){
			switch(type){
			case XmlPullParser.START_TAG://������ʼ��ǩ
				if("weather".equals(parser.getName())){//ȡ��weather�ı�ǩ
					//ʹ�ü��϶��������
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
			case XmlPullParser.END_TAG://����������ǩ
				
				break;
			}
			
			type=parser.next();
			
		}
		
		
		return null;
		
	}

}
