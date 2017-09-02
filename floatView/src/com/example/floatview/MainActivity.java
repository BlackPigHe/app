package com.example.floatview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取启动按钮  
        Button start = (Button)findViewById(R.id.start_id);  
        //获取移除按钮  
        Button remove = (Button)findViewById(R.id.remove_id);  
        //绑定监听  
        start.setOnClickListener(new OnClickListener()   
        {  
              
            @Override  
            public void onClick(View v)   
            {  
                Intent intent = new Intent(MainActivity.this, FxService.class);  
                //启动FxService  
                startService(intent);  
                finish();  
            }  
        });  
          
        remove.setOnClickListener(new OnClickListener()   
        {  
              
            @Override  
            public void onClick(View v)   
            {  
                //uninstallApp("com.phicomm.hu");  
                Intent intent = new Intent(MainActivity.this, FxService.class);  
                //终止FxService  
                stopService(intent);  
            }  
        });  
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
