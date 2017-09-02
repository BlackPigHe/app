package com.example.login;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import android.os.Environment;

public class userInfoUtils {

	/**
	 * @param args
	 */
	public static boolean saveData(String username,String pwd) {
		String result=username+"##"+pwd;
		
		String path = Environment.getExternalStorageDirectory().getPath();
		File file=new File(path,"info.txt");
		try {
			FileOutputStream fos=new FileOutputStream(file);
			fos.write(result.getBytes());
			fos.close();
			return true;
		} catch (FileNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			return false;
		}
		// TODO �Զ����ɵķ������
		
	}

	public static Map<String,String>  readData() {
		Map<String,String> maps=new HashMap<String, String>();
		
		String path = Environment.getExternalStorageDirectory().getPath();
		File file=new File(path,"info.txt");
		try {
			FileInputStream fis=new FileInputStream(file);
			BufferedReader bfr=new BufferedReader(new InputStreamReader(fis));
			String content=bfr.readLine();
			String[] splits=content.split("##");
			String name=splits[0];
			String pwd=splits[1];
			maps.put("name", name);
			maps.put("pwd", pwd);
			bfr.close();
			return maps;
			
		} catch (FileNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			return null;
		}
		
		// TODO �Զ����ɵķ������

	}

}
