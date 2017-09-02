package com.example.rpcalc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class TestActivity extends Activity {
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_second);
	        TextView tv_name = (TextView) findViewById(R.id.tv_name);
	        TextView tv_sex = (TextView) findViewById(R.id.tv_sex);
	        TextView tv_info = (TextView) findViewById(R.id.tv_info);
	        
	        Intent intent = getIntent();
	         String name = intent.getStringExtra("name");
	         tv_name.setText(name);
	         int sex = intent.getIntExtra("sex", 0);
	         System.out.println("dayin"+sex);
	         switch(sex){
	         case 1 :
	        	 tv_sex.setText("男");
	        	 break;
	         case  2 :
	        	 tv_sex.setText("女");
	        	 break;
	         case  3 :
	        	 tv_sex.setText("人妖");
	        	 break;
	         case -1:
	        	 Toast.makeText(getApplicationContext(), "请输入性别", 1).show();
	        	 break;
	         }
	         
	        //写个textView的算法
	         byte[] bytes = name.getBytes();
	         int toal=0;
	         for(byte b:bytes){
	        	 int number=b&0xff;
	        	 toal+=number;
	         }
	         if("贺子江".equals(name)){
	        	 tv_info.setText("此乃主人名讳，，哈哈");
	         }else if(sex==3){
	        	 tv_info.setText("人妖死开...");
	         }
	         else{
	        	 int score=Math.abs(toal)%100;
		         if(score>90){
		        	 tv_info.setText("天啦！你不是人！你是神！！！");
		        
		         }else if(score>70){
		        	 tv_info.setText("你有较好的人品..继续保持..");
		       
		         }else if(score>60){
		        	 tv_info.setText("你的人品勉勉强强..要自己好自为之..");
		        
		         }else if(score>40){
		        	 tv_info.setText("你貌似应该三岁就偷看隔壁大妈洗澡的吧...");
		        
		         }else{
		        	 tv_info.setText("是我不好...不应该跟你谈人品问题的...");
		        	
		         }
	         }
	         
	        	
	        
	    }
}
