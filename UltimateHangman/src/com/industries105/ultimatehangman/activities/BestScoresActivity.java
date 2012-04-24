package com.industries105.ultimatehangman.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.industries105.ultimatehangman.R;
import com.industries105.ultimatehangman.helpers.SoundManager;

public class BestScoresActivity extends HangmanActivity {
	
	private int bestSurvivalScore;
	private int bestArcadeScore;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setupView();
		updateScores();
	}
	
	private void resetScores() {
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putInt("bestSurvivalScore", 0);
		editor.putInt("bestArcadeScore", 0);
		editor.commit();
	}
	
	private void updateScores() {
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		bestSurvivalScore = settings.getInt("bestSurvivalScore", 0);
		bestArcadeScore = settings.getInt("bestArcadeScore", 0);
		
		TextView bestArcadeScoreTextView = (TextView) findViewById(R.id.arcade_score);
        bestArcadeScoreTextView.setText(Integer.toString(bestArcadeScore));
        
        TextView bestSurvivalScoreTextView = (TextView) findViewById(R.id.survival_score);
        bestSurvivalScoreTextView.setText(Integer.toString(bestSurvivalScore));
	}

	private void setupView() {
		setContentView(R.layout.best_scores);
		
        Button backButton = (Button) findViewById(R.id.back_button);
        backButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				SoundManager.playSound(1, 1);
				finish();
			}
		});
        
        Button resetButton = (Button) findViewById(R.id.reset_scores);
        resetButton.setTypeface(font);
        resetButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				resetScores();
				updateScores();
			}

		});
        
        TextView bestArcadeScoreTextView = (TextView) findViewById(R.id.arcade_score);
        bestArcadeScoreTextView.setTypeface(font);
        bestArcadeScoreTextView.setText(Integer.toString(bestArcadeScore));
        
        TextView bestSurvivalScoreTextView = (TextView) findViewById(R.id.survival_score);
        bestSurvivalScoreTextView.setTypeface(font);
        bestSurvivalScoreTextView.setText(Integer.toString(bestSurvivalScore));

        
        TextView bestArcadeScoreLabelTextView = (TextView) findViewById(R.id.arcade_score_label);
        bestArcadeScoreLabelTextView.setTypeface(font);
        
        TextView bestSurvivalScoreLabelTextView = (TextView) findViewById(R.id.survival_score_label);
        bestSurvivalScoreLabelTextView.setTypeface(font);
	}
}
