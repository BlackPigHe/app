package com.example.Magneto;


import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class zhuceActivity extends Activity {
	EditText ed_zhuceName;
	EditText ed_zhucePwd;
	EditText ed_zhucePwd2;
	 String zhuceName,zhucePwd,zhucePwd2;
	@Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_zhuce);
	        ed_zhuceName = (EditText) findViewById(R.id.ed_zhuceName);
	        ed_zhucePwd = (EditText) findViewById(R.id.ed_zhucePwd);
	        ed_zhucePwd2 = (EditText) findViewById(R.id.ed_zhucePwd2);
	       
	 }
		public void submit(View v){
			zhuceName = ed_zhuceName.getText().toString().trim();
	        zhucePwd = ed_zhucePwd.getText().toString().trim();
	        zhucePwd2 = ed_zhucePwd2.getText().toString().trim();
			 if(zhuceName.isEmpty()||zhucePwd.isEmpty()){
		        	Toast.makeText(getApplicationContext(), "用户名或密码为空", 0).show();
		        }else if(!zhucePwd.equals(zhucePwd2)){
		        	Toast.makeText(getApplicationContext(),zhucePwd , 0).show();
		        	Toast.makeText(getApplicationContext(), zhucePwd2, 0).show();
		        	Toast.makeText(getApplicationContext(), "两次密码输入的不一样", 0).show();
		        }else{
		        	
		        	//拿到封装的方法
		        	//getServiceltData.submitData();
		        	//MyOpenHelper myOpenHelper = new MyOpenHelper(getApplicationContext());
		            //sqLiteDatabase = myOpenHelper.getWritableDatabase();
		            //sqLiteDatabase.execSQL("insert into info (name,password) values(zhuceName,zhucePwd)");
		        	String path="data/data/com.example.Magneto/databases/login_db.db";
					SQLiteDatabase sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(path,null);
					String sql="insert into info (userName,password) values"+"("+zhuceName+","+zhucePwd+")";
					sqLiteDatabase.execSQL(sql);
		        	Toast.makeText(getApplicationContext(), "注册成功", 0).show();
		        	Intent intent=new Intent(this,LoginActivity.class);
		        	startActivity(intent);
		        }
		}
	}
