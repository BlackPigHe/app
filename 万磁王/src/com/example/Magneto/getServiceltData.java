package com.example.Magneto;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.Message;

public class getServiceltData {
	//ȥ�������õ�������
		 public static void submitData(){
			 new Thread(){
		    		public void run(){
		    	    	//����url����
		    			try {
		    				
		    	    		URL url=new URL("");
		    				HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		    				urlConnection.setRequestMethod("GET");
		    				//�������ӳ�ʱ��ʱ��
		    				urlConnection.setConnectTimeout(5000);
		    				int code = urlConnection.getResponseCode();
		    				if(code==200){
		    					//˵�����ӳɹ�
		    					System.out.print("���ӳɹ�"+code);
		    					//��ȡ�ӷ�������������������
		    					InputStream in = urlConnection.getInputStream();
		    					//����TextView��Ҫ�ַ�����������Ҫ��������ת��Ϊ�ַ��������԰Ѵ˷�����װһ��
		    					//final String data = streamStringUtils.utils(in);
		    					//����ui�����߳���
		    				
		    					
		    					
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
