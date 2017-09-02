package com.example.GameLogin;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Pattern;

import com.example.gameContent.GameActivity;
import com.example.myfirstgame.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	String userName;
	String userPwd;
	EditText et_userName;
	EditText et_userPwd;
	Handler handle=null;
	

/*	class MyHandler extends Handler {
	public MyHandler() {
		         }
		
	public MyHandler(Looper L) {
		             super(L);
		        }*/
	// �������̵߳�handler�����ܴ����߳��д�����������
		

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et_userName = (EditText) findViewById(R.id.et_userName);
		et_userPwd = (EditText) findViewById(R.id.et_userPwd);

		handle = new Handler() {
			
			public void handleMessage(android.os.Message msg) {
				
				if (msg.what == 1) {
				Toast.makeText(getApplicationContext(), "���ӳɹ�", 0)
					.show();
					//System.out.println("��������");
					Bundle b=msg.getData();
					String data=b.getString("���������");
					//System.out.println(data);
					nextActivity(data);

				}

				if (msg.what == 2) { // ����ʧ��
					Toast.makeText(getApplicationContext(), "����ʧ�ܣ�������ά����", 1)
							.show();

				}

			}
		};
	}

	public void nextActivity(String data) {
		//Toast.makeText(getApplicationContext(), data, 1).show();
		
		// ���е�¼�ıȶԣ��Լ���¼����ת
		String[] split = data.split(",");
		//Log.d(data, data);
		String name = split[0];
		//System.out.println(name);
		String pwd = split[1];
		//System.out.println(pwd);
		if (name.equals(userName) && pwd.equals(userPwd)) {
			// ��¼�ɹ�������ת
			Toast.makeText(getApplicationContext(), "������û�����ȷ", 0).show();
			Intent intent = new Intent(this, GameActivity.class);
			startActivity(intent);
		} else {// ��¼ʧ��
			Toast.makeText(getApplicationContext(), "��������û�������", 1).show();
		}
		
	}

	public void login(View v) {
		String regexName = "[1-9][0-9]{10}";
		// ����6��20
		String regexPwd = "[a-zA-Z0-9@\\.]{6,20}";
		userName = et_userName.getText().toString().trim();
		userPwd = et_userPwd.getText().toString().trim();
		if (userName.isEmpty() || userPwd.isEmpty()) {
			Toast.makeText(getApplicationContext(), "�˺Ż�����Ϊ��", 0).show();
		} else if (Pattern.matches(regexName, userName) == false
				|| Pattern.matches(regexPwd, userPwd) == false) {
			Toast.makeText(getApplicationContext(), "�����Ϲ���", 0).show();
			Toast.makeText(getApplicationContext(),
					"" + Pattern.matches(regexName, userName), 0).show();
			Toast.makeText(getApplicationContext(),
					"" + Pattern.matches(regexPwd, userPwd), 0).show();

		} else {
			Toast.makeText(getApplicationContext(), "���Ϲ���", 0).show();
			getData();
			Toast.makeText(getApplicationContext(), "getDatayunxing wan ", 0).show();
		}
	}
	public void click2(View v){
		Intent intent=new Intent(this,GameActivity.class);
		startActivity(intent);
	}
	// ȥ�������õ�������
	public void getData() {

		new Thread() {
			public void run() {
				// ����url����
				try {

					URL url = new URL(
							"http://192.168.186.1:8080/javaWeb/ServletDemo2");
					HttpURLConnection urlConnection = (HttpURLConnection) url
							.openConnection();
					urlConnection.setRequestMethod("GET");
					// �������ӳ�ʱ��ʱ��
					urlConnection.setConnectTimeout(5000);
					int code = urlConnection.getResponseCode();
					if (code == 200) {
						//Toast.makeText(getApplicationContext(), "���ӳɹ�", 1).show();
						// ˵�����ӳɹ�
						System.out.print("���ӳɹ�" + code);
						// ��ȡ�ӷ�������������������
						InputStream in = urlConnection.getInputStream();
						BufferedReader bfr = new BufferedReader(
								new InputStreamReader(in));
						StringBuilder sb = new StringBuilder();
						String result;
						
						while ((result = bfr.readLine()) != null) {
							sb.append(result+"\n");
							
						}
						in.close();
						System.out.println("�õ��������ǣ�"+sb.toString());
						String s=sb.toString().trim();
						Message msg = Message.obtain();
						msg.what = 1;
						Bundle b=new Bundle();
						b.putString("���������", s);
						
						msg.setData(b);
						
						
						
						handle.sendMessage(msg);
		
						Log.d("���ӳɹ�", "chenggong ");
					} else {// ����ʧ��
						
						Message msg = handle.obtainMessage();
						msg.what = 2;
						handle.sendMessage(msg);
						// msg.what=CONNECTFAIL;
						// handle.sendMessage(msg);
						Log.d("����ʧ��", "hehe");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}.start();
	}

}
		