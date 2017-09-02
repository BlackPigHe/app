package com.example.URLlook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.photo.R;


public class MainActivity extends Activity {
	protected static final int CONNECTSUCCESS = 0;
	protected static final int CONNECTFAIL = 1;
	EditText et_path;
	ImageView iv;
	private Handler handle=new Handler(){
		public void handleMessage(android.os.Message msg){
			Bitmap bitmap=(Bitmap) msg.obj;
			iv.setImageBitmap(bitmap);
			}
			
		
	};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        et_path= (EditText) findViewById(R.id.et);
        iv = (ImageView) findViewById(R.id.iv);
        
        
        
        
       
    }
    public void click(View v){
    	new Thread(){
    		public void run(){
    	    	//����url����
    			try {
    				File file=new File(getCacheDir(),"text.png");
    				if(file.exists()&&file.length()>0){
    					System.out.println("ʹ�û����ͼƬ");
    					Bitmap cacheBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
    					Message msg = Message.obtain();
    					msg.obj=cacheBitmap;
    					handle.sendMessage(msg);
    				}else{
    					System.out.println("��һ�η���ͼƬ");
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
        					//�����ļ�
        					
        					FileOutputStream fos=new FileOutputStream(file);
        					int len=-1;
        					byte[] buffer=new byte[1024];
        					while((len=in.read(buffer))!=-1){
        						fos.write(buffer,0,len);
        					}
        					fos.close();
        					//��������ת��Ϊbitmap��Ϣ
        					//1�ҵ�bitmap
        					Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        					//2�����ݷŵ�Bitmap��,ȥ���߳��и���UI
        					
        					//��Ҫhandler����Ϣ
        					Message msg = Message.obtain();
        					msg.obj=bitmap;
        					handle.sendMessage(msg);
        					
        				}else{
        					
        				}
    				}} catch (Exception e) {
        				e.printStackTrace();
        			}
        	    }
    				
    				
    		
    		
    	}.start();
    	

    			
    		
    	
    
   
}
    }
    
