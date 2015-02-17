package com.josuebasurto.Activities;

import java.util.Date;

import com.josuebasurto.common.stringHelper;

import android.R.bool;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

public class BaseGeneralActivity extends Activity {
	
	private String TAG = "NO_TAG_SET";
	
	protected boolean HayInternet() {
		try {
			ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	  		return activeNetworkInfo != null;
		} catch (Exception e) {
			Toasty("Ups! " + e.getMessage());
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
	
	protected void setDateKeyValue(int Key, Date value) {
		setStringKeyValue(Key, stringHelper.DateToString(value));
	}
	
	protected void setIntKeyValue(int Key, int value)
	{
		SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();
		
		editor.putInt(getString(Key), value);
		editor.commit();
		
		setLog("Valor Almacenado [" + getString(Key) + "|" + value + "]");
	}
	
	protected void setStringKeyValue(int Key, String value)
	{
		SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();
		
		editor.putString(getString(Key), value);
		editor.commit();
		
		setLog("Valor Almacenado [" + getString(Key) + "|" + value + "]");
	}
	
	protected void setBooleanKeyValue(int Key, Boolean value)
	{
		SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();
		
		editor.putBoolean(getString(Key), value);
		editor.commit();
		
		setLog("Valor Almacenado [" + getString(Key) + "|" + value + "]");
	}
	
	protected String getStringKeyValue(int Key, String defValue)
	{
		SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
		return sharedPref.getString(getString(Key), defValue);		
	}
	
	protected Date getDateKeyValue(int Key, Date defValue)
	{
		SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
		String respuesta = sharedPref.getString(getString(Key),stringHelper.DateToString(defValue));
		if (respuesta == stringHelper.DateToString(defValue))
			return defValue;
		else
		{
			try {
				return stringHelper.StringToDate(respuesta);
			} catch (Exception e) {
				return defValue;
			}
		}
	}
	
	protected int getIntKeyValue(int Key, int defValue)
	{
		SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
		return sharedPref.getInt(getString(Key), defValue);		
	}

	protected boolean getBoolKeyValue(int Key, boolean defValue){
		SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
		return sharedPref.getBoolean(getString(Key), defValue);		
	}
}
