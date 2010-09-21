package euler.problems;

import java.util.ArrayList;
import java.util.List;

public class Problem41 extends Problem {

	int solve() {
		List<Integer> permutations = getPandigitalPermutations(1, 7);
		for(Integer permutation : permutations) {
			if(isPrime(permutation)) return permutation;
		}
		return 0;
	}
	
	public List<Integer> getPandigitalPermutations(int from, int to) {
		return getPandigitalPermutations(from, to, false);
	}
	
	public List<Integer> getPandigitalPermutations(int from, int to, boolean ascending) {
		StringBuilder s = new StringBuilder();
		for(int i = ascending ? from : to; ascending ? i <= to : i >= from; i = i + (ascending ? 1 : -1)) {
			s.append(i);
		}
		List<Integer> pandigitalPermutations = new ArrayList<Integer>();
		for(String str : permute(s.toString())) {
			pandigitalPermutations.add(Integer.parseInt(str));
		}
		return pandigitalPermutations;
	}
	
	public List<String> permute(String s) {
		return permute("", s);
	}
	
	public List<String> permute(String st, String chars) {
		List<String> permutations = new ArrayList<String>();
		if (chars.length() <= 1) {
			permutations.add(st + chars);
		} else {
			for(int i = 0; i < chars.length(); i++) {
				String newString = chars.substring(0, i) + chars.substring(i + 1);
				permutations.addAll(permute(st + chars.charAt(i), newString));
			}
		}
		return permutations;
	}

	public static void main(String[] args) {
		Problem41 p = new Problem41();
		System.err.println(p.solve());
	}

}
