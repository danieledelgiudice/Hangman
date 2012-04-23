package com.industries105.ultimatehangman.activities;

import android.content.Intent;
import android.os.Bundle;

import com.industries105.ultimatehangman.R;

public class ClassicGameActivity extends HangmanGameActivity {
	
	@Override
	protected void onLose() {
		Intent intent = new Intent(ClassicGameActivity.this, LostScreenActivity.class);
		intent.putExtra("solution", game.getSolution());
		intent.putExtra("callingActivity", ClassicGameActivity.class);
		startActivity(intent);
		finish();
	}

	@Override
	protected void onWin() {
		Intent intent = new Intent(ClassicGameActivity.this, WonScreenActivity.class);
		intent.putExtra("callingActivity", ClassicGameActivity.class);
		startActivity(intent);
		finish();
	}
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.classic_game);
    }
}