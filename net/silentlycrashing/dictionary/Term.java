package net.silentlycrashing.dictionary;

public class Term {
	public static enum TYPE { NORMAL, GENERIC, SIMILAR, RELATED, ANTONYM };

	private String value;
	private TYPE type;
	
	public Term(String segment) {
		String[] pieces = segment.split("\\(");
		value = pieces[0].trim();
		// set the type
		if (pieces.length > 1) {
			if (pieces[1].startsWith("generic")) {
				type = TYPE.GENERIC;
			} else if (pieces[1].startsWith("similar")) {
				type = TYPE.SIMILAR;
			} else if (pieces[1].startsWith("related")) {
				type = TYPE.RELATED;
			} else if (pieces[1].startsWith("antonym")) {
				type = TYPE.ANTONYM;
			} else {
				System.out.println("PROBLEM with "+value+" > "+pieces[1]);
			}
		} else {
			type = TYPE.NORMAL;
		}
	}
	
	public String getValue() { return value; }
	public TYPE getType() { return type; }
}
