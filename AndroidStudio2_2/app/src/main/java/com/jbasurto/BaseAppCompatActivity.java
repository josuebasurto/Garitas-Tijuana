package com.jbasurto;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by josue on 12/19/16.
 */

public class BaseAppCompatActivity extends AppCompatActivity {

	private String _tag;
	private Context mContext;

	public BaseAppCompatActivity() {
		_tag = "Untagged";
	}



	protected String getTag() {
		return _tag;
	}

	protected void setTag(String _tag) {
		this._tag = _tag;
	}

	protected void ShortToast(String message){
		Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT);
	}

	protected void LongToast(String message){
		Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
	}

	protected void Log(String message){
		Log.d(_tag, message);
	}

	protected boolean InternetConnectivity() {
		try {
			ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
			return activeNetworkInfo != null;
		} catch (Exception e) {
			Log.e(_tag,"Error on trying to get Internet existance.", e);
			ShortToast("Ups! " + e.getMessage());
		}
		return false;
	}

	protected String GetAppVersion(){
		try {
			PackageInfo _info = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
			return _info.versionName;
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
			return "";
		}
	}

	protected int GetVersionCode(){
		try {
			PackageInfo _info = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
			return _info.versionCode;
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
			return -1;
		}
	}

	protected void setContext(Context context) {
		this.mContext = context == null ? getApplicationContext() : context;
	}
}
