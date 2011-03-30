package com.inpranet.mobile;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * La classe qui permet de lancer les service au démarrage de mobile
 * @author yqzhou
 *
 */
public class ServiceManager extends BroadcastReceiver {
	/** Tag pour le log */
	private static final String TAG = "ServiceManager";

	/**
	 * Procédure appelée lorsqu'on reçoit un intent
	 * S'il s'agit de la fin du démarrage de l'OS, on lance les services de localisation
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		// Si l'intent est la fin du démarrage de l'OS
		if( intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
			Log.d(TAG, "Boot completed");
			
			// Lancer le service de localisation
			ComponentName comp = new ComponentName(context.getPackageName(), LocalizationService.class.getName());
			ComponentName service = context.startService(new Intent().setComponent(comp));
			
			// Si erreur
			if (service == null){
				Log.e(TAG, "Could not start service " + comp.toString());
			}
		} else {
			Log.e(TAG, "Received unexpected intent " + intent.toString());   
		}
	}			
}

