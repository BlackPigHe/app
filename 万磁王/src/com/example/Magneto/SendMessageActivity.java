package com.example.Magneto;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SendMessageActivity extends Activity {
	private EditText mobileText;
	private EditText contentText;
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_sendmessage);
	        //获取电话号文本框
	        mobileText = (EditText)this.findViewById(R.id.mobile);
	        //获取短信内容文本框
	        contentText = (EditText)this.findViewById(R.id.content);
	    
	        //获取按钮
	        Button button = (Button)this.findViewById(R.id.button);
	        
	        button.setOnClickListener(new View.OnClickListener(){
	public void onClick(View v) {
	//获取电话号码
	String moblie = mobileText.getText().toString();
	//短信内容
	String content = contentText.getText().toString();
	//获取短信管理器
	SmsManager smsManager = SmsManager.getDefault();
	//如果汉字大于70个
	if(content.length() > 70){
	//返回多条短信
	List<String> contents = smsManager.divideMessage(content);
	for(String sms:contents){
	//1.目标地址：电话号码 2.原地址：短信中心服号码3.短信内容4.意图
	smsManager.sendTextMessage(moblie, null, sms, null, null);
	}
	}else{
	smsManager.sendTextMessage(moblie, null, content, null, null);
	}
	//吐西1.应用上下文2.提示内容3.时间
	Toast.makeText(SendMessageActivity.this,"发送成功",2).show();
	}
	        });
	        
	    }
}
