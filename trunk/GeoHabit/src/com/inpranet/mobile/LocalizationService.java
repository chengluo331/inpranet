package com.inpranet.mobile;

import java.util.Timer;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class LocalizationService extends Service {

	/** Tag pour le log */
	private static final String TAG = "LocalizationService";
	
	/** Le timer pour déclencher l'opération */
	private Timer timer = new Timer();
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Méthode appelée lorsque le processus du service est créé
	 */
	@Override
	public void onCreate(){
		super.onCreate();
		Log.i(TAG, "Localization service started...");
	}
	
	/**
	 * Méthode appelée lorsque le service est éteint
	 */
	@Override
	public void onDestroy(){
		super.onDestroy();
		Log.i(TAG, "Localization service ended...");
	}

}
