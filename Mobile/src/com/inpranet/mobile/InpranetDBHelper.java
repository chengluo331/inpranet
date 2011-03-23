package com.inpranet.mobile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.DataFormatException;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class InpranetDBHelper extends SQLiteOpenHelper {
	public static final String CAT_WELCOME = "acceuil";
	public static final String CAT_SPORT = "sport";
	public static final String CAT_COMMERCIAL = "commerce";

	private static final String TAG = "DocumentDBHelper";

	private static final int DB_VERSION = 1;
	private static final String DATABASE_NAME = "inpranet_db";
	// TODO essayer de faire plus proprement sur le nom du pacakge
	private static final String DATABASE_PATH = "/data/data/com.inpranet.mobile/databases/";

	private static final String TABLE_DOCUMENT = "document";
	private static final String TABLE_CACHE = "cache";

	private static final String KEY_DOCID = "_id";
	private static final String KEY_TITLE = "title";
	private static final String KEY_ZONE = "zone";
	private static final String KEY_EVENT_IMPORTANCE = "eventImportance";
	private static final String KEY_CATEGORY = "category";
	private static final String KEY_START_DATE = "startDate";
	private static final String KEY_END_DATE = "endDate";
	private static final String KEY_FIRST_LINE = "firstLine";
	private static final String KEY_HTML_DATA = "htmlData";

	private static final String KEY_USERID = "_userID";
	private static final String KEY_TIME = "time";
	private static final String KEY_LOGITUDE = "longitude";
	private static final String KEY_LATITUDE = "latitude";

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	private Context mContext;
	private SQLiteDatabase mDataBase;

	public InpranetDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DB_VERSION);
		mContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}

	public void createDataBase() throws IOException {
		boolean dbExist = checkDataBase();
		if (dbExist) {
			// db existe deja
			Log.d(TAG, "db exist");
		} else {
			Log.d(TAG, "create db");
			// creer db par defaut, puis le ecraser
			this.getReadableDatabase();
			try {
				copyDataBase();
			} catch (IOException e) {
				throw new Error("Error copying database");
			}
		}
	}

	private void copyDataBase() throws IOException {
		// ouvrir db locale en flux
		InputStream mInput = mContext.getAssets().open(DATABASE_NAME);
		String outFileName = DATABASE_PATH + DATABASE_NAME;
		OutputStream mOutput = new FileOutputStream(outFileName);

		// transferer donnees de la db
		byte[] buffer = new byte[1024];
		int length;
		while ((length = mInput.read(buffer)) > 0) {
			mOutput.write(buffer, 0, length);
		}
		// fermeture
		mOutput.flush();
		mOutput.close();
		mInput.close();
	}

	private boolean checkDataBase() {
		String path = DATABASE_PATH + DATABASE_NAME;
		File db_f = new File(path);
		return db_f.exists();
	}

	/**
	 * ouvrir la base de donnees en lecture seule
	 * */
	public void openRODB() throws SQLException {
		String mPath = DATABASE_PATH + DATABASE_NAME;
		mDataBase = SQLiteDatabase.openDatabase(mPath, null,
				SQLiteDatabase.OPEN_READONLY);
	}

	/**
	 * ouvrir la base de donnees en lecture + ecriture
	 */
	public void openRWDB() throws SQLException {
		String mPath = DATABASE_PATH + DATABASE_NAME;
		mDataBase = SQLiteDatabase.openDatabase(mPath, null,
				SQLiteDatabase.OPEN_READWRITE);
	}

	/**
	 * fermer la base de donnees
	 */
	public synchronized void close() {
		if (mDataBase != null)
			mDataBase.close();
		super.close();
	}

	/**
	 * fonction interne utilise pour requeter(1er niveu)
	 * 
	 * @param distinct
	 * @param table
	 * @param columns
	 * @param selection
	 * @param selectionArgs
	 * @param groupBy
	 * @param having
	 * @param orderBy
	 * @param limit
	 * @return
	 */
	private Cursor query(boolean distinct, String table, String[] columns,
			String selection, String[] selectionArgs, String groupBy,
			String having, String orderBy, String limit) {
		return mDataBase.query(distinct, table, columns, selection,
				selectionArgs, groupBy, having, orderBy, limit);
	}

	/**
	 * fonction interne utilise pour requeter(2eme niveau)
	 * 
	 * @param table
	 * @param selection
	 * @return
	 */
	private Cursor query(String table, String selection) {
		return query(true, table, null, selection, null, null, null, null, null);
	}

	/**
	 * fonction publique pour recuperer tout les document par category
	 * 
	 * @param category
	 * @return
	 * @throws IllegalArgumentException
	 * @throws ParseException
	 */
	public List<DocumentInfo> getAllDocumentsByCategory(String category) {
		Cursor c = query(TABLE_DOCUMENT, KEY_CATEGORY + "='" + category + "'");
		List<DocumentInfo> docList = new ArrayList<DocumentInfo>();
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			docList.add(new DocumentInfo(c.getLong(c
					.getColumnIndexOrThrow(KEY_DOCID)), c.getString(c
					.getColumnIndexOrThrow(KEY_TITLE)), c.getInt(c
					.getColumnIndexOrThrow(KEY_EVENT_IMPORTANCE)) != 0, c
					.getString(c.getColumnIndexOrThrow(KEY_CATEGORY)), c
					.getString(c.getColumnIndexOrThrow(KEY_FIRST_LINE))));
		}
		return docList;
	}

	public Document getDocumentByID(long id) {
		Cursor c = query(TABLE_DOCUMENT, KEY_DOCID + "=" + id);
		Document doc = null;
		c.moveToFirst();
		if (!c.isAfterLast()) {
			try {
				doc = new Document(
						c.getLong(c.getColumnIndexOrThrow(KEY_DOCID)),
						c.getString(c.getColumnIndexOrThrow(KEY_TITLE)),
						c.getString(c.getColumnIndexOrThrow(KEY_ZONE)),
						c.getInt(c.getColumnIndexOrThrow(KEY_EVENT_IMPORTANCE)) != 0,
						c.getString(c.getColumnIndexOrThrow(KEY_CATEGORY)),
						dateFormat.parse(c.getString(c
								.getColumnIndexOrThrow(KEY_START_DATE))),
						dateFormat.parse(c.getString(c
								.getColumnIndexOrThrow(KEY_END_DATE))),
						c.getString(c.getColumnIndexOrThrow(KEY_FIRST_LINE)), c
								.getString(c
										.getColumnIndexOrThrow(KEY_HTML_DATA)));
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return doc;
	}

	public boolean hasLocationCacheByID(long id){
		Cursor c = query(TABLE_CACHE, KEY_USERID + "="+id);
		c.moveToFirst();
		if(c.isAfterLast()){
			return false;
		}else{
			return true;
		}		
	}
	
	public List<LocationInfo> getLocationCacheByID(long id) {
		Cursor c = query(TABLE_CACHE, KEY_USERID + "="+id);
		List<LocationInfo> cache = new ArrayList<LocationInfo>();
		try {
			for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {

				cache.add(new LocationInfo(c.getLong(c
						.getColumnIndexOrThrow(KEY_USERID)), dateFormat.parse(c
						.getString(c.getColumnIndexOrThrow(KEY_TIME))), c
						.getDouble(c.getColumnIndexOrThrow(KEY_LOGITUDE)), c
						.getDouble(c.getColumnIndexOrThrow(KEY_LATITUDE))));

			}
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cache;
	}
	
	private void insert(String table, ContentValues values){
		mDataBase.insertOrThrow(table, null, values);
	}
	
	public void insertLocationCache(LocationInfo cache){
		ContentValues cv = new ContentValues();
		cv.put(KEY_USERID, cache.getUserID());
		cv.put(KEY_TIME, dateFormat.format(cache.getTime()));
		cv.put(KEY_LOGITUDE, cache.getLongitude());
		cv.put(KEY_LATITUDE, cache.getLatitude());
		insert(TABLE_CACHE, cv);
	}

	//****************
	// Android haut niveau API ne marche pas T_T
	//****************
//	private void delete(String table, String whereClause, String[] whereArgs){
//		int num = mDataBase.delete(table, whereClause, whereArgs);
//		Log.d(TAG, "exec: delete"+ whereClause+whereArgs[0]);
//		Log.d(TAG, "delete rows: " +  num);
//	}
//	
//	public void deleteLocationCacheByID(long id){
//		delete(TABLE_CACHE, KEY_USERID+" = ? ", new String[]{Long.toString(id)});
//	}
	
	// TODO debugger avec delete
	public void deleteLocationCacheByID(long id){
		mDataBase.execSQL("delete from "+ TABLE_CACHE + " where "+KEY_USERID+" = "+id);
	}
}
