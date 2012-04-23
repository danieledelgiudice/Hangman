package com.industries105.ultimatehangman.activities;

import android.app.Activity;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.industries105.ultimatehangman.R;

public abstract class HangmanActivity extends Activity {
	
    protected static final String PREFS_NAME = "UltimateHangmanSettings";
	protected Typeface font;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        					 WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setVolumeControlStream(AudioManager.STREAM_MUSIC); //volume buttons should control application sounds
        
        font = Typeface.createFromAsset(getAssets(), "sigs.ttf");
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		
		overridePendingTransition(R.anim.fadein, R.anim.fadeout);
	}
}
