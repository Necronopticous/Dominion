package euler.problems;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem42 extends Problem {

	int solve() {
		List<String> words = getWords();
		int count = 0;
		for(String word : words) {
			if(isTriangleWord(word)) {
				count++;
				System.out.println(word + " (" + getWordValue(word) + ")");
			}
		}
		return count;
	}

	List<String> getWords() {
		File file = new File(this.getClass().getResource("/Problem42.txt").getPath());
		Scanner scanner = null;
		try { scanner = new Scanner(file); } catch(Exception e) {}
		scanner.useDelimiter("\",?+\"?+");
		List<String> words = new ArrayList<String>();
		while(scanner.hasNext()) {
			words.add(scanner.next());
		}
		return words;
	}
	
	public static void main(String[] args) {
		Problem42 p = new Problem42();
		System.err.println(p.solve());
	}

}
