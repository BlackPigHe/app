package com.example.Magneto;
import java.util.ArrayList;
import java.util.List;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class wapActivity extends Activity {
	private static final String TAG = "ActivityDemo"; 
	private Spinner spinner;
	private List<String> data_list;
	private ArrayAdapter<String> arr_adapter;
	private String Myurl;
	EditText wap_ed ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wap);
        spinner = (Spinner) findViewById(R.id.spinner);
        wap_ed = (EditText) findViewById(R.id.wap_ed);
        
      //数据
        data_list = new ArrayList<String>();
        data_list.add("运城学院官网");
        data_list.add("百度");
        data_list.add("谷歌");
        data_list.add("搜狗");
        //适配器
        arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        spinner.setAdapter(arr_adapter);
      //添加事件Spinner事件监听  
        spinner.setOnItemSelectedListener(new SpinnerSelectedListener());
         
        //设置默认值
        spinner.setVisibility(View.VISIBLE);
      
    }
    class SpinnerSelectedListener implements OnItemSelectedListener{
    	@Override
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                long arg3) {
    		switch(arg2){
    		case 0://运城学院
    			wap_ed.setText("http://www.ycu.edu.cn");
    			break;
    		case 1://百度
    			wap_ed.setText("http://www.baidu.com");
    			break;
    		case 2://谷歌
    			wap_ed.setText("http://www.google.com");
    			break;
    		case 3://搜狗 
    			wap_ed.setText("http://www.sogou.com");
    			break;
    		}
            
        }
    	@Override
        public void onNothingSelected(AdapterView<?> arg0) {
        }

		
    }
    public void click1(View v){
    	Myurl = wap_ed.getText().toString().trim();
    	Intent intent = new Intent();        
        intent.setAction("android.intent.action.VIEW");    
        Uri content_url = Uri.parse(Myurl);   
        intent.setData(content_url);  
        startActivity(intent);
    }
        @Override  
        protected void onResume() {  
            super.onResume(); 
           
            Log.e(TAG, "start onResume~~~");  
        }  

        @Override  
        protected void onStop() {  
            super.onStop();  
            this.finish();
            Log.e(TAG, "start onStop~~~");  
}
        }
