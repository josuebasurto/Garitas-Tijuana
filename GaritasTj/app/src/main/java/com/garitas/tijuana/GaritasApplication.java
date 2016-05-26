package com.garitas.tijuana;

import android.app.Application;

/**
 * Created by josuebasurto on 5/25/16.
 */
public class GaritasApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
	}

	@Override
	public void onTrimMemory(int level) {
		super.onTrimMemory(level);
	}
}
