package com.example.hehe;

			
import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.click4.R;
			
			
		public class MainActivity extends ActionBarActivity implements OnClickListener {
			EditText  et_Num;
			    @Override
			    protected void onCreate(Bundle savedInstanceState) {
			        super.onCreate(savedInstanceState);
			        //���ز���
			        setContentView(R.layout.activity_main);
			        //�ҵ�����Ŀؼ�
			        et_Num= (EditText) findViewById(R.id.editText1);
			        Button btn=(Button) findViewById(R.id.button1);
			        //��button��ӵ���¼�������
			        btn.setOnClickListener(this);
			    }
			    
			
			 
			 
			



			public void click4(View v) {
				// TODO �Զ����ɵķ������
				System.out.println("��ť�����");
				String num=et_Num.getText().toString().trim();
				if("".equals(num)){
					Toast.makeText(MainActivity.this, "���Ų���Ϊ��", Toast.LENGTH_LONG).show();
				}
				else{
					System.out.print("��Ϊ��");
				}
				
				Intent intent=new Intent();
				//��ͼ��Ϊ�����Ͷ���
				//��������
				intent.setAction(Intent.ACTION_CALL);
				//����
				intent.setData(Uri.parse("tel:"+num));
				//������ͼ
				startActivity(intent);
			}








			@Override
			public void onClick(View arg0) {
				// TODO �Զ����ɵķ������
				
			}








		
			}
			