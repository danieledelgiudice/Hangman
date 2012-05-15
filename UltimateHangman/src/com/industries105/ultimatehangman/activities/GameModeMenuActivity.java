package com.industries105.ultimatehangman.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.industries105.ultimatehangman.R;
import com.industries105.ultimatehangman.helpers.SoundManager;

public class GameModeMenuActivity extends HangmanActivity {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {    	
    	super.onCreate(savedInstanceState);
    	
    	setContentView(R.layout.game_mode_menu);
        
        setupView();
    }

	private void setupView() {
		Button classicButton = (Button) findViewById(R.id.classic_button);        
        classicButton.setTypeface(font);
        
        Button multiplayerButton = (Button) findViewById(R.id.multiplayer_button);        
        multiplayerButton.setTypeface(font);
        
        Button arcadeButton = (Button) findViewById(R.id.arcade_button);        
        arcadeButton.setTypeface(font);
        
        Button survivalButton = (Button) findViewById(R.id.survival_button);        
        survivalButton.setTypeface(font);
        
        Button backButton = (Button) findViewById(R.id.back_button);
        backButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				SoundManager.playClick();
				finish();
			}
		});
        
        classicButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				SoundManager.playClick();
				Intent intent = new Intent(GameModeMenuActivity.this, ClassicGameActivity.class);
				startActivity(intent);
			}
		});
        
        
        multiplayerButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				SoundManager.playClick();
				Intent intent = new Intent(GameModeMenuActivity.this, TwoPlayersGameActivity.class);
				startActivity(intent);
			}
		});

        
		arcadeButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				SoundManager.playClick();
				Intent intent = new Intent(GameModeMenuActivity.this, ArcadeGameActivity.class);
				startActivity(intent);;
			}
		});
		
		
		survivalButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				SoundManager.playClick();
				Intent intent = new Intent(GameModeMenuActivity.this, SurvivalGameActivity.class);
				startActivity(intent);
			}
		});
		
		loadAds();
	}
}