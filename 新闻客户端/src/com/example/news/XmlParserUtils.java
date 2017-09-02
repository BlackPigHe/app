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
		//1.拿到xml的解析器
				XmlPullParser parser = Xml.newPullParser();
				//2.设置要解析的内容
				parser.setInput(in, "utf-8");
				//3.拿到解析的标记
				int type = parser.getEventType();
				//4.不停地进行解析
				while(type!=XmlPullParser.END_DOCUMENT){
					switch(type){
					case XmlPullParser.START_TAG:
						if("channal".equals(parser.getName())){
							//创建一个集合
							infoList=new ArrayList<Info>();
						}else if("item".equals(parser.getName())){
							//开始往info中写入数据
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
							//吧Info数据放到集合中
							System.out.println("打印"+info.getAuthor());
							System.out.println("打印"+parser.getName());
							if(infoList.add( info)){
								System.out.println("打印过去了");
							}else{
								System.out.println("打印过不去");
							}
							
							
							
						}
						break;
					}
					type=parser.next();
				}
	} catch (Exception e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}
			
			return infoList;
			
		}
}
