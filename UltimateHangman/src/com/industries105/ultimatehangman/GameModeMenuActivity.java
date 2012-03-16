package com.industries105.ultimatehangman;

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

public class GameModeMenuActivity extends Activity {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        					 WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        
        setContentView(R.layout.game_mode_menu);
        
        // Selezione font
        Typeface font = Typeface.createFromAsset(getAssets(), "sigs.ttf");
        
        Button classicButton = (Button) findViewById(R.id.game_mode_classic_button);        
        classicButton.setTypeface(font);
        
        Button multiplayerButton = (Button) findViewById(R.id.game_mode_multiplayer_button);        
        multiplayerButton.setTypeface(font);
        
        Button arcadeButton = (Button) findViewById(R.id.game_mode_arcade_button);        
        arcadeButton.setTypeface(font);
        
        Button survivalButton = (Button) findViewById(R.id.game_mode_survival_button);        
        survivalButton.setTypeface(font);
        
        Button backButton = (Button) findViewById(R.id.game_mode_back_button);
        backButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				finish();
			}
		});
        
        classicButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(GameModeMenuActivity.this, ClassicGameActivity.class);
				startActivity(intent);
				//Toast toast = Toast.makeText(GameModeMenuActivity.this, "Not supported yet.", Toast.LENGTH_SHORT);
				//toast.show();
			}
		});
        
        
        multiplayerButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(GameModeMenuActivity.this, TwoPlayersGameActivity.class);
				startActivity(intent);
				//Toast toast = Toast.makeText(GameModeMenuActivity.this, "Not supported yet.", Toast.LENGTH_SHORT);
				//toast.show();
			}
		});

        
		arcadeButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Toast toast = Toast.makeText(GameModeMenuActivity.this, "Not supported yet.", Toast.LENGTH_SHORT);
				toast.show();
			}
		});
		
		
		survivalButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(GameModeMenuActivity.this, SurvivalGameActivity.class);
				startActivity(intent);
				//Toast toast = Toast.makeText(GameModeMenuActivity.this, "Not supported yet.", Toast.LENGTH_SHORT);
				//toast.show();
			}
		});
    }
    
    @Override
    protected void onPause() {
    	super.onPause();
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }
}