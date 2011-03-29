package com.inpranet.mobile;

import java.io.IOException;

import com.inpranet.core.model.Document;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DocumentActivity extends Activity {
	public static final String EXTRA_KEY = "id";

	private static final String TAG = "DocumentActivity";
	
	private InpranetDBHelper mDBHelper;
	
	private long mDocID;
	private Document mDoc;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.document);
		mDocID = getIntent().getExtras().getLong(EXTRA_KEY);
		Log.d(TAG, "document ID: "+mDocID);
		
		mDBHelper = new InpranetDBHelper(this);
		try {
            mDBHelper.createDataBase();
            mDBHelper.openRODB();
	    } catch (IOException e) {
	            Log.d(TAG, "erreur IO db");
	    }
	    mDoc = mDBHelper.getDocumentByID(mDocID);
	    if(mDoc!=null){
	    	((TextView)findViewById(R.id.title)).setText(mDoc.getTitle());
	    	((TextView)findViewById(R.id.date)).setText(
	    			mDoc.getStart_date().toLocaleString()+" - "+
	    			mDoc.getEnd_date().toLocaleString());
	    	//TODO afficher en html
	    	((TextView)findViewById(R.id.content)).setText(mDoc.getData());
	    }
	}
	
	public void onStop(){
		super.onStop();
		if(mDBHelper!=null){
			mDBHelper.close();
		}
	}
}
