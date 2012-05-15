package com.industries105.ultimatehangman.activities;

import android.app.Activity;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
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
	
	protected void loadAds() {
		AdRequest adRequest = new AdRequest();
        adRequest.addTestDevice(AdRequest.TEST_EMULATOR);
        
        String adMobUnitId = getResources().getString(R.string.admob_unit_id);
        AdView adView = new AdView(this, AdSize.BANNER, adMobUnitId);
        
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.layout);
        RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        relativeParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        
        layout.addView(adView, relativeParams);
        
        adView.loadAd(adRequest);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		
		overridePendingTransition(R.anim.fadein, R.anim.fadeout);
	}
}
