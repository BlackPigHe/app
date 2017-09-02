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
			
				//����һ��ȷ�϶Ի�����߼�
				
			
			}else if(msg.what==1){
				Toast.makeText(getApplicationContext(), "���ӳɹ�", 1).show();
			}else if(msg.what==2){
				//û������
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
		// ���������Ϊ�˻�ȡ���Ƕ�����м��˶���
		bindService(intent, conn, BIND_AUTO_CREATE);
		// startService(intent);

		mp_play.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new Thread(){
		    		public void run(){
		    	    	//����url����
		    			try {
		    				//�õ�path������
		    				String path = mp_et.getText().toString().trim();
		    	    		URL url=new URL(path);
		    	    		//�������ȿ�
		    	    		Message msg=new Message();
		    				msg.what=0;
		    				handle.sendMessage(msg);
		    				
		    	    		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		    				urlConnection.setRequestMethod("GET");
		    				//�������ӳ�ʱ��ʱ��
		    				urlConnection.setConnectTimeout(2000);
		    				int code = urlConnection.getResponseCode();
		    				
		    				System.out.println(code);
		    				
		    				if(code==200){
		    					//˵�����ӳɹ�
		    					Message msg1=new Message();
		    					msg1.what=1;
		    					handle.sendMessage(msg1);
		    					System.out.print("���ӳɹ�"+code);
		    				
		    					iservice.callPlay();
		    					
		    				}else if(code!=200){
		    					Message msg2=new Message();
		    					msg2.what=2;
		    					handle.sendMessage(msg2);
		    					//Toast.makeText(getApplicationContext(), "Ŀǰû��·��������ݲ�����", 1).show();
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
		// ��activity����ʱ������
		unbindService(conn);
	}*/
	//��װȷ�϶Ի����
	public void queren(){
		AlertDialog.Builder build=new Builder(this);
		build.setTitle("����");
		build.setMessage("����������ʧ��");
		build.setPositiveButton("ȷ��",null);
		build.setNegativeButton("ȡ�� ", null);
		build.show();
		
	}
	//��װ�������ķ���
	public void jindu(){
		final ProgressDialog pd=new ProgressDialog(this);
		pd.setMessage("���ڶ�ȡ������Դ");
		//����һ�½���������ʽ
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
			// IBinder�������ǵ��м��˶���
			iservice = (Iservice) service;
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {

		}

	}
}
