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
 * ������Service �÷�����ں�̨һֱ����һ��������͸���Ĵ��� 
 *  
 * @author Administrator 
 *  
 */  
public class FloatService extends Service {  
  
    //private static final int UPDATE_PIC = 0x100;  
   // private int statusBarHeight;// ״̬���߶�  
   // private View view;// ͸������  
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
       
        //ͨ��WindowManager�������Ĵ�����ӵ���Ļ
        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       
        mView = (ViewGroup) mInflater.inflate(R.layout.activity_main, null);
        mView.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()){
			       
		        //�˴�ʵ����������϶� www.2cto.com
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
        //ʹ�ô�����Ҫ�������һ��Ȩ��
        mLayoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
       
        mWindowManager.addView(mView, mLayoutParams);
        listenActivity();
    }
   
    private void listenActivity(){
        new Thread(){
            public void run(){
                List<RunningTaskInfo> taskInfos;
                //�жϳ����Ƿ�������
                //ľ���ҵ��ȽϺõļ�����������������һ����ķ���
                //��Ϊ����ӦҪ�󲻸ߣ�����ÿ100������һ��
                //�к÷����������ԣ���ʤ��л
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