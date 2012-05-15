package com.industries105.ultimatehangman.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.industries105.ultimatehangman.R;
import com.industries105.ultimatehangman.helpers.SoundManager;

public class MainMenuActivity extends HangmanActivity {
	
	private boolean isSilenced;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupSound();
        setupView();
    }

	private void setupView() {
		setContentView(R.layout.main_menu);
		
		Button playButton = (Button) findViewById(R.id.play_button);        
        playButton.setTypeface(font);
        
        Button scoresButton = (Button) findViewById(R.id.scores_button);
        scoresButton.setTypeface(font);
        
        // Handler audio button
        Button audioButton = (Button) findViewById(R.id.sound_button);
        audioButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				toggleSound();
			}
        });
        
        // Audio button background
        audioButton.setBackgroundResource(isSilenced ? R.drawable.audio_off : R.drawable.audio_on);
        
        //Handler info button
        Button infoButton = (Button) findViewById(R.id.info_button);
        infoButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				SoundManager.playClick();
				Intent intent = new Intent(MainMenuActivity.this, InfoActivity.class);
				startActivity(intent);
			}
		});
        
        //Handler play button
        playButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				SoundManager.playClick();
				Intent intent = new Intent(MainMenuActivity.this, GameModeMenuActivity.class);
				startActivity(intent);
			}
		});
        
        //Handler scores button
        scoresButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				SoundManager.playClick();
				Intent intent = new Intent(MainMenuActivity.this, BestScoresActivity.class);
				startActivity(intent);
			}
		});
        
        
        
      
	}

	private void setupSound() {
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        isSilenced = settings.getBoolean("isSilenced", false);
        
        SoundManager.getInstance();
        SoundManager.initSounds(this);
        SoundManager.loadSounds();        
        SoundManager.setSilenced(isSilenced);
	}
    
    private void toggleSound() {    	
    	SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
    	boolean isSilenced = settings.getBoolean("isSilenced", false);
    	
    	isSilenced = !isSilenced;
    	SoundManager.setSilenced(isSilenced);
    	
    	SharedPreferences.Editor editor = settings.edit();
    	editor.putBoolean("isSilenced", isSilenced);
    	editor.commit();
    	
    	Button audioButton = (Button) findViewById(R.id.sound_button);
    	audioButton.setBackgroundResource(isSilenced ? R.drawable.audio_off : R.drawable.audio_on);
	}
}