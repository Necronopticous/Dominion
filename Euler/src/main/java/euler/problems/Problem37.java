package euler.problems;

public class Problem37 extends Problem {
	
	public static final int TO_FIND = 11;
	
	int solve() {
		int sum = 0;
		int found = 0;
		for(int i = 10; found < TO_FIND; i++) {
			if(isLeftRightTruncatablePrime(i)) {
				System.out.println(i);
				sum += i;
				found++;
			}
		}
		return sum;
	}

	public static void main(String[] args) {
		Problem37 p = new Problem37();
		System.err.println(p.solve());
	}
	
}
