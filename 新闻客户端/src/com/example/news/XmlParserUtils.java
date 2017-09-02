package com.example.news;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import android.util.Xml;

public class XmlParserUtils {
	
	public static List<Info> parserXml(InputStream in){
			List<Info>  infoList = null;	
			Info info = null;
	try {
		//1.�õ�xml�Ľ�����
				XmlPullParser parser = Xml.newPullParser();
				//2.����Ҫ����������
				parser.setInput(in, "utf-8");
				//3.�õ������ı��
				int type = parser.getEventType();
				//4.��ͣ�ؽ��н���
				while(type!=XmlPullParser.END_DOCUMENT){
					switch(type){
					case XmlPullParser.START_TAG:
						if("channal".equals(parser.getName())){
							//����һ������
							infoList=new ArrayList<Info>();
						}else if("item".equals(parser.getName())){
							//��ʼ��info��д������
							info=new Info();
						}else if("title".equals(parser.getName())){
							info.setTitle(parser.nextText());
						}else if("link".equals(parser.getName())){
							info.setLink(parser.nextText());
						}else if("author".equals(parser.getName())){
							info.setAuthor(parser.nextText());
						}else if("image".equals(parser.getName())){
							info.setImage(parser.nextText());
						}else if("pubDate".equals(parser.getName())){
							info.setPubDate(parser.nextText());
						}else if("description".equals(parser.getName())){
							info.setDescription(parser.nextText());
						}else{}
						
						break;
					case     XmlPullParser.END_TAG:
						if("item".equals(parser.getName())){
							//��Info���ݷŵ�������
							System.out.println("��ӡ"+info.getAuthor());
							System.out.println("��ӡ"+parser.getName());
							if(infoList.add( info)){
								System.out.println("��ӡ��ȥ��");
							}else{
								System.out.println("��ӡ����ȥ");
							}
							
							
							
						}
						break;
					}
					type=parser.next();
				}
	} catch (Exception e) {
		// TODO �Զ����ɵ� catch ��
		e.printStackTrace();
	}
			
			return infoList;
			
		}
}
