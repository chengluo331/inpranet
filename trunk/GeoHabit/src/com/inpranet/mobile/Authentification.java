package com.inpranet.mobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

/**
 * Activité pour gérer l'authentification de l'utilisateur
 * @author yqzhou
 *
 */
public class Authentification extends Activity implements OnClickListener{
	/** Tag pour le log */
	private static final String TAG = "Authentification";

	@Override
	/**
	 * Procédure appelée lorsque l'activité est créé
	 * afficher l'interface et définir les listeners
	 */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authentification);
        //Definir les listeners pour les bouttons
        View loginButton = findViewById(R.id.button_login);
        loginButton.setOnClickListener(this);
    }

	/**
	 * Click sur un boutton
	 */
	public void onClick(View v) {
		switch(v.getId()) {
		// Boutton valider
		case R.id.button_login:
			//Recuperer le login et le mdp entres
			EditText editLogin = (EditText) findViewById(R.id.edit_login);
			String login = editLogin.getText().toString();
			Log.d(TAG,"login = " + login);
			
			EditText editPassword = (EditText) findViewById(R.id.edit_password);
			String password = editPassword.getText().toString();		
			Log.d(TAG,"mdp = " + password);
			
			//TODO crypter le mdp et envoie pour authentification 
			
			//Si authentifie
			Intent i = new Intent(this, GeoHabit.class);
			startActivity(i);
			break;
		
		//Autres bouttons
		}		
	}
}