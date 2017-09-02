package com.example.phoneListence;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Intent intent1=new Intent(context,PhoneService.class);
		context.sendBroadcast(intent1);
		context.startService(intent1);
	}

}
