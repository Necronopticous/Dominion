package euler.problems;

public class Problem35 extends Problem {
	
	public static final int UPPER_BOUND = 1000000;
	
	int solve() {
		int count = 0;
		for(int i = 0; i < UPPER_BOUND; i++) if(isCircularPrime(i)) count++;
		return count;
	}
	
	public static void main(String[] args) {
		Problem35 p = new Problem35();
		System.err.println(p.solve());
	}
	
}
