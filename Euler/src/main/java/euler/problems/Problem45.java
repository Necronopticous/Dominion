package euler.problems;

public class Problem45 extends Problem {

	@Override
	int solve() {
		int solution = 0;
		for(int i = 286; i < 100000; i++) {
			if(isHexagonal(triangle(i)) && isPentagonal(triangle(i))) {
				System.out.println(i + ": " + triangle(i));
			}
		}
		return solution;
	}
	
	public static void main(String[] args) {
		Problem p = new Problem45();
		System.err.println(p.solve());
	}

}
