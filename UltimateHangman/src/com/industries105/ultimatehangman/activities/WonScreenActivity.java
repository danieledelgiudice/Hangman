package com.industries105.ultimatehangman.activities;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.industries105.ultimatehangman.R;

public class WonScreenActivity extends HangmanActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
        setupView();
	}

	private void setupView() {
		setContentView(R.layout.won_screen);
		
		TextView winTextView = (TextView) findViewById(R.id.you_win);
        winTextView.setTypeface(font);
        
        Button restartButton = (Button) findViewById(R.id.restart);
        restartButton.setTypeface(font);
        
        Button backToModeButton = (Button) findViewById(R.id.back_to_mode_selection_button);
        backToModeButton.setTypeface(font);
        backToModeButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				finish();
			}
		});
	}
}
