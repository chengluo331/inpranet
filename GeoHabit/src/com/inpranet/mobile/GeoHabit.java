package com.inpranet.mobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * Activité qui gère l'interface principale
 * @author yqzhou
 *
 */
public class GeoHabit extends Activity implements OnClickListener{
	/** Tag pour le log */
	private static final String TAG = "GeoHabit";
   
	/** 
	 * Procédure appelée lorsque l'activité est créé
	 * 
	 */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //Test authentifiaction
        View buttonAuthentification = findViewById(R.id.button_authentification);
        buttonAuthentification.setOnClickListener(this);
                
        // Start service
        Intent iStartService = new Intent(this, LocalizationService.class);
		startService(iStartService);
    }

    /**
     * Click sur un boutton
     */
	public void onClick(View v) {
		switch(v.getId()) {
		// Boutton Authentification
		case R.id.button_authentification:
			Intent iStartAuthentification = new Intent(this, Authentification.class);
			startActivity(iStartAuthentification);
			break;
		
		}
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
	
}