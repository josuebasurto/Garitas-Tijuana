package com.garitas.tijuana.bases;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.garitas.tijuana.MainActivity;

/**
 * Created by josuebasurto on 3/6/16.
 */
public class GenericAppCompatActivity extends AppCompatActivity {

	protected void Toasty(String text) {
		Toast.makeText(this, text, Toast.LENGTH_LONG).show();
	}
}
