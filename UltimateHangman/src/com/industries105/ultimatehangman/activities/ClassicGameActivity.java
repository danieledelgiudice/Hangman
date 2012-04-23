package com.industries105.ultimatehangman.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.industries105.ultimatehangman.R;

public class ClassicGameActivity extends HangmanGameActivity {
	
	@Override
	protected void onLose() {
		String s = "You lost! The word was " + game.getSolution();
		Toast toast = Toast.makeText(ClassicGameActivity.this, s, Toast.LENGTH_LONG);
		toast.show();
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