package euler.problems;

import java.util.List;

public class Problem30 extends Problem {
	
	public static final int SEARCH_UPPER_BOUND = 1000000;
	public static final int POWER = 5;
	
	int solve() {
		int sum = 0;
		for(int i = 1; i < SEARCH_UPPER_BOUND; i++) {
			if(isSumOfPoweredDigits(i)) {
				System.out.println(i);
				sum += i;
			}
		}
		return sum;
	}
	
	boolean isSumOfPoweredDigits(int n) {
		List<Integer> digits = getDigits(n);
		if(digits.size() < 2) return false;
		int sum = 0;
		for(Integer digit : digits) {
			sum += Math.pow(digit, POWER);
		}
		return n == sum;
	}
	
	public static void main(String[] args) {
		Problem30 p = new Problem30();
		System.err.println(p.solve());
	}

}
