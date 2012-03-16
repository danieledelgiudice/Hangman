package com.industries105.ultimatehangman;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class InfoActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        					 WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
		
		setContentView(R.layout.info);
		
		// Selezione font
        Typeface font = Typeface.createFromAsset(getAssets(), "sigs.ttf");
        
        TextView tv = (TextView) findViewById(R.id.info_text_view);
        tv.setTypeface(font);
        
        Button backButton = (Button) findViewById(R.id.info_back_button);
        backButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				finish();
			}
		});
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		
		overridePendingTransition(R.anim.fadein, R.anim.fadeout);
	}
}
