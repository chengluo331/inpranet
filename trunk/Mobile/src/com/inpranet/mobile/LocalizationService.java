package com.inpranet.mobile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

/**
 * Service de localisation qui s'exécute au démarrage de mobile il enregistre
 * les déplacements de l'utilisateur et les envoie au système central
 * 
 * @author yqzhou, cluo
 * 
 */
public class LocalizationService extends Service {

	/** Tag pour le log */
	private static final String TAG = "LocalizationService";

	private static final int TIME_OUT = 2000;

	private static final String URI_WS_GEOPOS_BASE = "http://10.0.2.2:8080/service/public/geo";
		
	/** Le gps listener */
	GPSListener gpsListener = new GPSListener();

	/** Le timer */
	Timer timer = new Timer();

	/** L'id d'utilisateur qui utilise le service */
	private long mSessionID;

	/** Le temps minimum de maj de position en milliseconde */
	private long minTime;

	/** Le temps maximum entre deux maj de position en milliseconde */
	private long maxTime;

	/** La distance minimum de maj de position en metre */
	private float minDistance;

	/**
	 * Objet LocationManager qui fournit l'accès au service de localisation du
	 * système Android
	 */
	private LocationManager mgr;

	private HttpClient mHttpClient;

	private URI mLocationServiceURL;

	private InpranetDBHelper mDBHelper;

	/** L'heure du dernier enregistrement de geo-localisation */
	private Calendar lastRecordTime;

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Charger les paramètres de l'application
	 */
	private void loadParameters() {
		// charger le compte utilisateur
		// TODO charger depuis le param
		mSessionID = 1;
	}

	/**
	 * Enregistrer la dernière position obtenue de l'utilisateur si la connexion
	 * internet est disponible, on l'envoie au système central sinon on la
	 * sauvegarde en local pour envoyer utérieurement
	 * 
	 * @param newLocation
	 *            la dernière position utilisateur
	 * @throws DatatypeConfigurationException
	 */
	private void registerLocation(Location newLocation){
		double longitude = newLocation.getLongitude();
		double latitude = newLocation.getLatitude();
		LocationInfo locationInfo = null;

		locationInfo = new LocationInfo(mSessionID, new GeoPos(longitude,
				latitude, new Date()));

		// Si connexion internet établie
		if (isOnline()) {
			Log.d(TAG, "Connexion internet");
			localCacheProcess();
			sendLocationInfo(locationInfo);
		} // Sinon
		else {
			Log.d(TAG, "No connexion");
			saveLocationInfo(locationInfo);
		}
	}

	private void saveLocationInfo(LocationInfo locationInfo) {
		mDBHelper.insertLocationCache(locationInfo);
	}

