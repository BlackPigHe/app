package com.example.phoneListence;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class PhoneService extends Service {
	private MediaRecorder recorder;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	public void onCreate() {
		TelephonyManager tm = (TelephonyManager) this.getSystemService(Service.TELEPHONY_SERVICE);
		tm.listen(new MyListener(), PhoneStateListener.LISTEN_CALL_STATE);
		super.onCreate();

	}

	private class MyListener extends PhoneStateListener {
		public void onCallStateChanged(int state, String incomingNumber){
			switch(state){
			case TelephonyManager.CALL_STATE_IDLE:
				System.out.println("电话空闲");
				if(recorder!=null){
					recorder.stop();
					 recorder.reset();   // You can reuse the object by going back to setAudioSource() step
					 recorder.release(); // Now the object cannot be reused
				}
				
				break;
			case TelephonyManager.CALL_STATE_OFFHOOK:
				System.out.println("电话接起来了");
				 recorder.start();   // Recording is now started
				break;
			case TelephonyManager.CALL_STATE_RINGING:
				System.out.println("电话铃响了");
				//准备一个录音器
//				MediaRecorder mediaRecorder = new MediaRecorder();
				recorder = new MediaRecorder();
				 recorder.setAudioSource(MediaRecorder.AudioSource.VOICE_CALL);
				 recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
				 recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
				 recorder.setOutputFile("mnt/sdcard/luyin.3gp");
				 try {
					recorder.prepare();
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			
				 

				break;
			}
			super.onCallStateChanged(state, incomingNumber);
		}
	}

	public void onDestory() {
		super.onDestroy();
		System.out.println("fuwuguanbi le ");
	}
}
