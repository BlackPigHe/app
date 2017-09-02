package com.example.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
	SQLiteDatabase sqLiteDatabase;
	List<People>  list;
	TextView tv_name ;
	TextView tv_phone;
	ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        MyOpenHelper myOpenHelper = new MyOpenHelper(getApplicationContext());
        sqLiteDatabase = myOpenHelper.getWritableDatabase();
        
    }
    public void click1(View v){
    	 sqLiteDatabase.execSQL("insert into info (name,phone) values('hezijiang','17835395677')");
    }
    public void click2(View v){
   	 sqLiteDatabase.execSQL("delete from info where name=('hezijiang')");
   }
    public void click3(View v){
   	 sqLiteDatabase.execSQL("update info set phone ='110000' where name=('zhangshan')");
   }
    public void click4(View v){
   	 
   	Cursor cursor = sqLiteDatabase.rawQuery("select * from info", null);
   	People people = new People();
   	list=new ArrayList<People>();
   	
   	if(cursor!=null&&cursor.getCount()>0){
   		while(cursor.moveToNext()){
   			//columnIndex�����е�����
   			String name = cursor.getString(1);
   			String phone = cursor.getString(2);
   			//�����ݷ�װ��javabean��
   			
   			people.setName(name);
   			people.setPhone(phone);
   		
            
   			//��javabean�е����ݷ�װ��������
   			list.add(people);
   			
   		}
   	}
   	
   	
   	
   	lv = (ListView) findViewById(R.id.lv);
	lv.setAdapter(new MyAdapter());
   	 
   }
    public class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO �Զ����ɵķ������
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO �Զ����ɵķ������
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO �Զ����ɵķ������
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view;
			if(convertView==null){
				view = View.inflate(getApplicationContext(), R.layout.item, null);
			}else{
				view=convertView;
			}
			//�ҵ��ؼ�����ʾ����
			tv_name = (TextView)view.findViewById(R.id.tv_name);
		   	tv_phone = (TextView) view.findViewById(R.id.tv_phone);
		   	//��list�����ݷŵ��ؼ���
		   	People p = list.get(position);
		   	tv_name.setText(p.getName());
		   	tv_phone.setText(p.getPhone());
		   	
		   
			return view;
		}
		 
		
		//ȡ����
		 
		
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
