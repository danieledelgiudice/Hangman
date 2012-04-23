package com.industries105.ultimatehangman.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.industries105.ultimatehangman.R;
import com.industries105.ultimatehangman.helpers.SoundManager;
import com.industries105.ultimatehangman.logic.FixedWordRepository;
import com.industries105.ultimatehangman.logic.Game;
import com.industries105.ultimatehangman.logic.Word;
import com.industries105.ultimatehangman.logic.WordRepository;

public class TwoPlayersGameActivity extends HangmanGameActivity {

	@Override
	protected void onLose() {
		Intent intent = new Intent(TwoPlayersGameActivity.this, LostScreenActivity.class);
		intent.putExtra("solution", game.getSolution());
		intent.putExtra("callingActivity", TwoPlayersGameActivity.class);
		startActivity(intent);
		finish();
	}

	@Override
	protected void onWin() {
		Intent intent = new Intent(TwoPlayersGameActivity.this, WonScreenActivity.class);
		intent.putExtra("callingActivity", TwoPlayersGameActivity.class);
		startActivity(intent);
		finish();
	}
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.classic_game);
    }

    @Override
    protected void setupGame() {
    	if(game != null) //if i have another game ready, nothing to do here
    		return;
    	
		showDialog();
		game = new Game(new FixedWordRepository("")); //temp game
	}

	private void showDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		builder.setTitle(R.string.two_players_prompt_title);
		builder.setMessage(R.string.two_players_prompt_message);
		
		final LinearLayout layout = new LinearLayout(this);
		final EditText input = new EditText(this);
		
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
																	 LinearLayout.LayoutParams.WRAP_CONTENT);
		final float scale = getResources().getDisplayMetrics().density;
		int margin = (int) (20 * scale + 0.5f);
		lp.setMargins(margin, 0, margin, margin);
		input.setLayoutParams(lp);
		
		layout.addView(input);
		
		builder.setView(layout);
		builder.setCancelable(false);
		
		builder.setPositiveButton(R.string.two_players_prompt_positive,
				new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						SoundManager.playClick();
						String word = input.getText().toString().toUpperCase();
						
						if(Word.isAValidWord(word))
						{
							WordRepository repo = new FixedWordRepository(word);
							game = new Game(repo);
					    	TwoPlayersGameActivity.super.newGame();
						} else {
							Toast toast = Toast.makeText(TwoPlayersGameActivity.this,
									 R.string.two_players_prompt_error, Toast.LENGTH_SHORT);
							toast.show();
							setupGame();
						}
					}
				});
		
		builder.setNegativeButton(R.string.two_players_prompt_negative,
				new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						SoundManager.playClick();
						finish();
					}
				});
		
		builder.show();
	}
}