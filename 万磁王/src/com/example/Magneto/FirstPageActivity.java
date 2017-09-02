package com.example.Magneto;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ext.SatelliteMenu;
import android.view.ext.SatelliteMenuItem;
import android.view.ext.SatelliteMenu.SateliteClickedListener;
import android.widget.Button;
import android.widget.Toast;

public class FirstPageActivity extends Activity implements OnClickListener {
	SatelliteMenu menu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_firstpage);
		Intent intent=new Intent(this,FloatWindowService.class);
		startService(intent);
		Button btn_sy = (Button) findViewById(R.id.btn1);
		Button btn_lxr = (Button) findViewById(R.id.btn2);
		Button btn_me = (Button) findViewById(R.id.btn3);
		btn_sy.setOnClickListener(this);
		btn_lxr.setOnClickListener(this);
		btn_me.setOnClickListener(this);
		menu = (SatelliteMenu) findViewById(R.id.menu);
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
			FragmentManager fragmentManager = getFragmentManager();
			FragmentTransaction beginTransaction = fragmentManager
					.beginTransaction();
			public void eventOccured(int id) {
				switch (id) {
				case 1:
					System.out.println("1");//打电话
					Toast.makeText(getApplicationContext(), "打电话", 1)
							.show();
					setCallPhone();
					break;
				case 2:
					System.out.println("2");//视频图标
					Toast.makeText(getApplicationContext(),"看视频", 1)
							.show();
					setVideoIntent();
					break;
				case 3:
					System.out.println("3");//发短信
					Toast.makeText(getApplicationContext(),"发短信", 1)
							.show();
					setSendMessage();
					break;
				case 4:
					System.out.println("4");// 媒体
					setMusicIntent();
					
					Toast.makeText(getApplicationContext(),"听音乐", 1)
							.show();
				break;
				case 5:
					System.out.println("5");// 图片解析
					Toast.makeText(getApplicationContext(), "查看图片", 1)
							.show();
					setBitmap();
				break;
				case 6:
					System.out.println("6");// 浏览器
					Toast.makeText(getApplicationContext(), "浏览器", 1)
							.show();
					setWap();
					break;
				}

			}
			
		});
	}
	protected void setWap() {
		Intent intent=new Intent(this,wapActivity.class);
		startActivity(intent);
	}
	protected void setSendMessage() {
		Intent intent=new Intent(this,SendMessageActivity.class);
		startActivity(intent);
	}
	protected void setBitmap() {
		Intent intent=new Intent(this,ParserImageActivity.class);
		startActivity(intent);
	}
	protected void setCallPhone() {
		Intent intent=new Intent(this,CallPhoneActivity.class);
		startActivity(intent);
	}
	
	private void setVideoIntent() {
		Intent intent=new Intent(this,VideoActivity.class);
		startActivity(intent);
	}
	private void setMusicIntent(){
		Intent intent=new Intent(this,MusicPlayActivity.class);
		startActivity(intent);
	}
	// Set from XML, possible to programmatically set
	// float distance = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
	// 170, getResources().getDisplayMetrics());
	// menu.setSatelliteDistance((int) distance);
	// menu.setExpandDuration(500);
	// menu.setCloseItemsOnClick(false);
	// menu.setTotalSpacingDegree(60);
	
	//下面的东西打上注释
	public void onClick(View v) {
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction beginTransaction = fragmentManager
				.beginTransaction();
		// 判断具体的按钮事件
		switch (v.getId()) {
		case R.id.btn1:
			beginTransaction.replace(R.id.ll_layout, new shouyeFragment());
			break;
		case R.id.btn2:
			beginTransaction.replace(R.id.ll_layout, new crawlerFragment());
			break;
		case R.id.btn3:
			beginTransaction.replace(R.id.ll_layout, new MyFragment());
			break;
		}
		beginTransaction.commit();
	}
}
