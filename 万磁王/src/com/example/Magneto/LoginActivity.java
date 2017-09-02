package com.example.Magneto;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	String userName;
	String userPwd;
	EditText et_userName;
	EditText et_userPwd;

	/*
	 * private Handler handle=new Handler(){ public void
	 * handleMessage(android.os.Message msg){ if(msg.what==1){
	 * Toast.makeText(getApplicationContext(), "�˺Ż�����Ϊ��", 1).show(); //String
	 * data2=(String) msg.obj; //tv_result.setText(data2); }else
	 * if(msg.what==2){ //tv_result.setText("������");
	 * Toast.makeText(getApplicationContext(), "���е�¼���߼�", 1).show(); }
	 * 
	 * } };
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		et_userName = (EditText) findViewById(R.id.et_userName);
		et_userPwd = (EditText) findViewById(R.id.et_userPwd);

	}
	SQLiteDatabase sqLiteDatabase;
	String password;

	// Message msg=new Message();
	public void login(View v) {
		userName = et_userName.getText().toString().trim();
		userPwd = et_userPwd.getText().toString().trim();
		if (userName.isEmpty() || userPwd.isEmpty()) {
			Toast.makeText(getApplicationContext(), "�˺Ż�����Ϊ��", 0).show();
		}else {
			String path="data/data/com.example.Magneto/databases/login_db.db";
			
			SQLiteDatabase sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(path,null);
			//MyOpenHelpe myOpenHelpe = new MyOpenHelpe(getApplicationContext());
            //sqLiteDatabase = myOpenHelpe.getWritableDatabase();
            
            
		Cursor cursor = sqLiteDatabase.query("info", new String[] { 
					"userName", "password" }, "userName=?",
					new String[] { userName }, null, null, null);
			//String sql="select * from info where userName=?";
			//Cursor cursor = sqLiteDatabase.rawQuery(sql, (String[]) new Object[]{"admin"});
			while (cursor.moveToNext()) {
				String name = cursor.getString(cursor
						.getColumnIndex("userName"));
				password = cursor.getString(cursor.getColumnIndex("password"));
			}
			if (password.equals(userPwd)) {
				Toast.makeText(getApplicationContext(), "���е�¼���߼�", 0).show();
				//Intent intent = new Intent(this, FirstPageActivity.class);
				Intent intent = new Intent(this, FirstPageActivity.class);
				startActivity(intent);
			}else{
				Toast.makeText(getApplicationContext(), "���ݿ����ݲ�ƥ��", 0).show();
			}
		}
	}

	// ȥ�������õ�������
	public void submitData() {
		new Thread() {
			public void run() {
				// ����url����
				try {

					URL url = new URL("");
					HttpURLConnection urlConnection = (HttpURLConnection) url
							.openConnection();
					urlConnection.setRequestMethod("GET");
					// �������ӳ�ʱ��ʱ��
					urlConnection.setConnectTimeout(5000);
					int code = urlConnection.getResponseCode();
					if (code == 200) {
						// ˵�����ӳɹ�
						System.out.print("���ӳɹ�" + code);
						// ��ȡ�ӷ�������������������
						InputStream in = urlConnection.getInputStream();
						// ����TextView��Ҫ�ַ�����������Ҫ��������ת��Ϊ�ַ��������԰Ѵ˷�����װһ��
						// final String data = streamStringUtils.utils(in);
						// ����ui�����߳���

						/*
						 * msg.what=CONNECTSUCCESS; msg.obj=data;
						 * handle.sendMessage(msg);
						 */

					} else {
						Message msg = new Message();
						// msg.what=CONNECTFAIL;
						// handle.sendMessage(msg);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}.start();
	}
}