	private void sendLocationInfo(LocationInfo locationInfo) {
		String str_json = "{\"geopos\":{\"longitude\":"
				+ locationInfo.getLongitude() + ",\"latitude\":"
				+ locationInfo.getLatitude()
				+ ",\"time\":"
				+ "\""+ locationInfo.getTimeInCalendarFormat() + "\"}}";
		Log.d(TAG, str_json);

		// creer post, declarer reponse
		HttpResponse mHttpResponse = null;
		HttpPost post = new HttpPost(mLocationServiceURL);

		StringEntity se;
		try {
			se = new StringEntity(str_json);
		} catch (UnsupportedEncodingException e) {
			Log.d(TAG, "unsported encoding");
			return;
		}
		post.setHeader("Accept", "application/json");
		post.setHeader("Content-type", "application/json");
		se.setContentType("application/json");

		// poster json
		post.setEntity(se);
		try {
			Log.d(TAG, "uri: " + post.getURI().toString());
			mHttpResponse = mHttpClient.execute(post);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/* Checking response */
		if (mHttpResponse != null) {
			// TODO gestion d'erreur
			Log.d(TAG, "response received");
		} else {
			Log.d(TAG, "no response");
		}
	}

	private void sendLocationInfo(List<LocationInfo> cache) {
		// TODO gerer envoie d'une liste de geopos
		for (int i = 0; i != cache.size(); i++) {
			Log.d(TAG, "cache info: " + cache.get(i).getLongitude() + " "
					+ cache.get(i).getLatitude());
			sendLocationInfo(cache.get(i));
		}
	}

	private void localCacheProcess() {
		// vérifier la cache dans db
		boolean locationCached = mDBHelper.hasLocationCacheByID(mSessionID);
		if (locationCached) {
			// recuperer la liste de cache
			List<LocationInfo> cache = mDBHelper
					.getLocationCacheByID(mSessionID);
			mDBHelper.deleteLocationCacheByID(mSessionID);
			sendLocationInfo(cache);
		} else {
			Log.d(TAG, "cache vide pour utilisateur: " + mSessionID);
		}
	}

	/**
	 * Vérifier si le mobile est connecté à internet
	 * 
	 * @return boolean indiquant la connexion ou pas
	 */
	public boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cm.getActiveNetworkInfo() != null) {
			return cm.getActiveNetworkInfo().isConnectedOrConnecting();
		} else {
			return false;
		}
	}

	/**
	 * Classe fille de locationListener qui détecte l'état de GPS et le
	 * changement de localisation
	 * 
	 * @author yiquan
	 * 
	 */
	final class GPSListener implements LocationListener {
		/**
		 * méthode appelé lorsque la position change
		 */
		public void onLocationChanged(Location newLocation) {
			Log.d(TAG, "Location changed");
			// Enregistrer la nouvelle position

			registerLocation(newLocation);
			// Mise à jour l'heure du dernier enregistrement
			lastRecordTime = Calendar.getInstance();
			
		}

		/**
		 * méthode appelée lorsque le provider est désactivé
		 */
		public void onProviderDisabled(String provider) {
			Log.d(TAG, "GPS disabled");
		}

		/**
		 * Méthode appelée lorsque le provider est activé
		 */
		public void onProviderEnabled(String provider) {
			Log.d(TAG, "GPS enabled");
		}

		/**
		 * méthode appelée lorsque le status du provider change
		 */
		public void onStatusChanged(String provider, int status, Bundle extras) {
		}
	}

	final class registerTask extends TimerTask {
		public void run() {
			Calendar now = Calendar.getInstance();
			// Le temps passé depuis le dernier enregistrement en milliseconde
			long diff = (now.getTimeInMillis() - lastRecordTime
					.getTimeInMillis());
			Log.i(TAG, "Time since last update: " + String.valueOf(diff));

			if (diff >= maxTime - 1000) {
				Log.i(TAG, "Must update");
				// // Mise à jour l'heure du dernier enregistrement
				// lastRecordTime = Calendar.getInstance();

				// Enregistrer la nouvelle position
				gpsListener.onLocationChanged(mgr
						.getLastKnownLocation(LocationManager.GPS_PROVIDER));
				Log.i(TAG, "last record time = " + lastRecordTime.toString());
			}
		}
	}

	/**
	 * Procédure appelée lorsque le processus du service est créé
	 */
	@Override
	public void onCreate() {
		super.onCreate();
		Log.i(TAG, "Localization service started...");
		// Initialiser l'heure du dernier envoie à l'heure actuelle
		lastRecordTime = Calendar.getInstance();

		// Charger les paramètres
		loadParameters();

		// Connexion à la base de données Sqlite
		mDBHelper = new InpranetDBHelper(this);
		try {
			mDBHelper.createDataBase();
			mDBHelper.openRWDB();
		} catch (IOException e) {
			Log.d(TAG, "erreur IO db");
		}

		// Location manager
		mgr = (LocationManager) getSystemService(LOCATION_SERVICE);

		LocationListener gpsListener = new GPSListener();

		minTime = 10 * 60 * 1000; // 10 minutes
		maxTime = 15 * 60 * 1000; // 15 minutes
		minDistance = 0; // 20 meters

		// TODO Si GPS autorise dans le paramétrage
		mgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime,
				minDistance, gpsListener);

		// Planifier le timer qui appelle la procédure registerLocation à une
		// période maxTime
		timer.schedule(new registerTask(), 0, maxTime);

		mHttpClient = new DefaultHttpClient();
		HttpConnectionParams.setConnectionTimeout(mHttpClient.getParams(),
				TIME_OUT);
		mLocationServiceURL = setUpwsURI();
	}

	private URI setUpwsURI() {
		URI locationServiceURL = null;
		try {
			// TODO gerer session ID
			String uri = URI_WS_GEOPOS_BASE + "/" + mSessionID;
			locationServiceURL = new URI(uri);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return locationServiceURL;
	}

	/**
	 * Méthode appelée lorsque le service est éteint
	 */
	@Override
	public void onDestroy() {
		super.onDestroy();
		// Desarmer le timer
		timer.cancel();
		Log.i(TAG, "Localization service ended...");
	}

}
