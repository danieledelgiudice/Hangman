package com.industries105.ultimatehangman;

import java.io.InputStream;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.industries105.ultimatehangman.logic.Game;
import com.industries105.ultimatehangman.logic.RandomWordRepository;
import com.industries105.ultimatehangman.logic.WordRepository;

public class ClassicGameActivity extends Activity {
	private Game game;
	private Typeface font;
	
	//Keyboard
    private OnClickListener keyPressed = new OnClickListener() {
		
		public void onClick(View v) {
			TextView tv = (TextView) v;
			tv.setEnabled(false);
			game.guess(tv.getText().charAt(0));
			
			updateWordLayout();
			updateHangman();
			
			if(game.win()) {
				Toast toast = Toast.makeText(ClassicGameActivity.this, "You won!", Toast.LENGTH_LONG);
				toast.show();
				finish();
			}
			
			if(game.lose()) {
				String s = "You lost! The word was " + game.getSolution();
				Toast toast = Toast.makeText(ClassicGameActivity.this, s, Toast.LENGTH_LONG);
				toast.show();
				finish();
			}
		}
	};
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setupView();
        setupGame();
        setupWordLayout();
    }
    
    private void updateHangman() {
		ImageView hangman = (ImageView) findViewById(R.id.classic_game_hangman);
		hangman.setImageResource(R.drawable.hm0 + game.getErrors());
	}
    
    private void updateWordLayout() {
    	char[] word = game.getOutputWord();
		int wordLen = word.length;
		
		LinearLayout wordLayout = (LinearLayout) findViewById(R.id.classic_game_word);
		
		for(int i = 0; i < wordLen; i++)
		{
			TextView tv = (TextView) wordLayout.getChildAt(i);
			tv.setText(Character.toString(word[i]));
		}
    }

	private void setupWordLayout() {
		char[] word = game.getOutputWord();
		int wordLen = word.length;
		
		LinearLayout wordLayout = (LinearLayout) findViewById(R.id.classic_game_word);
		
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

	private void setupGame() {
		InputStream is = getResources().openRawResource(R.raw.wordlist);
		WordRepository repo = new RandomWordRepository(is);
		game = new Game(repo);
	}

	private void setupView() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        					 WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        
        setContentView(R.layout.classic_game);
        
        // Selezione font
        font = Typeface.createFromAsset(getAssets(), "sigs.ttf");
        
        
        //Back button
        Button backButton = (Button) findViewById(R.id.classic_game_back_button);
        backButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				finish();
			}
		});
        
        Button retryButton = (Button) findViewById(R.id.classic_game_retry_button);
        retryButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = getIntent();
				finish();
				startActivity(intent);
			}
		});
        
        TableLayout keyboard = (TableLayout) findViewById(R.id.classic_game_keyboard);
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
    
    @Override
    protected void onPause() {
    	super.onPause();
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }
}