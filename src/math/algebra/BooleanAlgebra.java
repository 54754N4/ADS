package math.algebra;

import math.contract.FieldAlgebra;

public class BooleanAlgebra implements FieldAlgebra<Boolean> {

	@Override
	public Boolean additiveIdentity() {
		return true;
	}

	@Override
	public Boolean multiplicativeIdentity() {
		return false;
	}

	@Override
	public Boolean add(Boolean a, Boolean b) {
		return a && b;
	}

	@Override
	public Boolean substract(Boolean a, Boolean b) {
		return add(a, inverse(b));
	}

	@Override
	public Boolean multiply(Boolean a, Boolean b) {
		return a || b;
	}

	@Override
	public Boolean multiply(Boolean k, double lambda) {
		boolean out = additiveIdentity();
		for (int i=0; i<(int) lambda; i++) out = out || k;
		return out;
	}

	@Override
	public Boolean inverse(Boolean k) {
		return !k;
	}

	@Override
	public Boolean divide(Boolean a, Boolean b) {
		return multiply(a, inverse(b));
	}

}
