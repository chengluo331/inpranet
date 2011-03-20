package com.inpranet.mobile;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

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
 * Service de localisation qui s'exécute au démarrage de mobile
 * il enregistre les déplacements de l'utilisateur et les envoie au système central
 * @author yqzhoum, cluo
 *
 */
public class LocalizationService extends Service {

	/** Tag pour le log */
	private static final String TAG = "LocalizationService";

	private static final int TIME_OUT = 1000;

	private static final String URI_WS_STOCK_DATA = "http://10.0.2.2:9999/inpranet/services/geo";

	/** Le timer pour déclencher l'opération */
	//private Timer timer = new Timer();
	
	/** Le temps minimum de maj de position */
	private long minTime;
	
	/** La distance minimum de maj de position */
	private float minDistance;

	/** Objet LocationManager qui fournit l'accès au service de localisation du système Android */
	private LocationManager mgr;
	
	private HttpClient mHttpClient;

	private URI mLocationServiceURL;
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/** 
	 * Charger les paramètres de l'application
	 */
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
			Log.d(TAG, "Connexion internet");
			// creer post, declarer reponse
			HttpPost post = new HttpPost(mLocationServiceURL);
			HttpResponse mHttpResponse;
			// creer json
			JSONObject oJson = new JSONObject();
			try {
				// ajouter coordonnees, date
				oJson.put("longitude", longitude);
				oJson.put("latitude", latitude);
				oJson.put("time", new Date());
				
				Log.d(TAG, oJson.toString());
				// configurer type json selon RFC 4627 
				
				
				StringEntity se = new StringEntity("JSON: "+oJson.toString()); 
	            se.setContentEncoding((Header) new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
	            // poster json
	            post.setEntity(se);
				
	            Log.d(TAG, "http client is null: " +(mHttpClient == null));
	            mHttpResponse = mHttpClient.execute(post);
	            /* Checking response */
	            if(mHttpResponse!=null){
	            	Log.d(TAG, "response received");
	                InputStream in = mHttpResponse.getEntity().getContent(); //Get
	                BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
	                String str;
	                do{
	                	str = reader.readLine();
	                	Log.d(TAG, "response: " + str);
	                }while (!str.equals("")); 
	            }
	            else {
	            	Log.d(TAG, "no response");
	            }
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} // Sinon
		else {
			Log.d(TAG, "No connexion");
			// TODO Sauvegarde en local
		}
	}
	
	public boolean isOnline() {
		 ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		 if (cm.getActiveNetworkInfo() != null) {
			 return cm.getActiveNetworkInfo().isConnectedOrConnecting();
		 }
		 else {
			 return false;
		 }
		 
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
		
		mHttpClient = new DefaultHttpClient();
		HttpConnectionParams.setConnectionTimeout(mHttpClient.getParams(), TIME_OUT); 
		try {
			// TODO utiliser le vrai url, un exemple pour l'instant
			mLocationServiceURL =  new URI(URI_WS_STOCK_DATA);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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