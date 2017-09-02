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
				Toast.makeText(getApplicationContext(), "���ӳɹ�", 1).show();
				String data2=(String) msg.obj;
				tv_result.setText(data2);
			}else if(msg.what==1){
				tv_result.setText("������");
				Toast.makeText(getApplicationContext(), "����ʧ��", 1).show();
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
    	    	//����url����
    			try {
    				//�õ�path������
    				String path = et_path.getText().toString().trim();
    	    		URL url=new URL(path);
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
    					final String data = streamStringUtils.utils(in);
    					//����ui�����߳���
    				
    					
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
    
