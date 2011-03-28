package com.inpranet.mobile;



import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;

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
 * @author yqzhou, cluo
 *
 */
public class LocalizationService extends Service {

	/** Tag pour le log */
	private static final String TAG = "LocalizationService";

	private static final int TIME_OUT = 1000;

	private static final String URI_WS_STOCK_DATA = "http://10.0.2.2:9999/inpranet/services/geo";

	/** L'id d'utilisateur qui utilise le service */
	private long mUserID;
	
	/** Le temps minimum de maj de position */
	private long minTime;
	
	/** La distance minimum de maj de position */
	private float minDistance;

	/** Objet LocationManager qui fournit l'accès au service de localisation du système Android */
	private LocationManager mgr;
	
	private HttpClient mHttpClient;

	private URI mLocationServiceURL;
	
	private InpranetDBHelper mDBHelper;
	
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
		mUserID = 1;
	}
	
	/**
	 * Enregistrer la dernière position obtenue de l'utilisateur
	 * si la connexion internet est disponible, on l'envoie au système central 
	 * sinon on la sauvegarde en local pour envoyer utérieurement
	 * @param newLocation la dernière position utilisateur
	 */
	private void registerLocation(Location newLocation) {
		double longitude = newLocation.getLongitude();
		double latitude = newLocation.getLatitude();
		LocationInfo locationInfo = new LocationInfo(mUserID, 
				new Date(), longitude, latitude);
		
		// Si connexion internet établie
		if(isOnline()) {
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
		// TODO Auto-generated method stub
		// creer post, declarer reponse
//		HttpPost post = new HttpPost(mLocationServiceURL);
//		HttpResponse mHttpResponse;
//		// creer json
////		JSONObject oJson = new JSONObject();
//		try {
//			// ajouter coordonnees, date
////			oJson.put("longitude", longitude);
////			oJson.put("latitude", latitude);
////			oJson.put("time", new Date());
//			
////			Log.d(TAG, oJson.toString());
////			// configurer type json selon RFC 4627 				
////			StringEntity se = new StringEntity("JSON: "+oJson.toString()); 
//			String oJson = "{ \"geopos\": {\"longitude\":2, \"latitude\":3,\"time\": "+"4}}";
//			Log.d(TAG, oJson);
//			StringEntity se = new StringEntity(oJson);				
//            se.setContentEncoding((Header) new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
//            se.setContentType("application/json");
//            // poster json
//            post.setEntity(se);
//////            Log.d(TAG, "http client is null: " +(mHttpClient == null));
//            mHttpResponse = mHttpClient.execute(post);
//            /* Checking response */
//            if(mHttpResponse!=null){
//            	//TODO gestion d'erreur
//            }
//            else {
//            	Log.d(TAG, "no response");
//            }
//			
////		} catch (JSONException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClientProtocolException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
	
	private void sendLocationInfo(List<LocationInfo> cache) {
		// TODO Auto-generated method stub
		for (int i=0; i!=cache.size();i++){
			Log.d(TAG, "cache info: "+cache.get(i).getLongitude()+" "+cache.get(i).getLatitude());
		}
	}

	private void localCacheProcess() {
		// vérifier la cache dans db
		boolean locationCached = mDBHelper.hasLocationCacheByID(mUserID);
		if(locationCached){
		// recuperer la liste de cache
			List<LocationInfo> cache = mDBHelper.getLocationCacheByID(mUserID);
			mDBHelper.deleteLocationCacheByID(mUserID);
			sendLocationInfo(cache);
		}else{
			Log.d(TAG, "cache vide pour utilisateur: "+mUserID);
		}
	}

	
	/**
	 * Vérifier si le mobile est connecté à internet
	 * @return boolean indiquant la connexion ou pas
	 */
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
		
		mDBHelper = new InpranetDBHelper(this);
		try {
            mDBHelper.createDataBase();
            mDBHelper.openRWDB();
	    } catch (IOException e) {
	            Log.d(TAG, "erreur IO db");
	    }
		
		mgr = (LocationManager) getSystemService(LOCATION_SERVICE);
		
		final class GPSListener implements LocationListener {
			public void onLocationChanged(Location newLocation) {
				Log.d(TAG, "Location changed");
				registerLocation(newLocation);				
			}

			public void onProviderDisabled(String provider) {
				Log.d(TAG, "GPS disabled");				
			}

			public void onProviderEnabled(String provider) {
				Log.d(TAG, "GPS enabled");				
			}

			public void onStatusChanged(String provider, int status,
					Bundle extras) {
			}			
		}
		
		GPSListener gpsListener = new GPSListener();

		
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
	 * Méthode appelée lorsque le service est éteint
	 */
	@Override
	public void onDestroy(){
		// TODO relacher des ressources
		super.onDestroy();
		Log.i(TAG, "Localization service ended...");
	}



	
}
