package com.garitas.tijuana;

import org.apache.cordova.*;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.content.Context;
import android.widget.Toast;

public class MainActivity extends DroidGap {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Carga();
        super.loadUrl("file:///android_asset/www/index.html");
    }

	private boolean HayInternet() {
		try {
			ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	  		return activeNetworkInfo != null;
		} catch (Exception e) {
			Toasty("Ups acaba de ocurrir un error. " + e.getMessage());
		}
		return false;
	}

	private void Toasty(String message) {
		Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
	}


	private void Carga() {
		if (HayInternet())
			super.loadUrl("file:///android_asset/www/index.html",5000);
        else
        {
        	Toasty("Ups, no hay internet.");
        }
	}
}
