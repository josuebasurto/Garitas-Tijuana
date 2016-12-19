package com.garitas.tijuana.com.garitas.tijuana.activities;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.garitas.tijuana.R;
import com.jbasurto.BaseAppCompatActivity;

/**
 * An example full-screen activity that shows and hides the system UI (i.e. status bar and
 * navigation/system bar) with user interaction.
 */
public class WebPageViewActivity extends BaseAppCompatActivity {
	/**
	 * ATTENTION: This was auto-generated to implement the App Indexing API.
	 * See https://g.co/AppIndexing/AndroidStudio for more information.
	 */
	private GoogleApiClient client;
	private ProgressDialog progressDialog;
	private WebView wv_garitas = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTag("WebPageViewActivity ");
		Log("Actividad Inicializada");

		setContentView(R.layout.activity_web_page_view);

		wv_garitas = (WebView) findViewById(R.id.wv_garitas);
		progressDialog = new ProgressDialog(getApplicationContext());

		wv_garitas.getSettings().setJavaScriptEnabled(true);
		wv_garitas.setWebViewClient(new WebViewClient(){
			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				Log("Pagina Cargada");
				if(progressDialog != null) progressDialog.dismiss();
			}
		});

		ShortToast((String) getText(R.string.message_cargando));

		if (InternetConnectivity())
		{
			progressDialog = new ProgressDialog(this);
			progressDialog.setTitle(getString(R.string.message_cargando));
			progressDialog.setMessage(getString(R.string.message_wait));
			progressDialog.show();
			Log("Obteniendo Pagina");
			wv_garitas.loadUrl((String) getText(R.string.url_garitastijuana));
			wv_garitas.scrollTo(0, 0);
			Log("Fin de Obtencion");

		}
		else
		{
			ShortToast((String) getText(R.string.message_nointernet));
			Log("No Internet");
		}

		// ATTENTION: This was auto-generated to implement the App Indexing API.
		// See https://g.co/AppIndexing/AndroidStudio for more information.
		client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
	}

	/**
	 * ATTENTION: This was auto-generated to implement the App Indexing API.
	 * See https://g.co/AppIndexing/AndroidStudio for more information.
	 */
	public Action getIndexApiAction() {
		Thing object = new Thing.Builder()
				.setName("Trafico en Garitas") // TODO: Define a title for the content shown.
				// TODO: Make sure this auto-generated URL is correct.
				.setUrl(Uri.parse("http://www.garitas-tijuana.com"))
				.build();
		return new Action.Builder(Action.TYPE_VIEW)
				.setObject(object)
				.setActionStatus(Action.STATUS_TYPE_COMPLETED)
				.build();
	}

	@Override
	public void onStart() {
		super.onStart();

		// ATTENTION: This was auto-generated to implement the App Indexing API.
		// See https://g.co/AppIndexing/AndroidStudio for more information.
		client.connect();
		AppIndex.AppIndexApi.start(client, getIndexApiAction());
	}

	@Override
	public void onStop() {
		super.onStop();

		// ATTENTION: This was auto-generated to implement the App Indexing API.
		// See https://g.co/AppIndexing/AndroidStudio for more information.
		AppIndex.AppIndexApi.end(client, getIndexApiAction());
		client.disconnect();
	}
}
