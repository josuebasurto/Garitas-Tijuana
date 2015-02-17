package com.garitas.tijuana;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.josuebasurto.Activities.BaseGeneralActivity;

public class MainActivity extends BaseGeneralActivity {

    /**
     * Private elements
     * */
	WebView lanesWebView;
	ProgressDialog loadingProgressDialog;

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

    private void generateProgressDialog(String titleDialog, String messageWait) {
        loadingProgressDialog = new ProgressDialog(this);
        loadingProgressDialog.setTitle(titleDialog);
        loadingProgressDialog.setMessage(messageWait);
        loadingProgressDialog.show();
    }


	protected void Configura() {
		setTag("GARITASTIJUANA");
		
		lanesWebView = (WebView) findViewById(R.id.webview);
        lanesWebView.getSettings().setJavaScriptEnabled(true);
        lanesWebView.setWebViewClient(new WebViewClient(){
			@Override
			public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
				if(loadingProgressDialog!= null) loadingProgressDialog.dismiss();
			}
		});
	}
	
	protected void Carga() {
		if (HayInternet())
		{
            generateProgressDialog(getString(R.string.message_cargando), getString(R.string.message_wait));

            lanesWebView.getSettings();
            lanesWebView.setBackgroundColor(0);

            lanesWebView.loadUrl((String) getText(R.string.url_garitastijuana));
            lanesWebView.scrollTo(0, 0);
		}
        else
        {
        	Toasty((String) getText(R.string.message_nointernet));
        }
	}

    protected void Limpia() {
        lanesWebView.loadUrl((String) getText(R.string.url_blank));
        lanesWebView.getSettings();
        lanesWebView.setBackgroundColor(0);
	}

}