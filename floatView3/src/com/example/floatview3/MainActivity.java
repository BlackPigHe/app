package com.example.floatview3;

import android.app.Activity;  
import android.content.Intent;  
import android.os.Bundle;  
  
 
  
public class MainActivity extends Activity {  
  
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        Intent intent = new Intent(MainActivity.this, FloatService.class);  
        startService(intent);  
        finish();  
    }  
}  