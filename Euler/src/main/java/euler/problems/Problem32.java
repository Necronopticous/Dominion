package euler.problems;

import java.util.HashSet;
import java.util.Set;

public class Problem32 extends Problem {
	
	public static final int PANDIGITAL_FROM = 1;
	public static final int PANDIGITAL_TO = 9;

	int solve() {
		Set<Integer> pandigitalProducts = new HashSet<Integer>();
		for(int i = 1; i < 2000; i++) {
			for(int j = 1; j < 2000; j++) {
				if(i * j > 10000) continue;
				pandigitalProducts.add(getPandigitalProduct(i,j));
			}
		}
		int sum = 0;
		for(int pandigitalProduct : pandigitalProducts) {
			sum += pandigitalProduct;
		}
		return sum;
	}
	
	int getPandigitalProduct(int multiplicand, int multiplier) {
		int product = multiplicand * multiplier;
		if(isPandigitalIdentity(multiplicand, multiplier, product, PANDIGITAL_FROM, PANDIGITAL_TO)) return product;
		else return 0;
	}
	
	private boolean isPandigitalIdentity(int multiplicand, int multiplier, int product, int from, int to) {
		boolean isPandigitalIdentity = false;
		String identity = multiplicand + "" + multiplier + "" + product;
		try {
			isPandigitalIdentity = isPandigital(Integer.parseInt(identity), from, to);
		} catch(Exception e) {}
		if(isPandigitalIdentity) System.out.println("Pandigital identity found: " + multiplicand + " x " + multiplier + " = " + product);
		return isPandigitalIdentity;
	}

	public static void main(String[] args) {
		Problem32 p = new Problem32();
		System.err.println(p.solve());
	}

}
