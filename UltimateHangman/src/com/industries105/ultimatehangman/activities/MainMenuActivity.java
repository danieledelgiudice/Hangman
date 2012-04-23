package com.industries105.ultimatehangman.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

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
        
        Button optionButton = (Button) findViewById(R.id.option_button);
        optionButton.setTypeface(font);
        
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
        
        //Handler option button
        optionButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				SoundManager.playClick();
				Toast toast = Toast.makeText(MainMenuActivity.this, "Options not supported yet.", Toast.LENGTH_SHORT);
				toast.show();
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