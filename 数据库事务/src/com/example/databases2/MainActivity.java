package com.example.databases2;


import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends Activity {
	SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyOpenHelpe myOpenHelpe = new MyOpenHelpe(getApplicationContext());
        database = myOpenHelpe.getWritableDatabase();
    }
    public void click(View v){
    	database.beginTransaction();
    	   try {
    	     database.execSQL("update info set money =money-1000  where name=?",new Object[]{"张三"});
    	     int i=10/0;
    	     
    	     database.execSQL("update info set money =money+1000  where name=?",new Object[]{"李四"});
    	     database.setTransactionSuccessful();
    	   }catch(Exception e){
    		   Toast.makeText(MainActivity.this, "服务器繁忙", 1).show();
    	   }
    	   finally {
    	     database.endTransaction();
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
