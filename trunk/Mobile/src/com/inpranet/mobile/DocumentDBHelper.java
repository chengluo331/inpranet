package com.inpranet.mobile;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DocumentDBHelper extends SQLiteOpenHelper{

	private static final String DOC_DB_NAME = "document";
	private static final int DB_VERSION = 1;

	public DocumentDBHelper(Context context) {
		super(context, DOC_DB_NAME, null, DB_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
