package euler.problems;

import java.util.ArrayList;
import java.util.List;

public class Problem50 extends Problem {
	
	private static final int UPPER_BOUND = 1000000;

	@Override
	int solve() {
		List<Integer> primes = new ArrayList<Integer>();
		for(int prime = 2; prime < UPPER_BOUND; prime = nextPrime(prime)) primes.add(prime);
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < primes.size(); i++) {
			int current = 0;
			for(int j = i; j < primes.size(); j++) {
				current += primes.get(j);
				if(current > UPPER_BOUND) break;
				if(j-i+1 >= max && isPrime(current)) {
					max = j-i+1;
					System.out.println(max + " | " + current);
				}
			}
		}
		return max;
	}
	
	public static void main(String[] args) {
		Problem p = new Problem50();
		System.out.println(p.solve());
	}

}
