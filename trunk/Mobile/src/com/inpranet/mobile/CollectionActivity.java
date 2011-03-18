package com.inpranet.mobile;

import java.util.List;
import java.util.zip.Inflater;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

public class CollectionActivity extends TabActivity {
	private static final int INDEX_ACCEUIL = 0;
	private static final int INDEX_COMMERCE = 1;
	private static final int INDEX_SPORT = 2;
	
	/** Tag pour le log */
	private static final String TAG = "CollectionActivity";
	
	private static final String[] TAB_ID = {"acceuil","commerce","sport"};

	private ListView mAcceuilListView;
	private ListView mCommerceListView;
	private ListView mSportListView;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.collection);
		
		//init tabs et listes
        TabHost tabHost = getTabHost();
       
        tabHost.addTab(tabHost.newTabSpec(TAB_ID[INDEX_ACCEUIL])
                .setIndicator(getString(R.string.tab_tag_acceuil))
                .setContent(R.id.listView_acceuil));
        tabHost.addTab(tabHost.newTabSpec(TAB_ID[INDEX_COMMERCE])
                .setIndicator(getString(R.string.tab_tag_commerce))
                .setContent(R.id.listView_commerce));
        tabHost.addTab(tabHost.newTabSpec(TAB_ID[INDEX_SPORT])
                .setIndicator(getString(R.string.tab_tag_sport))
                .setContent(R.id.listView_sport));
        
        mAcceuilListView = (ListView) findViewById(R.id.listView_acceuil);
        mCommerceListView = (ListView) findViewById(R.id.listView_commerce);
        mSportListView = (ListView) findViewById(R.id.listView_sport);
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
			break;
			
		}
		return false;
	}
	
	@Override
	public void onResume(){
		super.onResume();
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		Log.d(TAG, prefs.getString("habitPrecision", "nothing"));
	}
	
	// adapteur de liste pour les documents
	private class DocumentListAdapter extends BaseAdapter{
		private List<Document> mLocalDocumentList;
		private LayoutInflater mInflater;
		
		public DocumentListAdapter(Context context, List<Document> docList){
			mInflater = LayoutInflater.from(context);
			mLocalDocumentList = docList;
		}
		
		public int getCount() {
			return mLocalDocumentList.size();
		}

		public Object getItem(int position) {
			return mLocalDocumentList.get(position);
		}

		public long getItemId(int position) {
			return position;
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
			Document doc = mLocalDocumentList.get(position);
			if(doc!=null){
				// TODO affecter la vraie icone
				viewHolder.icon.setImageResource(R.drawable.icon);
				viewHolder.title.setText(doc.getDocTitle());
				viewHolder.firstLine.setText(doc.getDocFirstLine());
			}
			return convertView;
		}
		
		class ViewHolder{
			ImageView icon;		// icone d'un doc sur la liste
			TextView title;		// titre d'un doc sur la liste
			TextView firstLine;	// la première phrase d'un doc sur la liste 
		}
	}
}
