package com.example.Magneto;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public  class streamStringUtils {
	static String con;
	public static String utils(InputStream in){
		
		//要想将流数据写入到内存中，需要用到内存输出流
		ByteArrayOutputStream baos =new ByteArrayOutputStream();
		int len=-1;
		try {
			byte[] buffer=new byte[1024];//初始化byte数组
			while((len=in.read(buffer))!=-1){
				baos.write(buffer,0,len);
				
			}
			con=new String(baos.toByteArray());
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		
		return con;
		
	}
}
