package com.example.Magneto;
import java.util.ArrayList;
import java.util.List;

import com.example.Magneto.R;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ext.SatelliteMenu;
import android.view.ext.SatelliteMenuItem;
import android.view.ext.SatelliteMenu.SateliteClickedListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.view.ext.SatelliteMenu;
import android.view.ext.SatelliteMenuItem;
import android.view.ext.SatelliteMenu.SateliteClickedListener;

public class FloatWindowBigView extends LinearLayout{  
	SatelliteMenu menu;
    public static int viewWidth;  
    public static int viewHeight;  
  
    public FloatWindowBigView(final Context context) {  
        super(context);  
        LayoutInflater.from(context).inflate(R.layout.float_window_big, this);  
        View view = findViewById(R.id.big_window_layout); 
       
		menu = (SatelliteMenu) findViewById(R.id.menu2);
		List<SatelliteMenuItem> items = new ArrayList<SatelliteMenuItem>();
		items.add(new SatelliteMenuItem(3, R.drawable.ic_1));//发短信
		items.add(new SatelliteMenuItem(6, R.drawable.ic_3));// 浏览器
		items.add(new SatelliteMenuItem(4, R.drawable.ic_4));// 媒体
		items.add(new SatelliteMenuItem(5, R.drawable.ic_5));// 图片解析
		items.add(new SatelliteMenuItem(2, R.drawable.ic_6));// 视频图标
		items.add(new SatelliteMenuItem(1, R.drawable.ic_2));// 打电话
		/*
		 * SatelliteMenuItem satelliteMenuItem = new SatelliteMenuItem(4,
		 * R.drawable.ic_1); View view =
		 * findViewById(satelliteMenuItem.getId()); view.setOnClickListener(new
		 * OnClickListener(){
		 * 
		 * @Override public void onClick(View v) {
		 * Toast.makeText(getApplicationContext(),R.drawable.ic_1 , 1).show(); }
		 * 
		 * });
		 */
		// items.add(new SatelliteMenuItem(5, R.drawable.sat_item));
		menu.addItems(items);
		menu.setOnItemClickedListener(new SateliteClickedListener() {
			
			public void eventOccured(int id) {
				switch (id) {
				case 1:
					System.out.println("1");//打电话
					Toast.makeText(context, "打电话", 1)
							.show();
					setCallPhone();
					break;
				case 2:
					System.out.println("2");//视频图标
					Toast.makeText(context, "看视频", 1)
							.show();
					setVideoIntent();
					break;
				case 3:
					System.out.println("3");// 发短信
					Toast.makeText(context, "发短信", 1)
							.show();
					setSendMessage();
					break;
				case 4:
					System.out.println("4");// 媒体
					setMusicIntent();
					
					Toast.makeText(context,"听音乐", 1)
							.show();
					
				break;
				case 5:
					System.out.println("5");// 图片解析 
					Toast.makeText(context, "查看图片", 1)
							.show();
					setParserImage();
				break;
				case 6:
					System.out.println("6");// 浏览器
					Toast.makeText(context, "浏览器", 1)
							.show();
					setWap();
					break;
				}
			}
			private void setWap() {
				Intent intent=new Intent(context,wapActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent);
			}
			private void setParserImage() {
				Intent intent=new Intent(context,ParserImageActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent);
			}
			private void setSendMessage() {
				Intent intent=new Intent(context,SendMessageActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent);
			}
			private void setMusicIntent() {
				Intent intent=new Intent(context,MusicPlayActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent);
			}

			private void setVideoIntent() {
				Intent intent=new Intent(context,VideoActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    		context.startActivity(intent);
			}
			protected void setCallPhone() {
				Intent intent=new Intent(context,CallPhoneActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent);
			} 
		});
        viewWidth = view.getLayoutParams().width;  
        viewHeight = view.getLayoutParams().height;  
        Button close = (Button) findViewById(R.id.close);  
        Button back = (Button) findViewById(R.id.back);  
        close.setOnClickListener(new OnClickListener() {  
            @Override  
            public void onClick(View v) {  
                // 点击关闭悬浮窗的时候，移除所有悬浮窗，并停止Service  
                MyWindowManager.removeBigWindow(context);  
                MyWindowManager.removeSmallWindow(context);  
                Intent intent = new Intent(getContext(), FloatWindowService.class);  
                context.stopService(intent);  
            }  
        });  
        back.setOnClickListener(new OnClickListener() {  
            @Override  
            public void onClick(View v) {  
                // 点击返回的时候，移除大悬浮窗，创建小悬浮窗  
                MyWindowManager.removeBigWindow(context);  
                MyWindowManager.createSmallWindow(context);  
            }  
        });  
    }

	
   
}
