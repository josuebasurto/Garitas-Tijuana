package com.garitas.tijuana;

import android.app.Application;

import com.garitas.tijuana.MainActivity;
import com.parse.Parse;
import com.parse.PushService;

public class GaritasApplication extends Application {
	
	@Override
	public void onCreate() {
		Parse.initialize(this, "sKKihRL8D0Ny2kmuHqmOuZ7ciu6SilBIqPV29S6j", "vqGQjzVjzLtYk88qZpn4ECevgCuWwhTohcZPqaJm");
		PushService.setDefaultPushCallback(this, MainActivity.class);
	}
}
