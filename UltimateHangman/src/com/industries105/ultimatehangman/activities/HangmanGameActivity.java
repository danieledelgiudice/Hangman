package com.industries105.ultimatehangman.activities;

import java.io.InputStream;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.industries105.ultimatehangman.R;
import com.industries105.ultimatehangman.helpers.SoundManager;
import com.industries105.ultimatehangman.logic.Game;
import com.industries105.ultimatehangman.logic.RandomWordRepository;
import com.industries105.ultimatehangman.logic.WordRepository;

public abstract class HangmanGameActivity extends HangmanActivity {
	
	protected Game game;
	
	//Keyboard
    private OnClickListener keyPressed = new OnClickListener() {
		
		public void onClick(View v) {
			SoundManager.playClick();
			TextView tv = (TextView) v;
			tv.setEnabled(false);
			game.guess(tv.getText().charAt(0));
			
			updateWordLayout();
			updateHangman();
			
			if(game.win()) {
				onWin();
			}
			
			if(game.lose()) {
				onLose();
			}
		}
	};
	
	protected abstract void onLose();
	protected abstract void onWin();

	public void onCreate(Bundle savedInstanceState, int layoutResId) {
        super.onCreate(savedInstanceState);
        
        setupView(layoutResId);
        setupGame();
        setupWordLayout();
        
        // debug
        if(game != null)
        	Toast.makeText(this, game.getSolution(), Toast.LENGTH_SHORT).show();
        // end debug
    }
    
    protected void updateHangman() {
		ImageView hangman = (ImageView) findViewById(R.id.hangman);
		hangman.setImageResource(R.drawable.hm0 + game.getErrors());
	}
    
    protected void updateWordLayout() {
    	char[] word = game.getOutputWord();
		int wordLen = word.length;
		
		LinearLayout wordLayout = (LinearLayout) findViewById(R.id.word);
		
		for(int i = 0; i < wordLen; i++)
		{
			TextView tv = (TextView) wordLayout.getChildAt(i);
			tv.setText(Character.toString(word[i]));
		}
    }

	protected void setupWordLayout() {
		char[] word = game.getOutputWord();
		int wordLen = word.length;
		
		LinearLayout wordLayout = (LinearLayout) findViewById(R.id.word);
		
		for(int i = 0; i < wordLen; i++)
		{
			TextView tv = new TextView(this);
			
			final float scale = getResources().getDisplayMetrics().density;
			int width = (int) (25 * scale + 0.5f);
			LayoutParams params = new LayoutParams(width, LayoutParams.WRAP_CONTENT);
			tv.setLayoutParams(params);
			tv.setText(Character.toString(word[i]));
			tv.setTextColor(Color.WHITE);
			tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
			tv.setTypeface(font);
			
			wordLayout.addView(tv);
		}
	}

	protected void setupGame() {
		InputStream is = getResources().openRawResource(R.raw.wordlist);
		WordRepository repo = new RandomWordRepository(is);
		game = new Game(repo);
	}

	protected void setupView(int layoutResId) {
		setContentView(layoutResId);
        
        //Back button
        Button backButton = (Button) findViewById(R.id.back_button);
        backButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				SoundManager.playClick();
				finish();
			}
		});
        
        Button retryButton = (Button) findViewById(R.id.retry_button);
        retryButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				SoundManager.playClick();
				Intent intent = getIntent();
				finish();
				startActivity(intent);
			}
		});
        
        setupKeyboard();
	}

	private void setupKeyboard() {
		TableLayout keyboard = (TableLayout) findViewById(R.id.keyboard);
        for(int i = 0; i < 3; i++)
        {
        	TableRow row = (TableRow) keyboard.getChildAt(i);
        	int count = row.getChildCount();
        	for(int j = 0; j < count; j++)
        	{
        		//Key initialization
        		TextView tv = (TextView) row.getChildAt(j);
        		tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
        		tv.setTypeface(font);
        		tv.setOnClickListener(keyPressed);
        	}
        }
	}
}