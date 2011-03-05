package com.inpranet.mobile;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

/**
 * Service de localisation qui s'exécute au démarrage de mobile
 * il enregistre les déplacements de l'utilisateur et les envoie au système central
 * @author yqzhou
 *
 */
public class LocalizationService extends Service {

	/** Tag pour le log */
	private static final String TAG = "LocalizationService";
	
	/** Le timer pour déclencher l'opération */
	//private Timer timer = new Timer();
	
	/** Le temps minimum de maj de position */
	private long minTime;
	
	/** La distance minimum de maj de position */
	private float minDistance;

	/** Objet LocationManager qui fournit l'accès au service de localisation du système Android */
	private LocationManager mgr;
	
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void loadParameters() {
		
	}
	
	/**
	 * Enregistrer la dernière position obtenue de l'utilisateur
	 * si la connexion internet est disponible, on l'envoie au système central 
	 * sinon on la sauvegarde en local pour envoyer utérieurement
	 * @param newLocation la dernière position utilisateur
	 */
	private void registerLocation(Location newLocation) {
		double latitude = newLocation.getLatitude();
		double longitude = newLocation.getLongitude();
		
		// Si connexion internet établie
		if(isOnline()) {
			// TODO Envoie de données
		} // Sinon
		else {
			// TODO Sauvegarde en local
		}
	}
	
	public boolean isOnline() {
		 ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		 return cm.getActiveNetworkInfo().isConnectedOrConnecting();
	}
	/**
	 * Procédure appelée lorsque le processus du service est créé
	 */
	@Override
	public void onCreate(){
		super.onCreate();
		Log.i(TAG, "Localization service started...");
		
		loadParameters();
		
		mgr = (LocationManager) getSystemService(LOCATION_SERVICE);
		
		LocationListener gpsListener = new LocationListener() {
			
			public void onLocationChanged(Location newLocation) {
				Log.d(TAG, "Location changed");
				registerLocation(newLocation);
				// TODO Auto-generated method stub
				
			}

			public void onProviderDisabled(String provider) {
				Log.d(TAG, "GPS disabled");
				// TODO Auto-generated method stub
				
			}

			public void onProviderEnabled(String provider) {
				Log.d(TAG, "GPS enabled");
				// TODO Auto-generated method stub
				
			}

			public void onStatusChanged(String provider, int status,
					Bundle extras) {
				// TODO Auto-generated method stub
				
			}
			
		};
		
		minTime = 0;
		minDistance = 0;
		
		// TODO Si GPS autorise dans le paramétrage
		mgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minDistance, gpsListener);
		
		// Planifier le timer qui appelle la procédure UpdatePosition à une période
		//timer.schedule(new UpdatePosition(), 0, 5000);
	}
	
	/**
	 * La classe qui hérite de TimerTask contenant la procédure appelée par le timer
	 * @author yqzhou
	 *
	 */
	/*class UpdatePosition extends TimerTask {
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
	}*/
	
	/**
	 * Méthode appelée lorsque le service est éteint
	 */
	@Override
	public void onDestroy(){
		//timer.cancel();
		super.onDestroy();
		Log.i(TAG, "Localization service ended...");
	}



	
}
