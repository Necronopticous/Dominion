package euler.problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public abstract class Problem {
	
	abstract int solve();
	
	List<Integer> getDigits(int n, boolean ordered) {
		List<Integer> digits = new ArrayList<Integer>();
		while(n > 0) {
			int digit = n % 10;
			if(ordered) digits.add(0, digit);
			else digits.add(digit);
			n /= 10;
		}
		return digits;
	}
	
	List<Integer> getDigits(int n) {
		return getDigits(n, false);
	}
	
	int getNumberOfDigits(int n) {
		return (int)(Math.log10(n)+1);
	}
	
	List<Integer> getBinaryDigits(int n) {
		List<Integer> binaryDigits = new ArrayList<Integer>();
		while(n > 0) {
			binaryDigits.add(n & 1);
			n >>= 1;
		}
		return binaryDigits;
	}
	
	int factorial(int n) {
		int factorial = 1;
		while(n > 0) factorial *= n--;
		return factorial;
	}
	
	boolean isPandigital(int n) {
		return isPandigital(n, 1, getNumberOfDigits(n));
	}
	
	boolean isPandigital(int n, int from, int to) {
		List<Integer> digits = getDigits(n);
		if(digits.size() != to - from + 1) return false;
		Collections.sort(digits);
		Iterator<Integer> it = digits.iterator();
		for(int i = from; i <= to; i++) {
			if(!it.next().equals(i)) return false;
		}
		return true;
	}
	
	boolean isPrime(int n) {
		if(n < 2) return false;
		double nSquared = Math.sqrt(n);
		for(int i = 2; i <= nSquared; i++) {
			if(n % i == 0) return false;
		}
		return true;
	}
	
	boolean isCircularPrime(int n) {
		if(n < 2) return false;
		for(int i = 0; i < getNumberOfDigits(n); i++) {
			if(!isPrime(n)) return false;
			n = rotate(n);
		}
		return true;
	}
	
	int rotate(int n) {
		return (int)((n / 10) + (n % 10) * Math.pow(10, getNumberOfDigits(n)-1));
	}
	
	boolean isLeftRightTruncatablePrime(int n) {
		return isLeftTruncatablePrime(n) && isRightTruncatablePrime(n);
	}
	
	boolean isLeftTruncatablePrime(int n) {
		int numberOfDigits = getNumberOfDigits(n);
		if(numberOfDigits == 1) return isPrime(n);
		return isPrime(n) && isLeftTruncatablePrime((int)(n % Math.pow(10, numberOfDigits-1)));
	}
	
	boolean isRightTruncatablePrime(int n) {
		int numberOfDigits = getNumberOfDigits(n);
		if(numberOfDigits == 1) return isPrime(n);
		return isPrime(n) && isRightTruncatablePrime(n / 10);
	}
	
	boolean isPalindromic(Object[] a) {
		for(int i = 0; i < a.length / 2; i++) {
			if(!a[i].equals(a[a.length-i-1])) return false;
		}
		return true;
	}
	
	boolean isTriangleWord(String word) {
		return isTriangleNumber(getWordValue(word));
	}
	
	int getWordValue(String word) {
		int wordValue = 0;
		for(char c : word.toUpperCase().toCharArray()) {
			wordValue += (byte)c - 64;
		}
		return wordValue;
	}

	boolean isTriangleNumber(int n) {
		int term = 0;
		for(int i = 1; term < n; i++) {
			term = (int)((0.5) * i * (i+1));
		}
		if(term == n) return true;
		return false;
	}
	
	long triangle(int n) {
		return (long)n * ((long)n + 1) / 2;
	}
	
	boolean isTriangle(long n) {
		for(int i = ((int)Math.sqrt(n*2)-1); triangle(i) <= n; i++) {
			if(triangle(i) == n) return true;
		}
		return false;
	}
	
	long pentagonal(int n) {
		return (long)n * (3 * (long)n - 1) / 2;
	}
	
	boolean isPentagonal(long n) {
		for(int i = ((int)Math.sqrt(n*2/3)-1); pentagonal(i) <= n; i++) {
			if(pentagonal(i) == n) return true;
		}
		return false;
	}
	
	long hexagonal(int n) {
		return (long)n * (2 * (long)n - 1);
	}
	
	boolean isHexagonal(long n) {
		for(int i = ((int)Math.sqrt(n/2)-1); hexagonal(i) <= n; i++) {
			if(hexagonal(i) == n) return true;
		}
		return false;
	}
	
	boolean isOdd(int n) {
		return n%2!=0;
	}
	
	boolean isEven(int n) {
		return n%2==0;
	}
	
	public int nextPrime(int n) {
		if(n < 2) return 2;
		n++;
		while(!isPrime(n)) n++;
		return n;
	}
	
	public int nextOddComposite(int n) {
		if(n < 9) return 9;
		n++;
		if(isEven(n)) n++;
		while(isPrime(n)) n+=2;
		return n;
	}
	
}
