package com.industries105.ultimatehangman.logic;

//rappresenta un gioco ad impiccato
//una volta terminata la partita non è possibile più fare niente
public class Game {
	private static final int MAX_ERRORS = 6; 
	
	private Word _word;
	private int _errors;
	
	public Game(WordRepository repository) {
		String s = repository.getWord();
		_word = new Word(s);
		_errors = 0;
	}
	
	public boolean guess(char c) {
		if(lose() || win())
			throw new GameAlreadyFinishedException();
		
		boolean isThere = _word.guess(c);
		
		//se la lettera non è presente aumentiamo gli errori
		if(!isThere) {
			_errors++;
		}
		
		return isThere;
	}
	
	public char[] getOutputWord() {
		//clone necessario per difendersi da modifiche
		return _word.getOutputWord();
	}
	
	public String getSolution() {
		//ritorniamo la soluzione
		if(lose())
			return _word.getSolution();
		return null;		
	}
	
	public boolean win() {
		//controlliamo ogni carattere della stringa
		//se c'è un _, non hai ancora vinto
		for(char c : _word.getOutputWord())
			if (c == '_')
				return false;
		return true;
	}
	
	public boolean lose() {
		return _errors >= MAX_ERRORS;
	}
	
	public int getErrors() {
		return _errors;
	}
}
