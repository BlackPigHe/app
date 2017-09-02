package com.example.floatview2;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.example.floatview2.R;


public class MainActivity extends Activity {  
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_main);  
        Button startFloatWindow = (Button) findViewById(R.id.btn_show);  
        startFloatWindow.setOnClickListener(new OnClickListener() {  
            @Override  
            public void onClick(View arg0) {  
                Intent intent = new Intent(MainActivity.this, FloatWindowService.class);  
                startService(intent);  
               // finish();  
            }  
        });  
    }  
}  