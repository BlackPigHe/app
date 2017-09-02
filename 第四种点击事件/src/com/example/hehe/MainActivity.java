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
			        //加载布局
			        setContentView(R.layout.activity_main);
			        //找到所需的控件
			        et_Num= (EditText) findViewById(R.id.editText1);
			        Button btn=(Button) findViewById(R.id.button1);
			        //给button添加点击事件第四种
			        btn.setOnClickListener(this);
			    }
			    
			
			 
			 
			



			public void click4(View v) {
				// TODO 自动生成的方法存根
				System.out.println("按钮被点击");
				String num=et_Num.getText().toString().trim();
				if("".equals(num)){
					Toast.makeText(MainActivity.this, "拨号不能为空", Toast.LENGTH_LONG).show();
				}
				else{
					System.out.print("不为空");
				}
				
				Intent intent=new Intent();
				//意图分为动作和对象
				//创建动作
				intent.setAction(Intent.ACTION_CALL);
				//对象
				intent.setData(Uri.parse("tel:"+num));
				//开启意图
				startActivity(intent);
			}








			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
				
			}








		
			}
			