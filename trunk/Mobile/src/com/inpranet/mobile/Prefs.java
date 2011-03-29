package com.inpranet.mobile;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.util.Log;

/**
 * Activité pour configurer les paramètres de l'application
 * @author yqzhou
 *
 */
public class Prefs extends PreferenceActivity{

	/** Tag pour le log */
	private static final String TAG = "Preferences"; 
	
	/** Nom de l'objet persistent pour sauvegarder les preferences*/
	public static final String PREF_NAME = "Settings_GeoHabit";
	
	/** Identifiant de checkBoxLocalizationService*/
	public static final String KEY_CHECKBOX_LOCALISATION_SERVICE = "localizationService";
	
	/** Identifiant de listPrefHabitPrecision */
	public static final String KEY_LISTPREF_HABIT_PRECISION = "habitPrecision";
	
	/** Identifiant de listPrefRefreshFrequence	 */
	
	/** L'objet persistent pour sauvegarder les preferences*/
	SharedPreferences settings;
	
	/** Objet checkBox d'activation du service de localisation */
	private CheckBoxPreference checkBoxLocalizationService;
	
	/** Objet listPreference pour choisir le niveau de précision d'habitude */
	private ListPreference listPrefHabitPrecision;
	
	/** Objet listPreference pour choisir le niveau de fréquence de rechargement des documents */
	private ListPreference listPrefRefreshFrequence;
	
	/**
	 * Charger le fichier res.xml.settings.xml qui décrit cette page de configuration 
	 * pour instancier l'objet PreferenceActivity
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Charger le fichier XML 
		addPreferencesFromResource(R.xml.settings);
		
		settings = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
		PreferenceScreen prefScreen = getPreferenceScreen();
		
		checkBoxLocalizationService = (CheckBoxPreference)prefScreen.findPreference(KEY_CHECKBOX_LOCALISATION_SERVICE);
		checkBoxLocalizationService.setChecked(settings.getBoolean("localizationService", true));
		
		listPrefHabitPrecision = (ListPreference)prefScreen.findPreference(KEY_LISTPREF_HABIT_PRECISION);
		listPrefHabitPrecision.setValue(settings.getString(KEY_LISTPREF_HABIT_PRECISION, "medium"));
		
		listPrefRefreshFrequence = (ListPreference)prefScreen.findPreference(KEY_LISTPREF_HABIT_PRECISION);
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		//SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);		
		
		SharedPreferences.Editor ed = settings.edit();
		ed.putBoolean(KEY_CHECKBOX_LOCALISATION_SERVICE, checkBoxLocalizationService.isChecked());
		ed.putString(KEY_LISTPREF_HABIT_PRECISION, listPrefHabitPrecision.getValue());
		ed.commit();
	}
}
