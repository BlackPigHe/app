package com.example.Magneto;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public  class streamStringUtils {
	static String con;
	public static String utils(InputStream in){
		
		//Ҫ�뽫������д�뵽�ڴ��У���Ҫ�õ��ڴ������
		ByteArrayOutputStream baos =new ByteArrayOutputStream();
		int len=-1;
		try {
			byte[] buffer=new byte[1024];//��ʼ��byte����
			while((len=in.read(buffer))!=-1){
				baos.write(buffer,0,len);
				
			}
			con=new String(baos.toByteArray());
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		
		
		return con;
		
	}
}
