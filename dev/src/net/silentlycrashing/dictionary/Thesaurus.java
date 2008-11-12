package net.silentlycrashing.dictionary;

import java.io.*;
import java.util.*;

/**
 * A Thesaurus built using OpenOffice.org's Thesaurus index and data files.
 * <P>These files are usually called something like 'th_en_US_new.idx' for the index and 'th_en_US_new.dat' for the data.</P>
 */
public class Thesaurus {
	private HashMap<String, Integer> index;
	private RandomAccessFile data;
	
	public Thesaurus() {
		this("resources/th_en_US_new.idx", "resources/th_en_US_new.dat");
	}
	
	public Thesaurus(String indexFile, String dataFile) {
		index = new HashMap<String, Integer>();
		
		// open the files
		try {
			BufferedReader br = new BufferedReader(new FileReader(indexFile));
			loadIndex(br);
			data = new RandomAccessFile(dataFile, "r");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void loadIndex(BufferedReader br) {
		String line;
		String[] segments;
		try {
			while ((line = br.readLine()) != null) { 
				segments = line.split("\\|");
				// only consider the actual index lines
				if (segments.length == 2) {
					index.put(segments[0], Integer.parseInt(segments[1]));
				}
			} 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Word getWord(String w) throws WordNotFoundException {
		// find the word in the index
		Integer offset = index.get(w);
		if (offset == null) {
			// the word does not exist in the thesaurus
			throw new WordNotFoundException(w);
		}
		
		// find the word in the data
		try {
			data.seek(offset);
			String line = data.readLine();
			String[] segments = line.split("\\|");
			int meaningCount = Integer.parseInt(segments[1]);
			Word word = new Word(segments[0], meaningCount);
			
			// read in all the meanings
			for (int i=0; i < meaningCount; i++) {
				word.addMeaning(data.readLine());
			}
			
			return word;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// something fucked up... this should never happen
		return null;
	}
}
