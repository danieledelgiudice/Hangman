package com.industries105.ultimatehangman.activities;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.industries105.ultimatehangman.R;
import com.industries105.ultimatehangman.helpers.SoundManager;

public class InfoActivity extends HangmanActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setupView();
	}

	private void setupView() {
		setContentView(R.layout.info);
		
        TextView tv = (TextView) findViewById(R.id.text_view);
        tv.setTypeface(font);
        
        Button backButton = (Button) findViewById(R.id.back_button);
        backButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				SoundManager.playSound(1, 1);
				finish();
			}
		});
	}
}
