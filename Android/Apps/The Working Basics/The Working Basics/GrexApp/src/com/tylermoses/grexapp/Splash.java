package com.tylermoses.grexapp;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
//import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class Splash extends Activity{
	MediaPlayer ourSong ;
	@Override
	protected void onCreate(Bundle Moats) {
		// TODO Auto-generated method stub
		super.onCreate(Moats);
		setContentView(R.layout.splash); // set layout to splash.xml
		ourSong = MediaPlayer.create(Splash.this, R.raw.splashsound); // play the splash sound
		SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		boolean music = getPrefs.getBoolean("checkbox", true);
		if(music == true) {
		ourSong.start();
		}
		Thread timer = new Thread(){
			public void run(){
				try {
					sleep(1000); //was 5000 aka 5s now 1s
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					Intent openMainActivity = new Intent("com.tylermoses.grexapp.MENU");
					startActivity(openMainActivity);
				}
			}
		};
		timer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		ourSong.release();
		finish();
	}
	
	
}
