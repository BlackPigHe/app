package com.example.randomnumber;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	Button btn,btn2,btn3;TextView tv,tv2,show;int num,getNum;
	EditText et;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn = (Button) findViewById(R.id.btn);
		btn2 = (Button) findViewById(R.id.btn2);
		btn3 = (Button) findViewById(R.id.btn3);
		tv = (TextView) findViewById(R.id.tv);
		tv2 = (TextView) findViewById(R.id.tv2);
		show = (TextView) findViewById(R.id.show);
		et=(EditText) findViewById(R.id.et);
		
		System.out.println("onStart");
		System.out.println("onStop");
		System.out.println("onDestroy");
		System.out.println("onRestart");
		System.out.println("onResume");
		System.out.println("onPause");
		
		
	}
	//判断得到的数字
	public void puanduan(View v){
		System.out.print(tv.getText().toString());
		num=Integer.parseInt(tv.getText().toString());
		//num=10;
		getNum=Integer.parseInt(et.getText().toString());
		//getNum=10;
		if(getNum>num){
			show.setText("你输入的数字偏大");
		}else if(getNum<num){
			show.setText("你输入的数字偏小");
		}else{
			show.setText("恭喜你猜对了");
		}
		//tv.setText("haha");
	}
	//生成一个随机数
	public void produce(View v){
		
		Random rand=new Random();
		num=rand.nextInt(100);
		tv.setText(""+num);
		tv.setVisibility(android.view.View.INVISIBLE);
		
	}
	//显示一个随机数
	public void click(View v){
		
		
		tv.setVisibility(android.view.View.VISIBLE);
	}
	/*private OnClickListener click() {
		// TODO Auto-generated method stub
		//Random rand=new Random();
		//num=rand.nextInt(100);
		System.out.print("点击了");
		tv.setText(num);
		return null;
		
	}*/
	
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
