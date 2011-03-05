package com.inpranet.mobile;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;

/**
 * Activité pour configurer les paramètres de l'application
 * @author yqzhou
 *
 */
public class Prefs extends PreferenceActivity{

	/** Tag pour le log */
	private static final String TAG = "Preferences"; 
	
	/**
	 * Charger le fichier res.xml.settings.xml qui décrit cette page de configuration 
	 * pour instancier l'objet PreferenceActivity
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings);
	}
}
