package math.algebra;

import math.contract.LatticeAlgebra;

public class CharAlphaAlgebra implements LatticeAlgebra<Character> {
	private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789<>?/.,;:'\"\\|[]{}+=_-~`!@#$%^&*()";
	private static final int TOTAL = ALPHABET.length();
	
	private static char get(int i) {
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
		return get(a+b);
	}

	@Override
	public Character substract(Character a, Character b) {
		return get(a-b);
	}

	@Override
	public Character multiply(Character a, Character b) {
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
