package com.industries105.ultimatehangman.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class RandomWordRepository implements WordRepository {
	
	private ArrayList<String> words;
	private Random random;
	
	public RandomWordRepository(InputStream is) {
		random = new Random();
		words = new ArrayList<String>();
		
		BufferedReader buf = new BufferedReader(new InputStreamReader(is));
		String line;
		
		try {
			while((line = buf.readLine()) != null) {
				int length = line.length();
				if(length >= 3 && length <= 10)
					words.add(line.toUpperCase());
			}
			buf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getWord() {
		int index = random.nextInt(words.size());
		return words.get(index);
	}

}
