package euler.problems;

public class Problem38 extends Problem {
	
	public static int PANDIGITAL_FROM = 1;
	public static int PANDIGITAL_TO = 9;
	public static int UPPER_BOUND = 10000;
	
	int solve() {
		int largest = Integer.MIN_VALUE;
		for(int i = 1; i < UPPER_BOUND; i++) {
			String concatenatedProduct = "";
			for(int j = 1; true; j++) {
				concatenatedProduct += i * j;
				if(concatenatedProduct.length() > 9) break;
				int parsedConcatenatedProduct = Integer.parseInt(concatenatedProduct);
				if(isPandigital(parsedConcatenatedProduct, PANDIGITAL_FROM, PANDIGITAL_TO)) {
					System.out.print(i + " - " + concatenatedProduct);
					if(parsedConcatenatedProduct > largest) {
						System.out.print(" !");
						largest = parsedConcatenatedProduct;
					}
					System.out.println();
				}
			}
		}
		return largest;
	}
	
	public static void main(String[] args) {
		Problem38 p = new Problem38();
		System.err.println(p.solve());
	}
	
}
