package com.example.rpcalc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class MainActivity extends Activity {
	EditText et;
	RadioGroup rg;
	int sex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText) findViewById(R.id.et);
        et.setHint("请输入姓名");
       rg = (RadioGroup) findViewById(R.id.rg);
        RadioButton rb_1 = (RadioButton) findViewById(R.id.rb_1);
        RadioButton rb_2= (RadioButton) findViewById(R.id.rb_2);
        RadioButton rb_3 = (RadioButton) findViewById(R.id.rb_3);
    }
    public void click(View v){
    	Intent intent =new Intent(this,TestActivity.class);
    	String name = et.getText().toString().trim();
    	if(name.isEmpty()){
    		Toast.makeText(getApplicationContext(), "名字不能为空", 1).show();
    	}else{
    		int buttonId = rg.getCheckedRadioButtonId();
        	
        	sex=0;
        	switch(buttonId){
        	case R.id.rb_1:
        		sex=1;
        		break;
        	case R.id.rb_2:
        		sex=2;
        		break;
        	case R.id.rb_3:
        		sex=3;
        		break;
        	}
        	intent.putExtra("name", name);
        	intent.putExtra("sex", sex);
        	
        	startActivity(intent);
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
