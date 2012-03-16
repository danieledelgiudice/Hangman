package com.industries105.ultimatehangman.logic;

import java.util.*;

public class Word {
	private String _word;
	private List<Character> _said;
	private char _outputString[];
	
	public Word(String word) {
		_word = word;
		_said = new ArrayList<Character>();
		_outputString = new char[_word.length()];
		for(int i = 0; i < _outputString.length; i++)
			_outputString[i] = '_';
	}
	
	//metodo per verificare la presenza del carattere c
	//ritorna true se il carattere è presente, false altrimenti
	public boolean guess(char c) {
		//se la lettera è già stata detta, eccezione!
		if (_said.contains(c))
			throw new CharacterAlreadyPutException();
		//altrimenti, aggiungiamo alle lettere dette
		_said.add(c);
		//controlliamo se c'è
		boolean found = false;
		for(int i = 0; i < _word.length(); i++)
			if(_word.charAt(i) == c) {
				found = true;
				_outputString[i] = c;
			}
			
		return found;
	}
	
	//ritorna la stringa da mostrare
	public char[] getOutputWord() {
		//clone necessario per difendersi da modifiche
		return _outputString.clone();
	}
	
	public String getSolution() {
		return _word;
	}
	
	public static boolean isAValidWord(String s) {
		int len = s.length();
		if(len == 0 || len > 13)
			return false;
		for(int i = 0; i < len; i++)
			if(s.charAt(i) < 'A' || s.charAt(i) > 'Z')
				return false;
		return true;
	}
}
