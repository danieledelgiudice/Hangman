package com.industries105.ultimatehangman.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.industries105.ultimatehangman.R;

public class ArcadeGameActivity extends HangmanGameActivity {
	private static final int ARCADE_TIME = 120;
	protected TextView timeTextView;	
	private Handler handler;
	private long startTime;
	private int score;
	
	@Override
	protected void onWin() {
		score += game.getWordScore();
		updateScore();
		newGame();
	}
	
	@Override
	protected void onLose() {
		Intent intent = new Intent(ArcadeGameActivity.this, GameOverScreenActivity.class);
		intent.putExtra("callingActivity", ArcadeGameActivity.class);
		intent.putExtra("score", score);
		
		//check for best score
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		int bestScore = settings.getInt("bestArcadeScore", 0);
		
		if(score > bestScore)
		{
			intent.putExtra("bestScore", true);
			SharedPreferences.Editor editor = settings.edit();
			editor.putInt("bestArcadeScore", score);
			editor.commit();
		}
		
		startActivity(intent);
		finish();
	}
	
	private Runnable timerTick = new Runnable() {
		
		public void run() {
			long millis = System.currentTimeMillis() - startTime;
			int seconds = ARCADE_TIME - (int) (millis / 1000);
			
			if(seconds >= 0) {
				//abbiamo ancora tempo
				timeTextView.setText(seconds + "");
				handler.postDelayed(timerTick, 200);
			} else {
				onLose();
			}
		}
	};
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.arcade_game);

        startTimer();
    }
    
    @Override
    protected void setupView() {
    	super.setupView();
    	
    	timeTextView = (TextView) findViewById(R.id.time);
        timeTextView.setTypeface(font);
        
        TextView timeLabelTextView = (TextView) findViewById(R.id.time_label);
        timeLabelTextView.setTypeface(font);
        
        TextView scoreTextView = (TextView) findViewById(R.id.score);
        scoreTextView.setTypeface(font);
        
        TextView scoreLabelTextView = (TextView) findViewById(R.id.score_label);
        scoreLabelTextView.setTypeface(font);
    }
    
    private void updateScore() {
    	TextView scoreTextView = (TextView) findViewById(R.id.score);
    	scoreTextView.setText(String.valueOf(score));
    }
    
    private void startTimer() {
		startTime = System.currentTimeMillis();
		handler = new Handler();
		handler.postDelayed(timerTick, 200);
	}
    
    @Override
    protected void onPause() {
    	super.onPause();
    	handler.removeCallbacks(timerTick);
    }
}