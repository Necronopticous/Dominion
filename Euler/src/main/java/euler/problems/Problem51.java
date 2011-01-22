package euler.problems;

public class Problem51 extends Problem {
	
	private final static int FAMILY_SIZE = 6;

	@Override
	int solve() {
		Integer prime = 0;
		while(true) {
			prime = nextPrime(prime);
			break;
		}
		return 0;
	}
	
	public static void main(String[] args) {
		Problem p = new Problem51();
		System.out.println(p.solve());
	}

}
