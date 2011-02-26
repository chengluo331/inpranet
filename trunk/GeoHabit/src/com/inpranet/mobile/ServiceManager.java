package com.inpranet.mobile;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ServiceManager extends BroadcastReceiver {
	/** Tag pour le log */
	private static final String TAG = "ServiceManager";

	@Override
	public void onReceive(Context context, Intent intent) {
		if( intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
			Log.d(TAG, "Boot completed");
			ComponentName comp = new ComponentName(context.getPackageName(), LocalizationService.class.getName());
			ComponentName service = context.startService(new Intent().setComponent(comp));
			if (service == null){
				Log.e(TAG, "Could not start service " + comp.toString());
			}
		} else {
			Log.e(TAG, "Received unexpected intent " + intent.toString());   
		}
	}			
}

