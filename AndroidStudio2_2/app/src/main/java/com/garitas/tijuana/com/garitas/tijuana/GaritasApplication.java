package com.garitas.tijuana.com.garitas.tijuana;

import android.app.Application;
import android.util.Log;

/**
 * Created by josue on 12/19/16.
 */

public class GaritasApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		Log.d("GaritasTijuana", "+++ Garitas Tijuana App Created +++");
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
		Log.d("GaritasTijuana", "xxx Garitas Tijuana App Terminated xxx");
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
		Log.d("GaritasTijuana", "vvv Garitas Tijuana Low Memory Detected! vvv");
	}
}
