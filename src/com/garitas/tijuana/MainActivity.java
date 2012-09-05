package com.garitas.tijuana;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	WebView wv;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       
        //------------------------------------------
        Configura();
        Carga();
    }
	
	@Override
	public void onResume() {
		super.onResume();
		Limpia();
        Carga();
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
		Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
	}

	private void Configura() {
		wv = (WebView) findViewById(R.id.webview);
		wv.getSettings().setJavaScriptEnabled(true);
		wv.setWebViewClient(new WebViewClient(){
			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				Toasty("Listo!");
			}
		});
	}

	private void Carga() {
		Toasty("Espera, estamos cargando la informacion mas reciente y dependemos de la velocidad de tu conexión.");
		if (HayInternet())
		{	
			wv.loadUrl("http://garitas-tijuana.com");
			wv.scrollTo(0, 0);
		}
        else
        {
        	Toasty("Ups, no hay internet.");
        }
	}

	private void Limpia() {
		wv.clearView();
	}
	
	
  

    
}
