package com.example.floatview3;

import android.app.Service;  
import android.content.Intent;  
import android.graphics.PixelFormat;  
import android.graphics.Rect;  
import android.os.Handler;  
import android.os.IBinder;  
import android.os.Looper;  
import android.os.Message;  
import android.view.Gravity;  
import android.view.LayoutInflater;  
import android.view.MotionEvent;  
import android.view.View;  
import android.view.View.OnClickListener;  
import android.view.View.OnTouchListener;  
import android.view.WindowManager;  
import android.view.WindowManager.LayoutParams;  
import android.widget.Button;  
import android.widget.TextView;  
import android.widget.Toast;  
  
import com.example.floatview3.R;  

  
/** 
 * ������Service �÷�����ں�̨һֱ����һ��������͸���Ĵ��� 
 *  
 * @author Administrator 
 *  
 */  
public class FloatService extends Service {  
  
    private static final int UPDATE_PIC = 0x100;  
    private int statusBarHeight;// ״̬���߶�  
    private View view;// ͸������  
    private TextView text = null;  
    private Button hideBtn = null;  
    private Button updateBtn = null;  
    private HandlerUI handler = null;  
    private Thread updateThread = null;  
    private boolean viewAdded = false;// ͸�������Ƿ��Ѿ���ʾ  
    private boolean viewHide = false; // ��������  
    private WindowManager windowManager;  
    private WindowManager.LayoutParams layoutParams;  
  
    @Override  
    public IBinder onBind(Intent arg0) {  
        // TODO Auto-generated method stub  
        return null;  
    }  
  
    @Override  
    public void onCreate() {  
        // TODO Auto-generated method stub  
        super.onCreate();  
        createFloatView();  
    }  
  
    @Override  
    public void onStart(Intent intent, int startId) {  
        // TODO Auto-generated method stub  
        super.onStart(intent, startId);  
       
        viewHide = false;  
        refresh();  
    }  
  
    @Override  
    public void onDestroy() {  
        // TODO Auto-generated method stub  
        super.onDestroy();  
        removeView();  
    }  
  
    /** 
     * �ر������� 
     */  
    public void removeView() {  
        if (viewAdded) {  
            windowManager.removeView(view);  
            viewAdded = false;  
        }  
    }  
  
    private void createFloatView() {  
        handler = new HandlerUI();  
        UpdateUI update = new UpdateUI();  
        updateThread = new Thread(update);  
        updateThread.start(); // �����߳�  
  
        view = LayoutInflater.from(this).inflate(R.layout.activity_main, null);  
        text = (TextView) view.findViewById(R.id.usage);  
        hideBtn = (Button) view.findViewById(R.id.hideBtn);  
        updateBtn = (Button) view.findViewById(R.id.updateBtn);  
        windowManager = (WindowManager) this.getSystemService(WINDOW_SERVICE);  
        /* 
         * LayoutParams.TYPE_SYSTEM_ERROR����֤������������View�����ϲ� 
         * LayoutParams.FLAG_NOT_FOCUSABLE:�ø����������ý��㣬�����Ի���϶� 
         * PixelFormat.TRANSPARENT��������͸�� 
         */  
        layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,  
                LayoutParams.WRAP_CONTENT, LayoutParams.TYPE_SYSTEM_ERROR,  
                LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSPARENT);  
        // layoutParams.gravity = Gravity.RIGHT|Gravity.BOTTOM; //��������ʼ�����½���ʾ  
        layoutParams.gravity = Gravity.LEFT | Gravity.TOP;  
  
        /** 
         * ���������ƶ��¼� 
         */  
        view.setOnTouchListener(new OnTouchListener() {  
            float[] temp = new float[] { 0f, 0f };  
  
            public boolean onTouch(View v, MotionEvent event) {  
                layoutParams.gravity = Gravity.LEFT | Gravity.TOP;  
                int eventaction = event.getAction();  
                switch (eventaction) {  
                case MotionEvent.ACTION_DOWN: // �����¼�����¼����ʱ��ָ����������XY����ֵ  
                    temp[0] = event.getX();  
                    temp[1] = event.getY();  
                    break;  
  
                case MotionEvent.ACTION_MOVE:  
                    refreshView((int) (event.getRawX() - temp[0]),  
                            (int) (event.getRawY() - temp[1]));  
                    break;  
  
                }  
                return true;  
            }  
        });  
  
        hideBtn.setOnClickListener(new OnClickListener() {  
  
            @Override  
            public void onClick(View v) {  
                // TODO Auto-generated method stub  
                viewHide = true;  
                removeView();  
                System.out.println("----------hideBtn");  
            }  
        });  
  
        updateBtn.setOnClickListener(new OnClickListener() {  
  
            @Override  
            public void onClick(View v) {  
                // TODO Auto-generated method stub  
                Toast.makeText(getApplicationContext(), "you click UpdateBtn",  
                        Toast.LENGTH_SHORT).show();  
                
            }  
        });  
    }  
  
    /** 
     * ˢ�������� 
     *  
     * @param x 
     *            �϶����X������ 
     * @param y 
     *            �϶����Y������ 
     */  
    private void refreshView(int x, int y) {  
        // ״̬���߶Ȳ�������ȡ����Ȼ�õ���ֵ��0  
        if (statusBarHeight == 0) {  
            View rootView = view.getRootView();  
            Rect r = new Rect();  
            rootView.getWindowVisibleDisplayFrame(r);  
            statusBarHeight = r.top;  
        }  
  
        layoutParams.x = x;  
        // y���ȥ״̬���ĸ߶ȣ���Ϊ״̬�������û����Ի��Ƶ����򣬲�Ȼ�϶���ʱ���������  
        layoutParams.y = y - statusBarHeight;// STATUS_HEIGHT;  
        refresh();  
    }  
  
    /** 
     * ������������߸��������� �����������û�������� ����Ѿ�����������λ�� 
     */  
    private void refresh() {  
        // ����Ѿ�����˾�ֻ����view  
        if (viewAdded) {  
            windowManager.updateViewLayout(view, layoutParams);  
        } else {  
            windowManager.addView(view, layoutParams);  
            viewAdded = true;  
        }  
    }  
  
    /** 
     * ������Ϣ�ʹ�����Ϣ 
     *  
     * @author Administrator 
     *  
     */  
    class HandlerUI extends Handler {  
        public HandlerUI() {  
  
        }  
  
        public HandlerUI(Looper looper) {  
            super(looper);  
        }  
  
        /** 
         * ������Ϣ 
         */  
        @Override  
        public void handleMessage(Message msg) {  
            // TODO Auto-generated method stub  
            // �����յ�����Ϣ�ֱ���  
            if (msg.what == UPDATE_PIC) {  
                text.setText("hehe");  
                if (!viewHide)  
                    refresh();  
            } else {  
                super.handleMessage(msg);  
            }  
  
        }  
  
    }  
  
    /** 
     * ��������������Ϣ 
     *  
     * @author Administrator 
     *  
     */  
    class UpdateUI implements Runnable {  
  
        @Override  
        public void run() {  
            // TODO Auto-generated method stub  
            // ���û���жϾ�һֱ����  
            while (!Thread.currentThread().isInterrupted()) {  
                Message msg = handler.obtainMessage();  
                msg.what = UPDATE_PIC; // ������Ϣ��ʶ  
                handler.sendMessage(msg);  
                // ����1s  
                try {  
                    Thread.sleep(1000);  
                } catch (InterruptedException e) {  
                    // TODO Auto-generated catch block  
                    e.printStackTrace();  
                }  
            }  
        }  
    }  
}  