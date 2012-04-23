package com.industries105.ultimatehangman;

import java.io.InputStream;
import java.util.HashSet;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.Gravity;
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

public class ArcadeGameActivity extends Activity {
	private static final int ARCADE_TIME = 120;
	private Game game;
	private Typeface font;	
	protected TextView timeTextView;	
	private Handler handler;
	private long startTime;
	private int score;
	
	//Keyboard
    private OnClickListener keyPressed = new OnClickListener() {
		
		public void onClick(View v) {
			SoundManager.playClick();
			TextView tv = (TextView) v;
			tv.setEnabled(false);
			game.guess(tv.getText().charAt(0));
			
			updateWordLayout();
			updateHangman();
			
			//debug instruction
			if(tv.getText().charAt(0) == 'Z')
				Toast.makeText(ArcadeGameActivity.this, game.getSolution(), Toast.LENGTH_LONG).show();
			//end debug instruction
			
			if(game.win()) {
				score += getScore(game.getSolution());
				Toast toast = Toast.makeText(ArcadeGameActivity.this, "You won! Score: " + score, Toast.LENGTH_LONG);
				toast.show();
				newGame();
				//finish();
			}
			
			if(game.lose()) {
				String s = "You lost! The word was " + game.getSolution();
				Toast toast = Toast.makeText(ArcadeGameActivity.this, s, Toast.LENGTH_LONG);
				toast.show();
				finish();
			}
		}
	};
	
	private int getScore(String solution) {
		HashSet<Character> set = new HashSet<Character>();
		for(char c : solution.toCharArray())
			set.add(c);
		return set.size() * 10;
	}
	
	private Runnable timerTick = new Runnable() {
		
		public void run() {
			long millis = System.currentTimeMillis() - startTime;
			int seconds = ARCADE_TIME - (int) (millis / 1000);
			
			if(seconds >= 0) {
				//abbiamo ancora tempo
				timeTextView.setText(seconds + "s");
				handler.postDelayed(timerTick, 200);
			} else {
				String s = "You lost! The word was " + game.getSolution();
				Toast toast = Toast.makeText(ArcadeGameActivity.this, s, Toast.LENGTH_LONG);
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
        newGame();
        startTimer();
    }

	private void newGame() {
		setupGame();
        setupWordLayout();
        enableKeyboard();
	}
    
    private void startTimer() {
		startTime = System.currentTimeMillis();
		handler = new Handler();
		handler.postDelayed(timerTick, 200);
	}

	private void updateHangman() {
		ImageView hangman = (ImageView) findViewById(R.id.hangman);
		hangman.setImageResource(R.drawable.hm0 + game.getErrors());
	}
    
    private void updateWordLayout() {
    	char[] word = game.getOutputWord();
		int wordLen = word.length;
		
		LinearLayout wordLayout = (LinearLayout) findViewById(R.id.word);
		
		for(int i = 0; i < wordLen; i++)
		{
			TextView tv = (TextView) wordLayout.getChildAt(i);
			tv.setText(Character.toString(word[i]));
		}
    }

	private void setupWordLayout() {
		char[] word = game.getOutputWord();
		int wordLen = word.length;
		
		LinearLayout wordLayout = (LinearLayout) findViewById(R.id.word);
		wordLayout.removeAllViews();
		
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
			tv.setGravity(Gravity.CENTER_HORIZONTAL);
			
			wordLayout.addView(tv);
		}
	}

	private void setupGame() {
		InputStream is = getResources().openRawResource(R.raw.wordlist);
		WordRepository repo = new RandomWordRepository(is);
		game = new Game(repo);
	}
	
	private void enableKeyboard()
    {
		TableLayout keyboard = (TableLayout) findViewById(R.id.keyboard);
        for(int i = 0; i < 3; i++)
        {
        	TableRow row = (TableRow) keyboard.getChildAt(i);
        	int count = row.getChildCount();
        	for(int j = 0; j < count; j++)
        	{
        		//Key initialization
        		TextView tv = (TextView) row.getChildAt(j);
        		tv.setEnabled(true);
        	}
        }
    }

	private void setupView() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        					 WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setVolumeControlStream(AudioManager.STREAM_MUSIC); //volume buttons should control application sounds
        
        setContentView(R.layout.arcade_game);
        
        // Selezione font
        font = Typeface.createFromAsset(getAssets(), "sigs.ttf");
        
        
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
        
        timeTextView = (TextView) findViewById(R.id.time);
        timeTextView.setTypeface(font);
        
        TextView timeLabelTextView = (TextView) findViewById(R.id.time_label);
        timeLabelTextView.setTypeface(font); 
	}
    
    @Override
    protected void onPause() {
    	super.onPause();
    	handler.removeCallbacks(timerTick);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }    
}