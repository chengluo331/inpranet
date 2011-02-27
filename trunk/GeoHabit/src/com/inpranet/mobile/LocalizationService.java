package com.inpranet.mobile;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

/**
 * Service de localisation qui s'exécute au démarrage de mobile
 * il enregistre les déplacements de l'utilisateur et les envoie au système central
 * @author yqzhou
 *
 */
public class LocalizationService extends Service implements LocationListener {

	/** Tag pour le log */
	private static final String TAG = "LocalizationService";
	
	/** Le timer pour déclencher l'opération */
	private Timer timer = new Timer();
	
	/** La période d'exécution du timer */
	private long period = 5000;

	/** Objet LocationManager qui fournit l'accès au service de localisation du système Android */
	private LocationManager mgr;
	
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Procédure appelée lorsque le processus du service est créé
	 */
	@Override
	public void onCreate(){
		super.onCreate();
		Log.i(TAG, "Localization service started...");
		
		// Planifier le timer qui appelle la procédure UpdatePosition à une période
		timer.schedule(new UpdatePosition(), 0, period);
	}
	
	/**
	 * La classe qui hérite de TimerTask contenant la procédure appelée par le timer
	 * @author yqzhou
	 *
	 */
	class UpdatePosition extends TimerTask {
		@Override
		public void run() {
			Log.i(TAG,"Update position...");
			
			mgr = (LocationManager) getSystemService(LOCATION_SERVICE);
						
			Criteria criteria = new Criteria();
			String best = mgr.getBestProvider(criteria, true);
			Log.i(TAG, "The best provider is: " + best);
			
			// Récupérer la dernière position utilisateur
			Location location = mgr.getLastKnownLocation(best);
			if (location != null) {
				String longitude = String.valueOf(location.getLongitude());
				String latitude = String.valueOf(location.getLatitude());
				Log.i(TAG, "Current location: " + longitude 
						+ ',' + latitude);
			}else {
				Log.e(TAG, "location null");
			}
		}
	}
	
	/**
	 * Méthode appelée lorsque le service est éteint
	 */
	@Override
	public void onDestroy(){
		super.onDestroy();
		Log.i(TAG, "Localization service ended...");
	}

	public long getPeriod() {
		return period;
	}

	public void setPeriod(long period) {
		this.period = period;
	}

	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
	}

	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	
}
