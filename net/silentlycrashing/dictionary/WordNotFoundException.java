package net.silentlycrashing.dictionary;

public class WordNotFoundException extends Exception {
	public WordNotFoundException(String word) {
		super("Word '"+word+"' not found!");
	}
}
