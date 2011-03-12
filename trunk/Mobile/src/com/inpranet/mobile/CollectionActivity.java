package com.inpranet.mobile;

import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TabHost;

public class CollectionActivity extends TabActivity {
	private static final int INDEX_ACCEUIL = 0;
	private static final int INDEX_COMMERCE = 1;
	private static final int INDEX_SPORT = 2;
	
	/** Tag pour le log */
	private static final String TAG = "CollectionActivity";
	
	private static final String[] TAB_ID = {"acceuil","commerce","sport"};

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
}
