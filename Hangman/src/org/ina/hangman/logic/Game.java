package org.ina.hangman.logic;

public class Game {
	private static final int MAX_ERRORS = 5; 
	
	private Word _word;
	private int _errors;
	
	public Game(WordRepository repository) {
		String s = repository.getWord();
		_word = new Word(s);
		_errors = 0;
	}
	
	public boolean Guess(char c) {
		boolean isThere = _word.Guess(c);
		
		if(!isThere) {
			_errors++;
		}
		
		return isThere;
	}
	
	public char[] getOutputWord() {
		//clone necessario per difendersi da modifiche
		return _word.getOutputWord();
	}
	
	public boolean Win() {
		//controlliamo ogni carattere della stringa
		//se c'è un _, non hai ancora vinto
		for(char c : _word.getOutputWord())
			if (c == '_')
				return false;
		return true;
	}
	
	public boolean Lose() {
		return _errors >= MAX_ERRORS;
	}
}
