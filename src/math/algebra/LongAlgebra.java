package math.algebra;

import math.contract.LatticeAlgebra;

public class LongAlgebra implements LatticeAlgebra<Long> {

	@Override
	public Long additiveIdentity() {
		return 0l;
	}

	@Override
	public Long multiplicativeIdentity() {
		return 1l;
	}

	@Override
	public Long add(Long a, Long b) {
		return a+b;
	}

	@Override
	public Long substract(Long a, Long b) {
		return a-b;
	}

	@Override
	public Long multiply(Long a, Long b) {
		return a*b;
	}

	@Override
	public Long multiply(Long k, double lambda) {
		return (long) (k*lambda);
	}

	@Override
	public Long inverse(Long k) {
		return 1/k;
	}

	@Override
	public Long divide(Long a, Long b) {
		return a/b;
	}
	
}
