package com.garitas.tijuana;

import java.util.Date;
import java.util.logging.Logger;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.josuebasurto.Activities.GeneralActivity;
import com.josuebasurto.common.stringHelper;

public class MainActivity extends GeneralActivity {
	
	/*
	 * Web View principal
	 * */
	WebView wv;
	/*
	 * Progress Dialog
	 * */
	ProgressDialog pd;

	/*
	 * Getter de Progress Dialog
	 * */
	public ProgressDialog getProgressDialog() {
		return pd;
	}
	
	/*
	 * Setter de Progress Dialog
	 * */
	public void setProgressDialog(ProgressDialog progressDialog) {
		pd = progressDialog;
	}

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Configura();
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

	private void Reload() {
		Limpia();
        Carga();
        RateValidate();
	}

	private void RateValidate() {
		if (!getBoolKeyValue(R.string.preferences_rate_notified, false))
		{			
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
		    builder.setTitle(getString(R.string.dialogs_rate_title));
		    builder.setMessage(getString(R.string.dialogs_rate_message));
		    builder.setPositiveButton(getString(R.string.dialogs_rate_yes), new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) {
		            // Do nothing but close the dialog
		        	rateApp();
		        	setLog("Question: " + getString(R.string.dialogs_rate_title) + " " + getString(R.string.dialogs_rate_yes));
		            dialog.dismiss();
		        }
		    });

		    builder.setNegativeButton(getString(R.string.dialogs_rate_no), new DialogInterface.OnClickListener() {
		        @Override
		        public void onClick(DialogInterface dialog, int which) {
		        	setLog("Question: " + getString(R.string.dialogs_rate_title) + " " + getString(R.string.dialogs_rate_no));
		            dialog.dismiss();
		        }
		    });
		    AlertDialog alert = builder.create();
		    alert.show();
		    setLog("Question: " + getString(R.string.dialogs_rate_title) + " " + getString(R.string.dialogs_rate_message));
		}
	}

	private void shareApp() {
		Intent sharingIntent = new Intent(Intent.ACTION_SEND);
	    sharingIntent.setType("text/plain");
	    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, (String) getText(R.string.message_share) + " " + getString(R.string.url_googleplay_app));
	    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, (String) getText(R.string.title_share));
	    startActivity(Intent.createChooser(sharingIntent, (String) getText(R.string.title_share)));
	}

	private void rateApp() {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse((String) getText(R.string.url_googleplay_marketapp)));
		startActivity(intent);
		
		setBooleanKeyValue(R.string.preferences_rate_notified, true);
	}

	protected void Configura() {
		
		setTag("GARITASTIJUANA");
		
		wv = (WebView) findViewById(R.id.webview);
		wv.getSettings().setJavaScriptEnabled(true);
		wv.setWebViewClient(new WebViewClient(){
			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				Toasty((String) getText(R.string.message_termino));
				
				if(pd!= null)
					pd.dismiss();
			}
		});
	}
	
	protected void Carga() {
		Toasty((String) getText(R.string.message_cargando));
		if (HayInternet())
		{	
			pd = new ProgressDialog(this);
			pd.setTitle(getString(R.string.message_cargando));
			pd.setMessage(getString(R.string.message_wait));
			pd.setCancelable(false);
			pd.setIndeterminate(true);
			pd.show();
			
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