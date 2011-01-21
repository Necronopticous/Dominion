package euler.problems;

public class Problem47 extends Problem {
	
	private static final int CONSECUTIVE_NUMBERS = 4;
	private static final int DISTINCT_PRIME_FACTORS = 4;

	@Override
	int solve() {
		int last = 0;
		for(int i = 2; i < 1000000; i++) {
			int count = 0;
			for(int prime = 2; prime < i; prime = nextPrime(prime)) {
				if(i % prime == 0) count++;
			}
			if(count == DISTINCT_PRIME_FACTORS) last++;
			else last = 0;
			if(last == CONSECUTIVE_NUMBERS) return i-last+1;
		}
		return 0;
	}
	
	public static void main(String[] args) {
		Problem p = new Problem47();
		System.out.println(p.solve());
	}

}
