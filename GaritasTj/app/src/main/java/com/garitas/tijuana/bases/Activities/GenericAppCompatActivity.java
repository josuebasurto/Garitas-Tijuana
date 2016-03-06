package com.garitas.tijuana.bases.Activities;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by josuebasurto on 3/6/16.
 */
public class GenericAppCompatActivity extends AppCompatActivity {

	protected void Toasty(String text) {
		Toast.makeText(this, text, Toast.LENGTH_LONG).show();
	}
}
