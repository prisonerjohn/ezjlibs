package net.silentlycrashing.dictionary;

import java.util.*;
import net.silentlycrashing.util.*;

public class Meaning {
	public static final int UNKNOWN = -1;
	public static final int NOUN = 0;
	public static final int ADJ = 1;
	public static final int ADV = 2;
	public static final int VERB = 3;
	
	private int type;
	private Term[] terms;
	
	public Meaning(String line, ArrayList[] dumbList) {
		String[] pieces = line.split("\\|");
		// set the type
		String typeString = pieces[0].split("\\(")[1];
		//System.out.println(pieces[0]);
		//String[] tempshit = pieces[0].split("\\(");
		//for (int i=0;i<tempshit.length;i++) {
		//	System.out.println(tempshit[i]);
		//}
		
		if (typeString.startsWith("noun")) {
			type = NOUN;
		} else if (typeString.startsWith("adj")) {
			type = ADJ;
		} else if (typeString.startsWith("adv")) {
			type = ADV;
		} else if (typeString.startsWith("verb")) {
			type = VERB;
		} else {
			System.out.println("PROBLEM with meaning > "+ typeString);
		}
		
		// add the terms
		terms = new Term[pieces.length-1];
		for (int i=1; i < pieces.length; i++) {
			terms[i-1] = new Term(pieces[i]);
			dumbList[type].add(terms[i-1].getValue());
		}
	}
	
	public int getType() { return type; }
	public Term getSomeTerm() { return terms[MiscMath.randRange(0, terms.length)]; }
}
