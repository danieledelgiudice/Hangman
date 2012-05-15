package com.industries105.ultimatehangman.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.industries105.ultimatehangman.R;
import com.industries105.ultimatehangman.helpers.SoundManager;

public class GameOverScreenActivity extends HangmanActivity {
	
	private Class<? extends Activity> previous;
	private boolean bestScore;
	private int score;
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
		previous = (Class<? extends Activity>) intent.getSerializableExtra("callingActivity"); 
		score = intent.getIntExtra("score", 0);
		bestScore = intent.getBooleanExtra("bestScore", false);
		
        setupView();
	}

	private void setupView() {
		setContentView(R.layout.game_over_screen);
		
		TextView text = (TextView) findViewById(R.id.text);
        text.setTypeface(font);
        
        Button restartButton = (Button) findViewById(R.id.restart);
        restartButton.setTypeface(font);
        restartButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				SoundManager.playClick();
				Intent intent = new Intent(GameOverScreenActivity.this, previous);
				startActivity(intent);
				finish();
			}
		});
        
        if(previous == null)
        	restartButton.setVisibility(View.GONE);
        
        Button backToModeButton = (Button) findViewById(R.id.back_to_mode_selection_button);
        backToModeButton.setTypeface(font);
        backToModeButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				SoundManager.playClick();
				finish();
			}
		});
        
        String s = getResources().getString(R.string.score_text);
        String output = String.format(s, score);
        
        TextView scoreTextView = (TextView) findViewById(R.id.score_text);
        scoreTextView.setTypeface(font);
        scoreTextView.setText(output);
        
        if(bestScore)
        {
        	TextView goTextView = (TextView) findViewById(R.id.text);
        	String bestScoreText = getResources().getString(R.string.best_score); 
        	goTextView.setText(bestScoreText);
        }
        
        loadAds();
	}
}
