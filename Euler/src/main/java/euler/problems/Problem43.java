package euler.problems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Problem43 extends Problem {

	@Override
	int solve() {
		List<Long> satisfiers = new ArrayList<Long>();
		for(String t7 : buildTriads(17)) {
			String satisfier = t7;
			if(repeatsCharacters(satisfier)) continue;
			System.out.println(satisfier);
			for(String t6 : buildTriads(13)) {
				if(!satisfier.startsWith(t6.substring(1)) || repeatsCharacters(t6.charAt(0) + satisfier)) continue;
				satisfier = t6.charAt(0) + satisfier;
				System.out.println(satisfier);
				for(String t5 : buildTriads(11)) {
					if(!satisfier.startsWith(t5.substring(1)) || repeatsCharacters(t5.charAt(0) + satisfier)) continue;
					satisfier = t5.charAt(0) + satisfier;
					System.out.println(satisfier);
					for(String t4 : buildTriads(7)) {
						if(!satisfier.startsWith(t4.substring(1)) || repeatsCharacters(t4.charAt(0) + satisfier)) continue;
						satisfier = t4.charAt(0) + satisfier;
						System.out.println(satisfier);
						for(String t3 : buildTriads(5)) {
							if(!satisfier.startsWith(t3.substring(1)) || repeatsCharacters(t3.charAt(0) + satisfier)) continue;
							satisfier = t3.charAt(0) + satisfier;
							System.out.println(satisfier);
							for(String t2 : buildTriads(3)) {
								if(!satisfier.startsWith(t2.substring(1)) || repeatsCharacters(t2.charAt(0) + satisfier)) continue;
								satisfier = t2.charAt(0) + satisfier;
								System.out.println(satisfier);
								for(String t1 : buildTriads(2)) {
									if(!satisfier.startsWith(t1.substring(1)) || repeatsCharacters(t1.charAt(0) + satisfier)) continue;
									satisfier = t1.charAt(0) + satisfier;
									System.out.println(satisfier + " !");
									satisfiers.add(finish(satisfier));
									satisfier = satisfier.substring(1);
								}
								satisfier = satisfier.substring(1);
							}
							satisfier = satisfier.substring(1);
						}
						satisfier = satisfier.substring(1);
					}
					satisfier = satisfier.substring(1);
				}
				satisfier = satisfier.substring(1);
			}
		}
		Long sum = 0L;
		for(Long satisfier : satisfiers) sum += satisfier;
		System.out.println("Sum: " + sum);
		return 0;
	}
	
	private Long finish(String satisfier) {
		if(!satisfier.contains("0")) return new Long("0" + satisfier);
		if(!satisfier.contains("1")) return new Long("1" + satisfier);
		if(!satisfier.contains("2")) return new Long("2" + satisfier);
		if(!satisfier.contains("3")) return new Long("3" + satisfier);
		if(!satisfier.contains("4")) return new Long("4" + satisfier);
		if(!satisfier.contains("5")) return new Long("5" + satisfier);
		if(!satisfier.contains("6")) return new Long("6" + satisfier);
		if(!satisfier.contains("7")) return new Long("7" + satisfier);
		if(!satisfier.contains("8")) return new Long("8" + satisfier);
		if(!satisfier.contains("9")) return new Long("9" + satisfier);
		return 0L;
	}

	public boolean repeatsCharacters(String s) {
		Set<Character> chars = new HashSet<Character>();
		for(char c : s.toCharArray()) {
			if(chars.contains(c)) return true;
			else chars.add(c);
		}
		return false;
	}
	
	public List<String> buildTriads(int multiplicand) {
		List<String> triads = new ArrayList<String>();
		for(int i = 0; i*multiplicand < 1000; i++) {
			triads.add(toTriad(i*multiplicand));
		}
		return triads;
	}
	
	public String toTriad(Integer n) {
		String triad = n.toString();
		while(triad.length() < 3) triad = "0" + triad;
		return triad;
	}
	
	public static void main(String[] args) {
		Problem p = new Problem43();
		System.err.println(p.solve());
	}

}
