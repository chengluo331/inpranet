package com.inpranet.mobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class GeoHabit extends Activity implements OnClickListener{
	private static final String TAG = "GeoHabit";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //Test authentifiaction
        View buttonAuthentification = findViewById(R.id.button_authentification);
        buttonAuthentification.setOnClickListener(this);
    }

	public void onClick(View v) {
		Intent i = new Intent(this, Authentification.class);
		startActivity(i);
	}
}