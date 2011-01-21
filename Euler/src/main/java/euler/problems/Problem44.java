package euler.problems;

public class Problem44 extends Problem {

	@Override
	int solve() {
		for(int i = 1; i < 5000; i++) {
			long p1 = pentagonal(i);
			for(int j = 1; j < 5000; j++) {
				long p2 = pentagonal(j);
				if(isPentagonal(p1 + p2) && isPentagonal(p2 - p1)) {
					System.out.println("p1: " + p1 + " p2: " + p2 + " p2-p1: " + (p2-p1));
					break;
				}
			}
		}
		return 0;
	}
	
	public static void main(String[] args) {
		Problem p = new Problem44();
		System.err.println(p.solve());
	}

}
