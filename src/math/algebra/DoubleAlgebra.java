package math.algebra;

import math.contract.LatticeAlgebra;

public class DoubleAlgebra implements LatticeAlgebra<Double> {

	@Override
	public Double additiveIdentity() {
		return 0d;
	}

	@Override
	public Double multiplicativeIdentity() {
		return 1d;
	}

	@Override
	public Double add(Double a, Double b) {
		return a+b;
	}

	@Override
	public Double substract(Double a, Double b) {
		return a-b;
	}

	@Override
	public Double multiply(Double a, Double b) {
		return a*b;
	}

	@Override
	public Double multiply(Double k, double lambda) {
		return k*lambda;
	}

	@Override
	public Double inverse(Double k) {
		return 1/k;
	}

	@Override
	public Double divide(Double a, Double b) {
		return a/b;
	}
	
}
