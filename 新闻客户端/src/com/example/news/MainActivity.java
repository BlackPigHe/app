package com.example.news;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends Activity {
	List<Info> list=null;
	ListView lv;
	
	private Handler handle=new Handler(){
		public void handlerMessage(android.os.Message msg){
			list=(List<Info>) msg.obj;
			lv.setAdapter(new MyAdapter());
		}
	};
			
		
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);
        //1.1�ӷ�������ȡ���ݣ���װ��javabean��,info.java
        //1.2�ӷ�������ȡ����
        getData();
        
        
    }

	private void getData() {
		new Thread(){
			public void run(){
				try {
					URL url=new URL("http://192.168.43.244:8080/access.xml");
					//������������
					HttpURLConnection uc = (HttpURLConnection) url.openConnection();
					uc.setConnectTimeout(5000);
					uc.setRequestMethod("GET");
					int code = uc.getResponseCode();
					if(code==200){
						//˵������ɹ�
						System.out.println("����ɹ�");
						InputStream in = uc.getInputStream();
						//����xml�ļ�����װһ������XmlParserUtils
						System.out.println("hehe");
						
						list = XmlParserUtils.parserXml(in);
						System.out.println("��ӡ.."+list.size());
						Message msg = Message.obtain();
						msg.obj=list;
						handle.sendMessage(msg);
						//��������ʾ��ListView��,��ҪAdapter������
						/*runOnUiThread(new Runnable(){
							public void run(){
								lv.setAdapter(new MyAdapter());
							}
						});*/
					}else{
						//˵������ʧ��
					}
					
				} catch (Exception e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
			}
			
		}.start();
		

	}
	private class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
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
				view=View.inflate(getApplicationContext(), R.layout.item, null);
				//ImageView iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
				TextView  tv_title= (TextView) view.findViewById(R.id.tv_title);
				TextView tv_pubDate = (TextView) view.findViewById(R.id.tv_pubDate);
				TextView tv_link = (TextView) view.findViewById(R.id.tv_link);
				TextView tv_description = (TextView) view.findViewById(R.id.tv_description);
				TextView tv_category = (TextView) view.findViewById(R.id.tv_category);
				TextView tv_author = (TextView) view.findViewById(R.id.tv_author);
				
				
				tv_title.setText(list.get(position).getTitle());
				tv_pubDate.setText(list.get(position).getPubDate());
				tv_link.setText(list.get(position).getLink());
				tv_description.setText(list.get(position).getDescription());
				tv_category.setText(list.get(position).getCategory());
				tv_author.setText(list.get(position).getAuthor());
				
			}else{
				//������ʷ����
				view=convertView;
			}
			return view;
		}
		
	}
}
