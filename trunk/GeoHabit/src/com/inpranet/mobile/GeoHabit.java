package com.inpranet.mobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
        View buttonExit = findViewById(R.id.button_exit);
        buttonExit.setOnClickListener(this);
        
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
		
		// Boutton quit (utile pour tester sans relancer l'emulator)
		case R.id.button_exit:
			this.finish();
			Intent iStopService = new Intent(this, LocalizationService.class);
			stopService(iStopService);
			break;
		}
	}
}