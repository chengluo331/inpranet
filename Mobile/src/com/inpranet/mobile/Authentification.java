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
 * @author yqzhou, cluo
 *
 */
public class Authentification extends Activity implements OnClickListener{
	/** Tag pour le log */
	private static final String TAG = "Authentification";

	private boolean runFromLauncher; // lance par launcher
	
	@Override
	/**
	 * Procédure appelée lorsque l'activité est créé
	 * afficher l'interface et définir les listeners
	 */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        runFromLauncher = false;
        
        // TODO gerer service
        Intent intent = new Intent(this, LocalizationService.class);
        startService(intent);
        
        String action = getIntent().getAction();
        if(action.equals(Intent.ACTION_MAIN)){
        	runFromLauncher = true;
        	// test authentification
        	// si oui lancer collection
        	boolean isAuthentifie = checkSessionID();
        	if(isAuthentifie){
        		startCollectionActivity();
        		finish();
        	}else{
        		initView();
        	}
        }else if(action.equals(Intent.ACTION_VIEW)){
        	// supprimer authentification
        	initView();
        }else{
        	// pas possible
        	Log.e(TAG, "action inconnue");
        	finish();
        }       
    }

	private void initView() {
		 // affichier view, init composant
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
			
			//TODO verification synchrone avec timeout(a voir avec handler)
			//Si authentifie
			boolean isAuthentified = checkAuthentification(login,password);
			if(isAuthentified){
				// si lance par launcher,on va passer dans la page de collection
				if(runFromLauncher)	startCollectionActivity();
				// stop activity une fois utilisateur est authentifie
				finish();
			}else{
				Log.d(TAG, "erreur d'authentification");
				// TODO affichier erreur dialog
			}
			break;
		
		//Autres bouttons
			default:
				break;
		}
	}

	private void startCollectionActivity() {
		Intent i = new Intent(this, CollectionActivity.class);
		startActivity(i);
	}

	private boolean checkSessionID() {
		// TODO test session ID sauvegardee
		return true;
	}
	
	private boolean checkAuthentification(String login, String password) {
		// TODO verfifier authentification
		return true;
	}
}
