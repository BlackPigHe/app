package com.example.floatview3;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Service;  
import android.content.Context;
import android.content.Intent;  
import android.os.Handler;  
import android.os.IBinder;  
import android.view.Gravity;  
import android.view.LayoutInflater;  
import android.view.MotionEvent;  
import android.view.View;  
import android.view.View.OnTouchListener;  
import android.view.ViewGroup;
import android.view.WindowManager;  
import android.widget.TextView;  
  
import com.example.floatview4.R;  

  
/** 
 * 悬浮窗Service 该服务会在后台一直运行一个悬浮的透明的窗体 
 *  
 * @author Administrator 
 *  
 */  
public class FloatService extends Service {  
  
    //private static final int UPDATE_PIC = 0x100;  
   // private int statusBarHeight;// 状态栏高度  
   // private View view;// 透明窗体  
   // private TextView text = null;  
   // private Button hideBtn = null;  
   // private Button updateBtn = null;  
      
  
    private WindowManager mWindowManager;  
    private WindowManager.LayoutParams mLayoutParams;  
    ActivityManager mActivityManager;
    LayoutInflater mInflater;
    ViewGroup mView;
    TextView mTextView;
    int x,y,xOffset,yOffset,robotMsg;
	long startTime;
	Handler mHandler=new Handler();
    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
       
        //通过WindowManager将浮动的窗口添加到屏幕
        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       
        mView = (ViewGroup) mInflater.inflate(R.layout.activity_main, null);
        mView.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()){
			       
		        //此处实现在桌面的拖动 www.2cto.com
		        case MotionEvent.ACTION_DOWN:
		            xOffset = (int) event.getRawX();
		            yOffset = (int) event.getRawY();
		            x = mLayoutParams.x;
		            y = mLayoutParams.y;
		            startTime = System.currentTimeMillis();
		            break;
		        case MotionEvent.ACTION_MOVE:
		            if ( System.currentTimeMillis() - startTime < 1000 )break;
		            mLayoutParams.x = x + (int) event.getRawX() - xOffset;
		            mLayoutParams.y = y + (int) event.getRawY() - yOffset;
		            mWindowManager.updateViewLayout(mView, mLayoutParams);
		            break;
		        case MotionEvent.ACTION_UP:
		            if (robotMsg%2 == 0){
		                mTextView.setText("Hello Boy!");
		            }else{
		                mTextView.setText("Hello Gril!!");
		            }
		            robotMsg ++;           
		            break;
		        }
		        return true;
				
			}
        	
        });
        mView.setVisibility(View.GONE);
        mTextView = (TextView) mView.findViewById(R.id.flowspeed);
       
        mLayoutParams = new WindowManager.LayoutParams();
        mLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mLayoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mLayoutParams.alpha = (float) 0.99;
        mLayoutParams.gravity =  Gravity.LEFT | Gravity.TOP;
        mLayoutParams.x = 10;
        mLayoutParams.y = 10;
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        //使用此类型要额外添加一个权限
        mLayoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
       
        mWindowManager.addView(mView, mLayoutParams);
        listenActivity();
    }
   
    private void listenActivity(){
        new Thread(){
            public void run(){
                List<RunningTaskInfo> taskInfos;
                //判断程序是否处于桌面
                //木有找到比较好的监听方法，所以用了一个最笨的方法
                //因为对相应要求不高，所以每100毫秒检查一次
                //有好方法的请留言，不胜感谢
                try {
                    while(true){
                        sleep(100);
                        taskInfos = mActivityManager.getRunningTasks (1);
                        if(taskInfos.get(0).topActivity.getPackageName().equals("com.android.launcher") ){
                            mHandler.sendEmptyMessage(View.VISIBLE);
                        }else{
                            mHandler.sendEmptyMessage(View.GONE);
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
   
    @Override
    public void onDestroy() {
        mWindowManager.removeView(mView);
        super.onDestroy();
    }


@Override
public IBinder onBind(Intent intent) {
	// TODO Auto-generated method stub
	return null;
}
}