package euler.problems;

public class Problem34 extends Problem {

	int solve() {
		int sum = 0;
		for(int i = 10; i < 100000; i++) {
			if(i == sumOfFactorialDigits(i)) {
				System.out.println(i);
				sum += i;
			}
		}
		return sum;
	}
	
	int sumOfFactorialDigits(int n) {
		if(n < 10) return 0;
		int sumOfFactorialDigits = 0;
		for(int digit : getDigits(n)) {
			sumOfFactorialDigits += factorial(digit);
		}
		return sumOfFactorialDigits;
	}
	
	public static void main(String[] args) {
		Problem34 p = new Problem34();
		System.err.println(p.solve());
	}
	
}
