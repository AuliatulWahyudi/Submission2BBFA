package com.yudi.submission2bbfa;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

	private int loadingSplash = 3000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {

				Intent login=new Intent(SplashScreenActivity.this, MainActivity.class);
				startActivity(login);
				finish();

			}
		}, loadingSplash);
	}
}