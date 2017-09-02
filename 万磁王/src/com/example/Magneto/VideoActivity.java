package com.example.Magneto;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer.OnPreparedListener;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class VideoActivity extends Activity {
	VideoView vv;
	String path;
	EditText video_et;
	SurfaceView video_sv;
	MediaPlayer mediaPlayer;
	SurfaceHolder holder;
	private Handler handle = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0) {
				// 弹出一个进度条的逻辑
				jindu();

			} else if (msg.what == 1) {
				Toast.makeText(getApplicationContext(), "连接成功", 1).show();
				// holder= (SurfaceHolder) msg.obj;

			} else if (msg.what == 2) {
				// 没连到网
				queren();
			}

		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video);
		video_et = (EditText) findViewById(R.id.video_et);
		Button video_play = (Button) findViewById(R.id.video_play);
		// video_sv = (SurfaceView) findViewById(R.id.video_sv);
		if (!LibsChecker.checkVitamioLibs(this)) {
			return;
		}
		final VideoView vv = (VideoView) findViewById(R.id.vv);
		path = video_et.getText().toString().trim();
		vv.setVideoPath(path);

		vv.setMediaController(new MediaController(this));
		video_play.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new Thread() {
					public void run() {
						// 创建url对象
						try {
							// 发送消息给handle
							Message msg = new Message();
							msg.what = 0;
							handle.sendMessage(msg);

							// vv.setVideoPath(path);
							URL url = new URL(path);
							HttpURLConnection urlConnection = (HttpURLConnection) url
									.openConnection();

							urlConnection.setRequestMethod("GET");
							// 设置连接超时的时间
							urlConnection.setConnectTimeout(2000);
							int code = urlConnection.getResponseCode();

							if (code == 200) {
								// 说明连接成功
								/*
								 * new Thread(new Runnable(){
								 * 
								 * @Override public void run() {
								 * SystemClock.sleep(1000); try {
								 * 
								 * holder = video_sv.getHolder();
								 * mediaPlayer.setDisplay(holder);
								 * 
								 * } catch (Exception e) { e.printStackTrace();
								 * } } }).start();
								 */

								Message msg1 = new Message();
								msg1.what = 1;
								handle.sendMessage(msg1);
								System.out.println("连接成功" + code);
								vv.setOnPreparedListener(new OnPreparedListener() {

									@Override
									public void onPrepared(
											io.vov.vitamio.MediaPlayer mp) {
										vv.start();
									}

								});

							} else if(code!=200) {
								Message msg2 = new Message();
								msg2.what = 2;
								handle.sendMessage(msg2);
								Toast.makeText(getApplicationContext(),
										"目前没网路，或该数据不存在", 1).show();
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				}.start();

			}

		});

	}

	public void queren() {
		AlertDialog.Builder build = new Builder(this);
		build.setTitle("警告");
		build.setMessage("你网络连接失败");
		build.setPositiveButton("确认", null);
		build.setNegativeButton("取消 ", null);
		build.show();

	}

	// 封装进度条的方法
	public void jindu() {
		final ProgressDialog pd = new ProgressDialog(this);
		pd.setMessage("正在读取网络资源");
		// 设置一下进度条的样式
		pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		pd.show();
		new Thread(new Runnable() {
			@Override
			public void run() {
				pd.setMax(100);
				for (int i = 0; i < 100; i++) {
					pd.setProgress(i);
					SystemClock.sleep(10);
				}
				pd.dismiss();
			}
		}).start();
	}
}
