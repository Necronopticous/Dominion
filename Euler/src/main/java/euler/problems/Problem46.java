package euler.problems;

public class Problem46 extends Problem {

	@Override
	int solve() {
		int oddComposite = 0;
		on: while(true) {
			oddComposite = nextOddComposite(oddComposite);
			for(int i = 1; twiceASquare(i) < oddComposite; i++) {
				for(int prime = 2; twiceASquare(i) + prime <= oddComposite; prime = nextPrime(prime)) {
					if(twiceASquare(i) + prime == oddComposite) continue on;
					System.out.println("Odd Composite: " + oddComposite + " Twice a Square: " + twiceASquare(i) + " Prime: " + prime);
				}
			}
			break;
		}
		return oddComposite;
	}
	
	public int twiceASquare(int n) {
		return 2*n*n;
	}
	
	public static void main(String[] args) {
		Problem p = new Problem46();
		System.out.println(p.solve());
	}

}
