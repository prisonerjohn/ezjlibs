package net.silentlycrashing.dictionary;

import java.util.*;
import net.silentlycrashing.util.*;

public class Word {
	private String value;
	private Meaning[] meanings;
	private ArrayList[] dumbSynonyms;
	private int count;
	
	public Word(String value, int numMeanings) {
		this.value = value;
		meanings = new Meaning[numMeanings];
		dumbSynonyms = new ArrayList[4];
		for (int i=0; i < 4; i++) {
			dumbSynonyms[i] = new ArrayList();
		}
		count = 0;
	}
	
	public void addMeaning(String line) {
		meanings[count] = new Meaning(line, dumbSynonyms);
		count++;
	}
	
	public String getDumbSynonym(int type) throws WordNotFoundException { 
		if (dumbSynonyms[type].size() < 1) {
			throw new WordNotFoundException(value);
		}
		return (String)dumbSynonyms[type].get(MiscMath.randRange(dumbSynonyms[type].size())); 
	}
	
	public Meaning getAnyMeaning() { return meanings[MiscMath.randRange(count)]; }
	public String getValue() { return value; }
}
