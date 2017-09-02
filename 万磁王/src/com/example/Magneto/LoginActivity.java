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
	 * Toast.makeText(getApplicationContext(), "账号或密码为空", 1).show(); //String
	 * data2=(String) msg.obj; //tv_result.setText(data2); }else
	 * if(msg.what==2){ //tv_result.setText("无连接");
	 * Toast.makeText(getApplicationContext(), "进行登录的逻辑", 1).show(); }
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
			Toast.makeText(getApplicationContext(), "账号或密码为空", 0).show();
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
				Toast.makeText(getApplicationContext(), "进行登录的逻辑", 0).show();
				//Intent intent = new Intent(this, FirstPageActivity.class);
				Intent intent = new Intent(this, FirstPageActivity.class);
				startActivity(intent);
			}else{
				Toast.makeText(getApplicationContext(), "数据库数据不匹配", 0).show();
			}
		}
	}

	// 去服务器拿到流数据
	public void submitData() {
		new Thread() {
			public void run() {
				// 创建url对象
				try {

					URL url = new URL("");
					HttpURLConnection urlConnection = (HttpURLConnection) url
							.openConnection();
					urlConnection.setRequestMethod("GET");
					// 设置连接超时的时间
					urlConnection.setConnectTimeout(5000);
					int code = urlConnection.getResponseCode();
					if (code == 200) {
						// 说明连接成功
						System.out.print("连接成功" + code);
						// 获取从服务器传回来的流数据
						InputStream in = urlConnection.getInputStream();
						// 设置TextView需要字符串，所以需要把流数据转换为字符串，可以把此方法封装一下
						// final String data = streamStringUtils.utils(in);
						// 更新ui到主线程中

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
