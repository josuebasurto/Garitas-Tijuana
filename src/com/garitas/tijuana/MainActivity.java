package com.garitas.tijuana;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.josuebasurto.Activities.GeneralActivity;

public class MainActivity extends GeneralActivity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Configura();
        Carga();
    }
	
	@Override
	public void onResume() {
		super.onResume();
		Limpia();
        Carga();
	}
	
	WebView wv;
	
	protected void Configura() {
		setTag("GARITASTIJUANA");
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
	
	protected void Carga() {
		Toasty("Espera, estamos cargando la informacion mas reciente y dependemos de la velocidad de tu conexión.");
		if (HayInternet())
		{	
			String garitas_url = "http://garitas-tijuana.com";
			setLog("Cargando: " + garitas_url);
			wv.loadUrl(garitas_url);
			wv.scrollTo(0, 0);
		}
        else
        {
        	Toasty("Ups, no hay internet.");
        }
	}
	
	protected void Limpia() {
		wv.clearView();
	}

}