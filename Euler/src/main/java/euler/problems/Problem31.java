package euler.problems;


public class Problem31 extends Problem {
	
	public static final int[] COINS = {200, 100, 50, 20, 10, 5, 2, 1};
	public static final int TARGET = 200;

	public int solve() {
		return getWays(0,0);
	}
	
	private int getWays(int total, int coinIndex) {
		int ways = 0;
		for(int i = coinIndex; i < COINS.length; i++) {
			int currentCoin = COINS[i];
			int currentCoinCount = 0;
			while(total + currentCoin <= TARGET) {
				total += currentCoin;
				currentCoinCount++;
			}
			while(currentCoinCount > 0) {
				if(total == TARGET) ways++;
				else ways += getWays(total, i+1);
				total -= currentCoin;
				currentCoinCount--;
			}
		}
		return ways;
	}
	
	public static void main(String[] args) {
		Problem31 p = new Problem31();
		System.err.println(p.solve());
	}

}
