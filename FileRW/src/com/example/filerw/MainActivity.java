package com.example.filerw;

import javax.security.auth.PrivateCredentialPermission;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View.OnClickListener MyListener = null;
        Button btn,btn2;
        EditText ed1,ed2;
         btn=(Button) findViewById(R.id.btn);
         btn2=(Button) findViewById(R.id.btn2);
         ed1=(EditText)findViewById(R.id.ed1);
         ed2=(EditText)findViewById(R.id.ed2);
         
         
         btn.setOnClickListener(MyListener);
         
         MyListener=new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch (v.getId()) {
				case R.id.btn:
					//Toast.makeText(this, "这是btn的方法", 1).show();
					System.out.println("点击了btn");
					break;
				case R.id.btn2:
					System.out.println("点击了btn2");
					break;

				default:
					break;
				}
			}
		};
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
