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
	        //��ȡ�绰���ı���
	        mobileText = (EditText)this.findViewById(R.id.mobile);
	        //��ȡ���������ı���
	        contentText = (EditText)this.findViewById(R.id.content);
	    
	        //��ȡ��ť
	        Button button = (Button)this.findViewById(R.id.button);
	        
	        button.setOnClickListener(new View.OnClickListener(){
	public void onClick(View v) {
	//��ȡ�绰����
	String moblie = mobileText.getText().toString();
	//��������
	String content = contentText.getText().toString();
	//��ȡ���Ź�����
	SmsManager smsManager = SmsManager.getDefault();
	//������ִ���70��
	if(content.length() > 70){
	//���ض�������
	List<String> contents = smsManager.divideMessage(content);
	for(String sms:contents){
	//1.Ŀ���ַ���绰���� 2.ԭ��ַ���������ķ�����3.��������4.��ͼ
	smsManager.sendTextMessage(moblie, null, sms, null, null);
	}
	}else{
	smsManager.sendTextMessage(moblie, null, content, null, null);
	}
	//����1.Ӧ��������2.��ʾ����3.ʱ��
	Toast.makeText(SendMessageActivity.this,"���ͳɹ�",2).show();
	}
	        });
	        
	    }
}
