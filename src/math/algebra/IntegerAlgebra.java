package math.algebra;

import math.contract.LatticeAlgebra;

public class IntegerAlgebra implements LatticeAlgebra<Integer> {

	@Override
	public Integer additiveIdentity() {
		return 0;
	}

	@Override
	public Integer multiplicativeIdentity() {
		return 1;
	}

	@Override
	public Integer add(Integer a, Integer b) {
		return a+b;
	}

	@Override
	public Integer multiply(Integer a, Integer b) {
		return a*b;
	}

	@Override
	public Integer multiply(Integer k, double lambda) {
		return (int) (k*lambda);
	}

	@Override
	public Integer inverse(Integer k) {
		return 1/k;
	}
	
}
