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
	        	 tv_sex.setText("��");
	        	 break;
	         case  2 :
	        	 tv_sex.setText("Ů");
	        	 break;
	         case  3 :
	        	 tv_sex.setText("����");
	        	 break;
	         case -1:
	        	 Toast.makeText(getApplicationContext(), "�������Ա�", 1).show();
	        	 break;
	         }
	         
	        //д��textView���㷨
	         byte[] bytes = name.getBytes();
	         int toal=0;
	         for(byte b:bytes){
	        	 int number=b&0xff;
	        	 toal+=number;
	         }
	         if("���ӽ�".equals(name)){
	        	 tv_info.setText("�����������䣬������");
	         }else if(sex==3){
	        	 tv_info.setText("��������...");
	         }
	         else{
	        	 int score=Math.abs(toal)%100;
		         if(score>90){
		        	 tv_info.setText("�������㲻���ˣ������񣡣���");
		        
		         }else if(score>70){
		        	 tv_info.setText("���нϺõ���Ʒ..��������..");
		       
		         }else if(score>60){
		        	 tv_info.setText("�����Ʒ����ǿǿ..Ҫ�Լ�����Ϊ֮..");
		        
		         }else if(score>40){
		        	 tv_info.setText("��ò��Ӧ�������͵�����ڴ���ϴ��İ�...");
		        
		         }else{
		        	 tv_info.setText("���Ҳ���...��Ӧ�ø���̸��Ʒ�����...");
		        	
		         }
	         }
	         
	        	
	        
	    }
}
