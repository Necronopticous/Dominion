package euler.problems;

public class Problem40 extends Problem {
	
	public static final int[] SIGNIFICANT_DIGIT_INDICES = {1, 10, 100, 1000, 10000, 100000, 1000000};
	
	int solve() {
		int currentDigitIndex = 1;
		int significantIndex = 0;
		int product = 1;
		for(int i = 1; significantIndex < SIGNIFICANT_DIGIT_INDICES.length; i++) {
			int numberOfDigits = getNumberOfDigits(i);
			if(currentDigitIndex >= SIGNIFICANT_DIGIT_INDICES[significantIndex]) {
				int significantDigit = getDigits(i, true).get(currentDigitIndex - SIGNIFICANT_DIGIT_INDICES[significantIndex]);
				System.out.println("d" + SIGNIFICANT_DIGIT_INDICES[significantIndex] + " - " + significantDigit);
				product *= significantDigit;
				significantIndex++;
			}
			currentDigitIndex += numberOfDigits;
		}
		return product;
	}
	
	public static void main(String[] args) {
		Problem40 p = new Problem40();
		System.err.println(p.solve());
	}
	
}
