package com.industries105.ultimatehangman;

import com.industries105.ultimatehangman.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainMenuActivity extends Activity {
	private boolean audioOn = true;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        					 WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.main_menu);
        
        // Selezione font
        Typeface font = Typeface.createFromAsset(getAssets(), "sigs.ttf");
        
        Button playButton = (Button) findViewById(R.id.main_menu_play_button);        
        playButton.setTypeface(font);
        
        Button optionButton = (Button) findViewById(R.id.main_menu_option_button);
        optionButton.setTypeface(font);
        
        // Handler audio button
        Button audioButton = (Button) findViewById(R.id.main_menu_sound_button);
        audioButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				if(audioOn) {
					v.setBackgroundResource(R.drawable.audio_off);
					audioOn = false;
				} else {
					v.setBackgroundResource(R.drawable.audio_on);
					audioOn = true;
				}
			}
        });
        
        //Handler info button
        Button infoButton = (Button) findViewById(R.id.main_menu_info_button);
        infoButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(MainMenuActivity.this, InfoActivity.class);
				startActivity(intent);
			}
		});
        
        //Handler play button
        playButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(MainMenuActivity.this, GameModeMenuActivity.class);
				startActivity(intent);
			}
		});
        
        //Handler option button
        optionButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Toast toast = Toast.makeText(MainMenuActivity.this, "Options not supported yet.", Toast.LENGTH_SHORT);
				toast.show();
			}
		});
    }
}