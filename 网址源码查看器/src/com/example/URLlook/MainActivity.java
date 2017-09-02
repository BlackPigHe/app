package com.example.URLlook;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
	protected static final int CONNECTSUCCESS = 0;
	protected static final int CONNECTFAIL = 1;
	EditText et_path;
	TextView tv_result;
	private Handler handle=new Handler(){
		public void handleMessage(android.os.Message msg){
			if(msg.what==0){
				Toast.makeText(getApplicationContext(), "连接成功", 1).show();
				String data2=(String) msg.obj;
				tv_result.setText(data2);
			}else if(msg.what==1){
				tv_result.setText("无连接");
				Toast.makeText(getApplicationContext(), "连接失败", 1).show();
			}
			
		}
	};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        et_path= (EditText) findViewById(R.id.et);
        tv_result = (TextView) findViewById(R.id.tv_result);
        
        
        
       
    }
    public void click(View v){
    	
    	new Thread(){
    		public void run(){
    	    	//创建url对象
    			try {
    				//拿到path的内容
    				String path = et_path.getText().toString().trim();
    	    		URL url=new URL(path);
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
    					final String data = streamStringUtils.utils(in);
    					//更新ui到主线程中
    				
    					
    					Message msg=new Message();
    					msg.what=CONNECTSUCCESS;
    					msg.obj=data;
    					handle.sendMessage(msg);
    					
    				}else{
    					Message msg=new Message();
    					msg.what=CONNECTFAIL;
    					handle.sendMessage(msg);
    				}
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    	    }
    		
    		
    	}.start();
    	

    			
    		
    	
    
   
}
    }
    
