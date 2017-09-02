package com.example.Magneto;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MusicPlayActivity extends Activity {
	EditText mp_et;
	Button mp_zanting, mp_play, mp_jixu;
	Iservice iservice;
	Myconn conn;
	
	private Handler handle=new Handler(){
		public void handleMessage(android.os.Message msg){
			if(msg.what==0){
				jindu();
			
				//弹出一个确认对话框的逻辑
				
			
			}else if(msg.what==1){
				Toast.makeText(getApplicationContext(), "连接成功", 1).show();
			}else if(msg.what==2){
				//没连到网
				 queren();
			}
			
		}
	};
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mp);
		mp_play = (Button) findViewById(R.id.mp_play);
		mp_zanting = (Button) findViewById(R.id.mp_zanting);
		mp_jixu = (Button) findViewById(R.id.mp_jixu);
		final EditText mp_et = (EditText) findViewById(R.id.mp_et);
		String path = mp_et.getText().toString().trim();
		Intent intent = new Intent(this, MusicService.class);
		intent.putExtra("path", path);
		
		
		conn = new Myconn();
		// 这个方法是为了获取我们定义的中间人对象
		bindService(intent, conn, BIND_AUTO_CREATE);
		// startService(intent);

		mp_play.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new Thread(){
		    		public void run(){
		    	    	//创建url对象
		    			try {
		    				//拿到path的内容
		    				String path = mp_et.getText().toString().trim();
		    	    		URL url=new URL(path);
		    	    		//弹出进度框
		    	    		Message msg=new Message();
		    				msg.what=0;
		    				handle.sendMessage(msg);
		    				
		    	    		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		    				urlConnection.setRequestMethod("GET");
		    				//设置连接超时的时间
		    				urlConnection.setConnectTimeout(2000);
		    				int code = urlConnection.getResponseCode();
		    				
		    				System.out.println(code);
		    				
		    				if(code==200){
		    					//说明连接成功
		    					Message msg1=new Message();
		    					msg1.what=1;
		    					handle.sendMessage(msg1);
		    					System.out.print("连接成功"+code);
		    				
		    					iservice.callPlay();
		    					
		    				}else if(code!=200){
		    					Message msg2=new Message();
		    					msg2.what=2;
		    					handle.sendMessage(msg2);
		    					//Toast.makeText(getApplicationContext(), "目前没网路，或该数据不存在", 1).show();
		    				}
		    			} catch (Exception e) {
		    				e.printStackTrace();
		    			}
		    	    }
		    		
		    		
		    	}.start();
				

			}
		});
		mp_zanting.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				iservice.callZanting();
			}
		});
		mp_jixu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				iservice.callReplay();
			}
		});
	}

	/*protected void onDestroy() {
		// 当activity销毁时解绑服务
		unbindService(conn);
	}*/
	//封装确认对话框的
	public void queren(){
		AlertDialog.Builder build=new Builder(this);
		build.setTitle("警告");
		build.setMessage("你网络连接失败");
		build.setPositiveButton("确认",null);
		build.setNegativeButton("取消 ", null);
		build.show();
		
	}
	//封装进度条的方法
	public void jindu(){
		final ProgressDialog pd=new ProgressDialog(this);
		pd.setMessage("正在读取网络资源");
		//设置一下进度条的样式
		pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		pd.show();
		new Thread(new Runnable(){
			@Override
			public void run() {
				pd.setMax(100);
				for(int i=0;i<100;i++){
					pd.setProgress(i);
					SystemClock.sleep(10);
				}
				pd.dismiss();
			}
		}).start();
	}
	public class Myconn implements ServiceConnection {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// IBinder就是我们的中间人对象
			iservice = (Iservice) service;
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {

		}

	}
}
