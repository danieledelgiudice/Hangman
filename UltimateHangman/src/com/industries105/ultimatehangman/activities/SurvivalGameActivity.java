package com.industries105.ultimatehangman.activities;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.industries105.ultimatehangman.R;

public class SurvivalGameActivity extends HangmanGameActivity {
	
	private int score;
	
	protected void onLose() {
		String s = "You lost! The word was " + game.getSolution() + ". You made " + score + " points.";
		Toast toast = Toast.makeText(SurvivalGameActivity.this, s, Toast.LENGTH_LONG);
		toast.show();
		finish();
	}

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