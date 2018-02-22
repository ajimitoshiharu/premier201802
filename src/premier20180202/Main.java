package premier20180202;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.java.sen.SenFactory;
import net.java.sen.StreamTagger;
import net.java.sen.StringTagger;
import net.java.sen.dictionary.Token;

public class Main {
	static String filePath = "c:\\workspace\\premier\\src\\data.txt";
	static String code = "UTF-8";
	static String textType = "名詞";

	public static void main(String[] args) throws IOException {

		StringTagger stringTagger = SenFactory.getStringTagger(null);
		Reader reader = new InputStreamReader(new FileInputStream(filePath), code);
		StreamTagger tagger = new StreamTagger(stringTagger, reader);
		
		// 繰り返し単語をセットするMap
		LinkedHashMap<String, Integer> map = new LinkedHashMap<String, Integer>();
		
		Token token = null;
		while (tagger.hasNext()) {
			token = tagger.next();
			String word = token.getSurface();
			String targetType =  token.getMorpheme().getPartOfSpeech().split("-")[0];
			if(textType.equals(targetType)){
				if (map.containsKey(word)) {
			        map.put(word, map.get(word) + 1);
			    } else {
			        map.put(word, 1);
			    }
			}
		}
		
		List<Entry<String, Integer>> sortMap = entriesSortedByValues(map);
		for (Entry<String, Integer> entry : sortMap) {
		    System.out.printf("%s %d%n", entry.getKey(), entry.getValue());
		}
	}
	
	private static <K,V extends Comparable<? super V>> List<Entry<K, V>> entriesSortedByValues(Map<K,V> map){
		List<Entry<K,V>> sortedEntries = new ArrayList<Entry<K,V>>(map.entrySet());

	    Collections.sort(sortedEntries, 
	            new Comparator<Entry<K,V>>() {
	                @Override
	                public int compare(Entry<K,V> e1, Entry<K,V> e2) {
	                    return e2.getValue().compareTo(e1.getValue());
	                }
	            }
	    );

	    return sortedEntries;
	}
}