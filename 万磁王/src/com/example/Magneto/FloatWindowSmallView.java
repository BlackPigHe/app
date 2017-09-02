package com.example.Magneto;
import com.example.Magneto.R;
import java.lang.reflect.Field;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FloatWindowSmallView extends LinearLayout {  
	  
    public static int viewWidth;  
    public static int viewHeight;  
     private static int statusBarHeight;  
    private WindowManager windowManager;  
    private WindowManager.LayoutParams mParams;   
    private float xInScreen;  
    private float yInScreen;  
    private float xDownInScreen;  
    private float yDownInScreen;  
    private float xInView;  
    private float yInView;  
  
    public FloatWindowSmallView(Context context) {  
        super(context);  
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);  
        LayoutInflater.from(context).inflate(R.layout.float_window_small, this);  
        View view = findViewById(R.id.small_window_layout);  
        viewWidth = view.getLayoutParams().width;  
        viewHeight = view.getLayoutParams().height;  
    
        view.setOnTouchListener(new OnTouchListener(){
        	
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				switch (event.getAction()) {  
		        case MotionEvent.ACTION_DOWN:  
		            // 手指按下时记录必要数据,纵坐标的值都需要减去状态栏高度  
		            xInView = event.getX();  
		            yInView = event.getY();  
		            xDownInScreen = event.getRawX();  
		            yDownInScreen = event.getRawY() - getStatusBarHeight();  
		            xInScreen = event.getRawX();  
		            yInScreen = event.getRawY() - getStatusBarHeight();  
		            break;  
		        case MotionEvent.ACTION_MOVE:  
		            xInScreen = event.getRawX();  
		            yInScreen = event.getRawY() - getStatusBarHeight();  
		            // 手指移动的时候更新小悬浮窗的位置  
		            updateViewPosition();  
		            break;  
		        case MotionEvent.ACTION_UP:  
		            // 如果手指离开屏幕时，xDownInScreen和xInScreen相等，且yDownInScreen和yInScreen相等，则视为触发了单击事件。  
		            if (xDownInScreen == xInScreen && yDownInScreen == yInScreen) {  
		                Toast.makeText(getContext(), "点击了万磁王", 1).show();
		            	
		                openBigWindow();  
		            }  
		            break;  
		        default:  
		            break;  
		        }  
		        return true;  
				
			}
        	
        });
        
        TextView percentView = (TextView) findViewById(R.id.percent);  
        ImageView tupian = (ImageView) findViewById(R.id.float_1);
        tupian.setMaxHeight(100);
        tupian.setMaxWidth(100);
        percentView.setText(MyWindowManager.getUsedPercentValue(context));  
    }  
  
  /*  @Override  
    public boolean onTouchEvent(MotionEvent event) {  
        
    }  */
  
    /** 
     * 将小悬浮窗的参数传入，用于更新小悬浮窗的位置。 
     *  
     * @param params 
     *            小悬浮窗的参数 
     */  
    public void setParams(WindowManager.LayoutParams params) {  
        mParams = params;  
    }  
  
    /** 
     * 更新小悬浮窗在屏幕中的位置。 
     */  
    private void updateViewPosition() {  
        mParams.x = (int) (xInScreen - xInView);  
        mParams.y = (int) (yInScreen - yInView);  
        windowManager.updateViewLayout(this, mParams);  
    }  
  
    /** 
     * 打开大悬浮窗，同时关闭小悬浮窗。 
     */  
    private void openBigWindow() {  
        MyWindowManager.createBigWindow(getContext());  
        MyWindowManager.removeSmallWindow(getContext());  
    }  
  
    /** 
     * 用于获取状态栏的高度。 
     *  
     * @return 返回状态栏高度的像素值。 
     */  
    private int getStatusBarHeight() {  
        if (statusBarHeight == 0) {  
            try {  
                Class<?> c = Class.forName("com.android.internal.R$dimen");  
                Object o = c.newInstance();  
                Field field = c.getField("status_bar_height");  
                int x = (Integer) field.get(o);  
                statusBarHeight = getResources().getDimensionPixelSize(x);  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        return statusBarHeight;  
    }  
    

}