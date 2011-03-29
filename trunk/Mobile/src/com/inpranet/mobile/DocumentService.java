package com.inpranet.mobile;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import com.inpranet.core.model.Category;
import com.inpranet.core.model.Document;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.util.Xml.Encoding;

public class DocumentService extends Service {
	public static final String INSERT_DOCUMENT = "extra_msg";
	
	private static final String TAG = "DocumentService";
	private static final String DOC_WS_URI = "http://10.0.2.2:8080/inpranet/services/doc";
		
	private long mSessionID;
	private long mRequestPeriod;
	private Timer mRequestTimer;
	private TimerTask mRequestTimerTask;
	private HttpClient mHttpClient;
	private HttpGet mHttpRequest;
	private HttpResponse mHttpResponse;
	private InpranetDBHelper mDBHelper;
	private Messenger mMessenger;
	
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
		mHttpRequest = new HttpGet(DOC_WS_URI);
		
	}
	
	private long mRequestPeriod() {
		// TODO charger periode depuis param
		return 5000;
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
//		try {
//			mHttpResponse = mHttpClient.execute(mHttpRequest);
//			InputStream input =	mHttpResponse.getEntity().getContent();
//			byte[] buffer = new byte[1024];
//			int length;
//			StringBuilder builder = new StringBuilder();
//			while ((length = input.read(buffer)) > 0) {
//				builder.append(new String(buffer, 0, length));
//			}
//			String json = builder.toString();
//			Log.d(TAG, "received: "+ json);
//			JSONObject ojson = new JSONObject(json);
//			
//		} catch (ClientProtocolException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		List<Category> catList = new ArrayList<Category>();
		catList.add(new Category("acceuil"));
		Document doc = new Document(
				"ref11",
				"test service",
				true,
				catList,
				"www.test.com",
				new Date(),
				new Date(),
				0,0,"contenu");
		insertDocument(doc);
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
