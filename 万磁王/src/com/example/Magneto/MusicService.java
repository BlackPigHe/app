package com.example.Magneto;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;

public class MusicService extends Service {
	MediaPlayer mediaPlayer;
	String path;
	@Override
	public IBinder onBind(Intent intent) {
		Bundle bundle = intent.getExtras();
		path = bundle.getString("path");
		return new MyIBinder();
	}
	/*public void onStart(Intent intent,int startId){
		super.onStart(intent, startId);
	}*/

	public void onCreate(){
		
		
		mediaPlayer = new MediaPlayer();
		super.onCreate();
	}
	public void onDestroy(){
		
		super.onDestroy();
	}
	//≤•∑≈“Ù¿÷µƒ
	public void play(){
		try {
			mediaPlayer.setDataSource(path);
			mediaPlayer.prepare();
			mediaPlayer.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void zanting(){
		mediaPlayer.pause();
	}
	public void replay(){
		mediaPlayer.start();
	}
	public class  MyIBinder extends Binder implements Iservice{

		@Override
		public void callPlay() {
			play();
		}

		@Override
		public void callZanting() {
			zanting();
		}

		@Override
		public void callReplay() {
			replay();
		}
		
	}
}
