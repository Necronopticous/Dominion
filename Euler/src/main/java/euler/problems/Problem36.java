package euler.problems;

public class Problem36 extends Problem {
	
	public static final int UPPER_BOUND = 1000000;

	int solve() {
		int sum = 0;
		for(int i = 0; i < UPPER_BOUND; i++) {
			if(isPalindromic(getDigits(i).toArray()) && isPalindromic(getBinaryDigits(i).toArray())) sum += i;
		}
		return sum;
	}

	public static void main(String[] args) {
		Problem36 p = new Problem36();
		System.err.println(p.solve());
	}

}
