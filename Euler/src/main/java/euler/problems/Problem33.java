package euler.problems;


public class Problem33 extends Problem {

	int solve() {
		for(int numerator = 10; numerator < 100; numerator++) {
			for(int denominator = 10; denominator < 100; denominator++) {
				testForAcceptance(numerator, denominator);
			}
		}
		return 100;
	}
	
	private void testForAcceptance(int numerator, int denominator) {
		if(numerator % 10 == 0 || denominator % 10 == 0) return;
		int n0 = numerator / 10;
		int n1 = numerator % 10;
		int d0 = denominator / 10;
		int d1 = denominator % 10;
		if(n0 == d0 && (double)numerator / denominator < 1 && (double)n1 / d1 == (double)numerator / denominator) accept(numerator, denominator, n1, d1);
		if(n0 == d1 && (double)numerator / denominator < 1 && (double)n1 / d0 == (double)numerator / denominator) accept(numerator, denominator, n1, d0);
		if(n1 == d0 && (double)numerator / denominator < 1 && (double)n0 / d1 == (double)numerator / denominator) accept(numerator, denominator, n0, d1);
		if(n1 == d1 && (double)numerator / denominator < 1 && (double)n0 / d0 == (double)numerator / denominator) accept(numerator, denominator, n0, d0);
	}
	
	private void accept(int numerator, int denominator, int n, int d) {
		System.out.println(numerator + "/" + denominator + " = " + n + "/" + d);
	}

	public static void main(String[] args) {
		Problem33 p = new Problem33();
		System.err.println(p.solve());
	}
	
}
