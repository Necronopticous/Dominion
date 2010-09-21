package euler.problems;

import java.util.HashSet;
import java.util.Set;

public class Problem29 extends Problem {
	
	public static final int FIRST = 2;
	public static final int LAST = 100;
	
	public int solve() {
		Set<Double> terms = new HashSet<Double>();
		for(int a = FIRST; a <= LAST; a++) {
			for(int b = FIRST; b <= LAST; b++) {
				terms.add(Math.pow(a,b));
			}
		}
		return terms.size();
	}
	
	public static void main(String[] args) {
		Problem29 p = new Problem29();
		System.err.println(p.solve());
	}
	
}
