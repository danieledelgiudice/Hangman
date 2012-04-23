package com.industries105.ultimatehangman.activities;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

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
		Toast toast = Toast.makeText(ArcadeGameActivity.this, "You won! Score: " + score, Toast.LENGTH_LONG);
		toast.show();
		newGame();
	}
	
	@Override
	protected void onLose() {
		String s = "You lost! The word was " + game.getSolution();
		Toast toast = Toast.makeText(ArcadeGameActivity.this, s, Toast.LENGTH_LONG);
		toast.show();
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