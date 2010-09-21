package euler.problems;

public class Problem39 extends Problem {
	
	public static final int UPPER_BOUND = 1000;
	
	int solve() {
		int max = 0;
		int maxPerimeter = 0;
		for(int i = 1; i < UPPER_BOUND; i++) {
			int solutions = getRightAngleTriangleSolutions(i);
			if(solutions > max) {
				System.out.println(i + " - " + solutions + " !");
				max = solutions;
				maxPerimeter = i;
			}
		}
		return maxPerimeter;
	}
	
	private int getRightAngleTriangleSolutions(int perimeter) {
		int count = 0;
		for(int a = 1; a  < perimeter; a++) {
			for(int b = a; b < perimeter; b++) {
				double c = Math.sqrt(a*a + b*b);
				if(a + b + c == perimeter && c % 1 == 0) {
					//System.out.println(perimeter + ": {" + a + "," + b + "," + c + "}");
					count++;
				}
			}
		}
		return count;
	}
	
	public static void main(String[] args) {
		Problem39 p = new Problem39();
		System.err.println(p.solve());
	}
	
}
