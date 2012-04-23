package com.industries105.ultimatehangman.activities;

import com.industries105.ultimatehangman.R;

import android.app.Activity;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class WonScreenActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        					 WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setVolumeControlStream(AudioManager.STREAM_MUSIC); //volume buttons should control application sounds
        
		setContentView(R.layout.won_screen);
		
		// Selezione font
        Typeface font = Typeface.createFromAsset(getAssets(), "sigs.ttf");
        
        TextView winTextView = (TextView) findViewById(R.id.you_win);
        winTextView.setTypeface(font);
        
        Button restartButton = (Button) findViewById(R.id.restart);
        restartButton.setTypeface(font);
        
        Button backToModeButton = (Button) findViewById(R.id.back_to_mode_selection_button);
        backToModeButton.setTypeface(font);
        backToModeButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				finish();
			}
		});
        
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		
		overridePendingTransition(R.anim.fadein, R.anim.fadeout);
	}
}
