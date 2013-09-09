package com.garitas.tijuana;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
	
	private void Reload() {
		Limpia();
        Carga();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		setLog("Seleccion " + item.getTitle());
	    switch (item.getItemId()) {
	        case R.id.rateApp: rateApp(); return true;
	        case R.id.share: shareApp(); return true;
	        case R.id.refresh: Reload(); return true;
	        default: return super.onOptionsItemSelected(item);
	    }
	}
	
	private void shareApp() {
		Intent sharingIntent = new Intent(Intent.ACTION_SEND);
	    sharingIntent.setType("text/plain");
	    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, (String) getText(R.string.message_share) + getString(R.string.url_googleplay_app));
	    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, (String) getText(R.string.title_share));
	    startActivity(Intent.createChooser(sharingIntent, (String) getText(R.string.title_share)));
	}

	private void rateApp() {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse((String) getText(R.string.url_googleplay_marketapp)));
		startActivity(intent);
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
				Toasty((String) getText(R.string.message_termino));
			}
		});
	}
	
	protected void Carga() {
		Toasty((String) getText(R.string.message_cargando));
		if (HayInternet())
		{	
			wv.loadUrl((String) getText(R.string.url_garitastijuana));
			wv.scrollTo(0, 0);
		}
        else
        {
        	Toasty((String) getText(R.string.message_nointernet));
        }
	}
	
	protected void Limpia() {
		wv.loadUrl((String) getText(R.string.url_blank));
	}

}