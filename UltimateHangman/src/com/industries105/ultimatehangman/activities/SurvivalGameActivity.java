package com.industries105.ultimatehangman.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.industries105.ultimatehangman.R;

public class SurvivalGameActivity extends HangmanGameActivity {
	
	private int score;
	
	@Override
	protected void onLose() {
		
		Intent intent = new Intent(SurvivalGameActivity.this, GameOverScreenActivity.class);
		intent.putExtra("callingActivity", SurvivalGameActivity.class);
		intent.putExtra("score", score);
		
		//check for best score
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		int bestScore = settings.getInt("bestSurvivalScore", 0);
		
		if(score > bestScore)
		{
			intent.putExtra("bestScore", true);
			SharedPreferences.Editor editor = settings.edit();
			editor.putInt("bestSurvivalScore", score);
			editor.commit();
		}
		
		startActivity(intent);
		finish();
	}

	@Override
	protected void onWin() {
		score += 1;
		updateScore();
		newGame();
	}
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.survival_game);
    }
    
    private void updateScore() {
    	TextView scoreTextView = (TextView) findViewById(R.id.score);
    	scoreTextView.setText(String.valueOf(score));
    }

    @Override
	protected void setupView() {
		super.setupView();
		
        //Score label
        TextView scoreLabelTextView = (TextView) findViewById(R.id.score_label);
        scoreLabelTextView.setTypeface(font);
        
        //Score
        TextView scoreTextView = (TextView) findViewById(R.id.score);
        scoreTextView.setTypeface(font);
	}
}