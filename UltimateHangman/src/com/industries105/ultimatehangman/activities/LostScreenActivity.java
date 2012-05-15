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

public class LostScreenActivity extends HangmanActivity {
	
	private Class<? extends Activity> previous;
	private String word;
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		previous = (Class<? extends Activity>) getIntent().getSerializableExtra("callingActivity"); 
		word = getIntent().getStringExtra("solution");
		
        setupView();
	}

	private void setupView() {
		setContentView(R.layout.lost_screen);
		
		TextView lostTextView = (TextView) findViewById(R.id.text);
        lostTextView.setTypeface(font);
        
        TextView wordTextView = (TextView) findViewById(R.id.word);
        wordTextView.setTypeface(font);
        wordTextView.setText(word);
        
        Button restartButton = (Button) findViewById(R.id.restart);
        restartButton.setTypeface(font);
        restartButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				SoundManager.playClick();
				Intent intent = new Intent(LostScreenActivity.this, previous);
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
        
        loadAds();
	}
}
