package com.example.Magneto;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.Message;

public class getServiceltData {
	//去服务器拿到流数据
		 public static void submitData(){
			 new Thread(){
		    		public void run(){
		    	    	//创建url对象
		    			try {
		    				
		    	    		URL url=new URL("");
		    				HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		    				urlConnection.setRequestMethod("GET");
		    				//设置连接超时的时间
		    				urlConnection.setConnectTimeout(5000);
		    				int code = urlConnection.getResponseCode();
		    				if(code==200){
		    					//说明连接成功
		    					System.out.print("连接成功"+code);
		    					//获取从服务器传回来的流数据
		    					InputStream in = urlConnection.getInputStream();
		    					//设置TextView需要字符串，所以需要把流数据转换为字符串，可以把此方法封装一下
		    					//final String data = streamStringUtils.utils(in);
		    					//更新ui到主线程中
		    				
		    					
		    					
		    /*					msg.what=CONNECTSUCCESS;
		    					msg.obj=data;
		    					handle.sendMessage(msg);*/
		    					
		    				}else{
		    					Message msg=new Message();
		    		//			msg.what=CONNECTFAIL;
		    			//		handle.sendMessage(msg);
		    				}
		    			} catch (Exception e) {
		    				e.printStackTrace();
		    			}
		    	    }
		    		
		    		
		    	}.start();
		 }
}
