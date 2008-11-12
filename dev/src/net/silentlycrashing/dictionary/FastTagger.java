package net.silentlycrashing.dictionary;

import com.knowledgebooks.nlp.fasttag.*;
import com.knowledgebooks.nlp.util.*;

public class FastTagger {
	private FastTag fastTag;
	
	public FastTagger() {
		fastTag = new FastTag();
	}
	
	public int[] tagString(String aString) {
		return tagWords(Tokenizer.wordsToArray(aString));
	}
	
	public int[] tagWords(String[] words) {
		String[] tempTags = fastTag.tag(words);
        int[] tags = new int[tempTags.length];
        for (int i=0; i < tags.length; i++) {
        	tags[i] = translateTag(tempTags[i]);
        }
        
        return tags;
	}
	
	public int translateTag(String aTag) {
		if (aTag.startsWith("NN")) {
			return Meaning.NOUN;
		} else if (aTag.startsWith("JJ")) {
			return Meaning.ADJ;
		} else if (aTag.startsWith("RB")) {
			return Meaning.ADV;
		} else if (aTag.startsWith("VB")) {
			return Meaning.VERB;
		}
		return Meaning.UNKNOWN;
	}
}
