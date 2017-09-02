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
				// ����һ�����������߼�
				jindu();

			} else if (msg.what == 1) {
				Toast.makeText(getApplicationContext(), "���ӳɹ�", 1).show();
				// holder= (SurfaceHolder) msg.obj;

			} else if (msg.what == 2) {
				// û������
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
						// ����url����
						try {
							// ������Ϣ��handle
							Message msg = new Message();
							msg.what = 0;
							handle.sendMessage(msg);

							// vv.setVideoPath(path);
							URL url = new URL(path);
							HttpURLConnection urlConnection = (HttpURLConnection) url
									.openConnection();

							urlConnection.setRequestMethod("GET");
							// �������ӳ�ʱ��ʱ��
							urlConnection.setConnectTimeout(2000);
							int code = urlConnection.getResponseCode();

							if (code == 200) {
								// ˵�����ӳɹ�
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
								System.out.println("���ӳɹ�" + code);
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
										"Ŀǰû��·��������ݲ�����", 1).show();
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
		build.setTitle("����");
		build.setMessage("����������ʧ��");
		build.setPositiveButton("ȷ��", null);
		build.setNegativeButton("ȡ�� ", null);
		build.show();

	}

	// ��װ�������ķ���
	public void jindu() {
		final ProgressDialog pd = new ProgressDialog(this);
		pd.setMessage("���ڶ�ȡ������Դ");
		// ����һ�½���������ʽ
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
