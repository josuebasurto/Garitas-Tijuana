package com.garitas.tijuana;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.webkit.WebView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	WebView wv;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       
        //------------------------------------------
        Configura();
        
        if (HayInternet())
        	Carga();
        else
        	Toasty("Ups, no hay internet.");
    }

	private boolean HayInternet() {
		try {
			ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	  		return activeNetworkInfo != null;
		} catch (Exception e) {
			Toasty("Error" + e.getMessage());
		}
		return false;
	}

	private void Toasty(String message) {
		Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
	}

	private void Configura() {
		wv = (WebView) findViewById(R.id.webview);
		wv.getSettings().setJavaScriptEnabled(true);
	}

	private void Carga() {
		wv.loadUrl("http://garitas-tijuana.com");
		wv.scrollTo(0, 0);
	}

	@Override
	public void onResume() {
		super.onResume();
		Limpia();
        Carga();
	}

	private void Limpia() {
		wv.clearView();
	}
	
	
  

    
}
