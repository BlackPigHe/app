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
        
      //����
        data_list = new ArrayList<String>();
        data_list.add("�˳�ѧԺ����");
        data_list.add("�ٶ�");
        data_list.add("�ȸ�");
        data_list.add("�ѹ�");
        //������
        arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list);
        //������ʽ
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //����������
        spinner.setAdapter(arr_adapter);
      //����¼�Spinner�¼�����  
        spinner.setOnItemSelectedListener(new SpinnerSelectedListener());
         
        //����Ĭ��ֵ
        spinner.setVisibility(View.VISIBLE);
      
    }
    class SpinnerSelectedListener implements OnItemSelectedListener{
    	@Override
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                long arg3) {
    		switch(arg2){
    		case 0://�˳�ѧԺ
    			wap_ed.setText("http://www.ycu.edu.cn");
    			break;
    		case 1://�ٶ�
    			wap_ed.setText("http://www.baidu.com");
    			break;
    		case 2://�ȸ�
    			wap_ed.setText("http://www.google.com");
    			break;
    		case 3://�ѹ� 
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
