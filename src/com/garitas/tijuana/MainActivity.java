package com.garitas.tijuana;

import org.apache.cordova.DroidGap;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.content.Context;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends DroidGap {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        Carga();
    }
	
	@Override
	public void onResume() {
		super.onResume();
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
		Toast.makeText(getApplicationContext(), message, 10).show();
	}


	private void Carga() {
		if (HayInternet())
			super.loadUrl("file:///android_assets/www/index.html",5000);
        else
        {
        	Toasty("Ups, no hay internet.");
        }
	}
}
