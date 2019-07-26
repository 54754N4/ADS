package math.algebra;

import math.contract.LatticeAlgebra;

public class FloatAlgebra implements LatticeAlgebra<Float> {

	@Override
	public Float additiveIdentity() {
		return 0f;
	}

	@Override
	public Float multiplicativeIdentity() {
		return 1f;
	}

	@Override
	public Float add(Float a, Float b) {
		return a+b;
	}

	@Override
	public Float multiply(Float a, Float b) {
		return a*b;
	}

	@Override
	public Float multiply(Float k, double lambda) {
		return (float) (k*lambda);
	}

	@Override
	public Float inverse(Float k) {
		return 1/k;
	}
	
}