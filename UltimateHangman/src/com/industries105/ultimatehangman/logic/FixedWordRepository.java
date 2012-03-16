package com.industries105.ultimatehangman.logic;

public class FixedWordRepository implements WordRepository {
	private String word;
	
	public FixedWordRepository(String word) {
		this.word = word;
	}
	
	public String getWord() {
		return word;
	}

}
