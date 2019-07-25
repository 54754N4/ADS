package math.algebra;

import math.contract.LatticeAlgebra;

public class AlphanumeralAlgebra implements LatticeAlgebra<Character> {
	private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789<>?/.,;:'\"\\|[]{}+=_-~`!@#$%^&*()";
	private static final int TOTAL = ALPHABET.length();
	
	public static char get(int i) {
		return ALPHABET.charAt(i%TOTAL);
	}
	
	@Override
	public Character additiveIdentity() {
		return '\0';
	}

	@Override
	public Character multiplicativeIdentity() {
		return '\1';
	}

	@Override
	public Character add(Character a, Character b) {
		// apply additive neutral element rules
		if (a.equals(additiveIdentity())) return b;
		else if (b.equals(additiveIdentity())) return a;
		return get(a+b);
	}

	@Override
	public Character substract(Character a, Character b) {
		return get(a-b);
	}

	@Override
	public Character multiply(Character a, Character b) {
		// additive law's neutral element becomes multiplicative's absorbant element 
		if (a.equals(additiveIdentity())) return additiveIdentity();
		else if (b.equals(additiveIdentity())) return additiveIdentity();
		// apply multiplicative neutral element rules
		else if (a.equals(multiplicativeIdentity())) return b;
		else if (b.equals(multiplicativeIdentity())) return a;
		return get(a*b);
	}

	@Override
	public Character multiply(Character k, double lambda) {
		return get((int) (k*lambda));
	}

	@Override
	public Character inverse(Character k) {
		return get(TOTAL-k%TOTAL);
	}

	@Override
	public Character divide(Character a, Character b) {
		return multiply(a, inverse(b));
	}

}
