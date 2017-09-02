package com.example.listview;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(new MyAdapter());
       
        String str3="w;8!2w.8"+'"'+"v";
        String str2="";
       
		for(int i=0;i<10;i++){
        	str2+=(char)((int)(str3.charAt(i)^87));
        }
		Toast.makeText(this, str2, 1).show();
    }
    
    private class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO 自动生成的方法存根
			return 5;
		}

		@Override
		public Object getItem(int position) {
			// TODO 自动生成的方法存根
			return null;
		}

		@Override
		public long getItemId(int position) {
			
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view;
			
			if(convertView==null){
				//创建新的对象view
				view = View.inflate(getApplicationContext(), R.layout.hehe, null);
				
			}else{
				//复用历史缓存对象
				view=convertView;
			}
			return view;
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
