package com.inpranet.mobile;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Service;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.IBinder;
import android.util.Log;

import com.inpranet.core.model.Category;
import com.inpranet.core.model.Document;

public class DocumentService extends Service {
	public static final String INSERT_DOCUMENT = "extra_msg";
	
	private static final String TAG = "DocumentService";
	private static final String DOC_WS_URI = "http://10.0.2.2:8080/service/public/doc/";
	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
	"yyyy-MM-dd'T'HH:mm:ss");	
	
	private long mSessionID;
	private long mRequestPeriod;
	private Timer mRequestTimer;
	private TimerTask mRequestTimerTask;
	private HttpClient mHttpClient;
	private HttpGet mHttpRequest;
	private HttpResponse mHttpResponse;
	private InpranetDBHelper mDBHelper;
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void onCreate(){
		super.onCreate();
		// load user id, update time
		mSessionID = loadSessionID();
		mRequestPeriod = mRequestPeriod();
		
		mDBHelper = new InpranetDBHelper(getApplicationContext());
		mDBHelper.openRWDB();
		
		// create handler with update time
		mRequestTimer = new Timer();
		mRequestTimerTask = new RequestDocumentTask();
		mRequestTimer.schedule(mRequestTimerTask, 5000, mRequestPeriod);
		mHttpClient = new DefaultHttpClient();
		mHttpRequest = new HttpGet(DOC_WS_URI+mSessionID);
		
	}
	
	private long mRequestPeriod() {
		// TODO charger periode depuis param
		return 300000;
	}

	private long loadSessionID() { 
		// TODO charger vrai id
		return 1;
	}

	public void onDestroy(){
		super.onDestroy();
		mRequestTimerTask.cancel();
		mRequestTimer.cancel();
		mDBHelper.close();
	}
	
	private void requestDocument(){
		Log.d(TAG, "request document for session: "+mSessionID);
		try {
			mHttpResponse = mHttpClient.execute(mHttpRequest);
			InputStream input =	mHttpResponse.getEntity().getContent();
			byte[] buffer = new byte[1024];
			int length;
			StringBuilder builder = new StringBuilder();
			while ((length = input.read(buffer)) > 0) {
				builder.append(new String(buffer, 0, length));
			}
			String json = builder.toString();
			Log.d(TAG, "received: "+ json);
			JSONObject ojson = new JSONObject(json);
			JSONArray documents = ojson.getJSONArray("document");
			for (int i = 0; i < documents.length(); i++) {
				JSONObject document = documents.getJSONObject(i);
				Log.d(TAG, "document: "+document.toString());
				String ref = document.getString("reference");
				String title = document.getString("title");
				boolean urgent = document.getBoolean("urgent");
				//TODO gerer la liste
				List<Category> catList = new ArrayList<Category>();
				catList.add(new Category(document.getJSONObject("categoriesList").getString("name")));
				String uri = document.getString("uri");
				//TODO adapter le format de date
				String str_sd = document.getString("start_date");
				String str_ed = document.getString("end_date");
				Date sd = dateFormat.parse(str_sd);
				Date ed = dateFormat.parse(str_ed);
				float longitude = (float) document.getDouble("longitude");
				float latitude = (float) document.getDouble("latitude");
				String data = document.getString("data");
				
				
				Log.d(TAG, "ref: "+ref);
				Log.d(TAG, "title: "+title);
				Log.d(TAG, "urgent: "+urgent);
				Log.d(TAG, "cat: "+catList.get(0).getName());
				Log.d(TAG, "sd: "+ sd.toString());
				Log.d(TAG, "ed: "+ ed.toString());
				Log.d(TAG, "lg: "+ longitude);
				Log.d(TAG, "lat: "+ latitude);
				Log.d(TAG, "data: "+ data);
				
				Document doc = new Document(ref,title,urgent,catList,uri,sd,ed,longitude,latitude,data);
				insertDocument(doc);
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void insertDocument(Document doc){
		try{
			mDBHelper.insertDocument(doc);
			Log.d(TAG,"insert doc: "+doc.getReference());
			sendBroadcast(new Intent(INSERT_DOCUMENT));
		}catch (SQLiteConstraintException e) {
			return;
		} 
	}
	
	class RequestDocumentTask extends TimerTask{
		public void run() {
			requestDocument();
		}
	}
}
