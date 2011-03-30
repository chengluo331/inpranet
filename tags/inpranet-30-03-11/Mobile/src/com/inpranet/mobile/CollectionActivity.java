package com.inpranet.mobile;

import java.io.IOException;
import java.util.List;

import android.app.TabActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

public class CollectionActivity extends TabActivity implements OnItemClickListener {
	private static final int INDEX_ACCEUIL = 0;
	private static final int INDEX_SCOLAIRE = 1;
	private static final int INDEX_SHOPPING = 2;
	
	/** Tag pour le log */
	private static final String TAG = "CollectionActivity";
	
	private static final String[] TAB_ID = {"acceuil","scolaire","shopping"};

	private ListView mAcceuilListView;
	private ListView mScolaireListView;
	private ListView mShoppingListView;
	
	private DocumentListAdapter mAcceuilListAdapter;
	private DocumentListAdapter mScolaireListAdapter;
	private DocumentListAdapter mShoppingListAdapter;
	
	private InpranetDBHelper mDBHelper;
	
	private DocumentUpdateRecevier mReceiver = new DocumentUpdateRecevier();
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.collection);
		
		//init tabs et listes
        TabHost tabHost = getTabHost();
       
        tabHost.addTab(tabHost.newTabSpec(TAB_ID[INDEX_ACCEUIL])
                .setIndicator(getString(R.string.tab_tag_acceuil))
                .setContent(R.id.listView_acceuil));
        tabHost.addTab(tabHost.newTabSpec(TAB_ID[INDEX_SCOLAIRE])
                .setIndicator(getString(R.string.tab_tag_scolaire))
                .setContent(R.id.listView_scolaire));
        tabHost.addTab(tabHost.newTabSpec(TAB_ID[INDEX_SHOPPING])
                .setIndicator(getString(R.string.tab_tag_shopping))
                .setContent(R.id.listView_shopping));
        
        mAcceuilListView = (ListView) findViewById(R.id.listView_acceuil);
        mScolaireListView = (ListView) findViewById(R.id.listView_scolaire);
        mShoppingListView = (ListView) findViewById(R.id.listView_shopping);
        
		//acceder db
		mDBHelper = new InpranetDBHelper(getApplicationContext());
		try {
            mDBHelper.createDataBase();
            mDBHelper.openRODB();
	    } catch (IOException e) {
	            Log.d(TAG, "erreur IO db");
	    }
		mAcceuilListAdapter = new DocumentListAdapter(this,InpranetDBHelper.CAT_WELCOME);
		mAcceuilListView.setAdapter(mAcceuilListAdapter);
		mAcceuilListView.setOnItemClickListener(this);
		
		mScolaireListAdapter = new DocumentListAdapter(this,InpranetDBHelper.CAT_SCOLAIRE);
		mScolaireListView.setAdapter(mScolaireListAdapter);
		mScolaireListView.setOnItemClickListener(this);
		
		mShoppingListAdapter = new DocumentListAdapter(this,InpranetDBHelper.CAT_SHOPPING);
		mShoppingListView.setAdapter(mShoppingListAdapter);
		mShoppingListView.setOnItemClickListener(this);
			
		// lancer document service
		Intent intent = new Intent(this, DocumentService.class);
		startService(intent);
	}
	
	/**
	 *  Afficher le menu décrit dans res.menu.menu.xml lorsque l'on appuie sur boutton menu de l'appareil
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;		
	}
	
	/**
	 * Procédure appelée lorsque un élement dans le menu est sélectionné
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		
		// Element settings qui dirige vers l'activité Prefs pour configurer les paramètres
		case R.id.settings:
			startActivity(new Intent(this, Prefs.class));
			break;
			
		// Element exit pour quitter l'application
		case R.id.exit:
			this.finish();
			stopService(new Intent(this, LocalizationService.class));
			mDBHelper.backupDB();
			break;
			
		}
		return false;
	}
	
	@Override
	public void onResume(){
		super.onResume();
		registerReceiver(mReceiver, new IntentFilter(DocumentService.INSERT_DOCUMENT));
//		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
//		Log.d(TAG, prefs.getString("habitPrecision", "nothing"));
	}
	
	public void onPause(){
		super.onPause();
		unregisterReceiver(mReceiver);
	}
	
	public void onItemClick(AdapterView<?> parent, View arg1, int arg2, long id) {
		Intent intent = new Intent(this, DocumentActivity.class);
		intent.putExtra(DocumentActivity.EXTRA_KEY, id);
		startActivity(intent);
	}
	
	public void onStop(){
		super.onStop();
		if(mDBHelper!=null){
			mDBHelper.close();
		}
		// stopper document service
		Intent intent = new Intent(this, DocumentService.class);
		getApplicationContext().stopService(intent);
	}
	
	private class DocumentUpdateRecevier extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			mAcceuilListAdapter.notifyLocalListChanged();
			mScolaireListAdapter.notifyLocalListChanged();
			mShoppingListAdapter.notifyLocalListChanged();
			Log.d(TAG, "update ui");
		}
		
	}
	
	// adapteur de liste pour les documents
	private class DocumentListAdapter extends BaseAdapter{
		private List<DocumentInfo> mLocalDocumentList;
		private LayoutInflater mInflater;
		private String mCategory;
		
		public DocumentListAdapter(Context context, String category){
			mInflater = LayoutInflater.from(context);
			mCategory = category;
			notifyLocalListChanged();
		}
		
		public int getCount() {
			return mLocalDocumentList.size();
		}

		public Object getItem(int position) {
			return mLocalDocumentList.get(position);
		}

		public long getItemId(int position) {
			return ((DocumentInfo)getItem(position)).getDocID();
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder;
			if(convertView == null){
				convertView = mInflater.inflate(R.layout.doc_list_item, null);
				viewHolder = new ViewHolder();
				viewHolder.icon = (ImageView) convertView.findViewById(R.id.doc_icon);
				viewHolder.title = (TextView) convertView.findViewById(R.id.doc_title);
				viewHolder.firstLine = (TextView) convertView.findViewById(R.id.doc_first_line);

				convertView.setTag(viewHolder);
			}else{
				viewHolder = (ViewHolder) convertView.getTag();
			}
			DocumentInfo doc = mLocalDocumentList.get(position);
			if(doc!=null){
				// TODO affecter la vraie icone
				if(doc.isUrgent()){
					viewHolder.icon.setImageResource(R.drawable.doc_urgent);
				}else{
					viewHolder.icon.setImageResource(R.drawable.doc_normal);
				}
				viewHolder.title.setText(doc.getDocTitle());
				viewHolder.firstLine.setText(""+doc.getDocFirstLine());
			}
			return convertView;
		}
		
		class ViewHolder{
			ImageView icon;		// icone d'un doc sur la liste
			TextView title;		// titre d'un doc sur la liste
			TextView firstLine;	// la première phrase d'un doc sur la liste 
		}
		
		public void notifyLocalListChanged(){
			mLocalDocumentList = mDBHelper.getAllDocumentsByCategory(mCategory);
			notifyDataSetChanged();
		}
	}
}
