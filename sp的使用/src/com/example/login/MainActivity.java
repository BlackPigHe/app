package com.example.login;

import java.util.Map;

import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.example.sp.R;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends ActionBarActivity {
	EditText et_name;
	EditText et_pwd;
	CheckBox check;
	SharedPreferences sp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		et_name = (EditText) findViewById(R.id.usename);
		et_pwd = (EditText) findViewById(R.id.password);
		check = (CheckBox) findViewById(R.id.ischeck);
		sp = getSharedPreferences("userInfo", 0);//回帮助生成一个xml文件
		//读数据
		String nm = sp.getString("name", "");
		String pd = sp.getString("pwd", "");
		sp.getBoolean("check",false);
		et_pwd.setText(pd);
		et_name.setText(nm);
		check.setChecked(true);
		
	}

	@SuppressLint("ShowToast")
	public void login(View v) {
		String name = et_name.getText().toString().trim();
		String pwd = et_pwd.getText().toString().trim();

		if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)) {
			Toast.makeText(MainActivity.this, "账号或密码不能为空", 1).show();
		} else {
			// Toast.makeText(MainActivity.this, "ok", 1).show();
			if (check.isChecked()) {
				if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
					Toast.makeText(getApplicationContext(), "sd卡可用", 0).show();
					//TODO 添加保存name password 写入
					
					//1先sp.edit
					Editor edit = sp.edit();
					//2
					edit.putString("name", name);
					edit.putString("pwd", pwd);
					edit.putBoolean("check", true);
					//3
					edit.commit();
				}
				else{
					Toast.makeText(getApplicationContext(), "sd卡不可用", 1).show();
				}
				
			} else {
				Toast.makeText(MainActivity.this, "请勾选记住密码", 0).show();
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
