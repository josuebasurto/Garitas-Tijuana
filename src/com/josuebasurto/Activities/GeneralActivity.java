package com.josuebasurto.Activities;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

public class GeneralActivity extends Activity {
	
	private String TAG = "NO_TAG_SET";
	
	protected boolean HayInternet() {
		try {
			ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	  		return activeNetworkInfo != null;
		} catch (Exception e) {
			Toasty("Ups acaba de ocurrir un error. " + e.getMessage());
		}
		return false;
	}
	
	protected void Toasty(String message) {
		Toasty(message, Toast.LENGTH_LONG);
	}
	
	protected void Toasty(String mess, int dur){
		setLog("Toast: " + mess);
		Toast.makeText(getApplicationContext(), mess, dur).show();
	}
	
	protected String getTag(){
		return TAG;
	}
	
	protected void setTag(String tag)
	{
		if (tag.length() > 0)
			TAG = tag;
	}
	
	protected void setLog(String mess){
		setLog(mess,Log.INFO);
	}
	
	protected void setLog(String mess, int prior){
		switch (prior) {
		case Log.DEBUG: Log.d(TAG, mess); break;
		case Log.ERROR: Log.e(TAG, mess); break;
		case Log.INFO: Log.i(TAG, mess); break;
		case Log.VERBOSE: Log.v(TAG, mess); break;
		case Log.WARN: Log.w(TAG, mess); break;
		default: Log.i(TAG, mess); break;
		}
	}
}
