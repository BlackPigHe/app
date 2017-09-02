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
        //1.1从服务器中取数据，封装到javabean中,info.java
        //1.2从服务器中取数据
        getData();
        
        
    }

	private void getData() {
		new Thread(){
			public void run(){
				try {
					URL url=new URL("http://192.168.43.244:8080/access.xml");
					//用来接收数据
					HttpURLConnection uc = (HttpURLConnection) url.openConnection();
					uc.setConnectTimeout(5000);
					uc.setRequestMethod("GET");
					int code = uc.getResponseCode();
					if(code==200){
						//说明请求成功
						System.out.println("请求成功");
						InputStream in = uc.getInputStream();
						//解析xml文件，封装一个方法XmlParserUtils
						System.out.println("hehe");
						
						list = XmlParserUtils.parserXml(in);
						System.out.println("打印.."+list.size());
						Message msg = Message.obtain();
						msg.obj=list;
						handle.sendMessage(msg);
						//将数据显示到ListView上,需要Adapter适配器
						/*runOnUiThread(new Runnable(){
							public void run(){
								lv.setAdapter(new MyAdapter());
							}
						});*/
					}else{
						//说明请求失败
					}
					
				} catch (Exception e) {
					// TODO 自动生成的 catch 块
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
				//复用历史缓存
				view=convertView;
			}
			return view;
		}
		
	}
}
