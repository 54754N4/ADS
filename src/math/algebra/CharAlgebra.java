package math.algebra;

import math.contract.LatticeAlgebra;

public class CharAlgebra implements LatticeAlgebra<Character> {

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
		return (char) (a+b%255);
	}

	@Override
	public Character substract(Character a, Character b) {
		return (char) (a-b%255);
	}

	@Override
	public Character multiply(Character a, Character b) {
		return (char) (a*b%255);
	}

	@Override
	public Character multiply(Character k, double lambda) {
		return (char) (k*lambda%255);
	}

	@Override
	public Character inverse(Character k) {
		return (char) (255-k);
	}

	@Override
	public Character divide(Character a, Character b) {
		return multiply(a, inverse(b));
	}

}
