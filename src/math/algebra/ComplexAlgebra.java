package math.algebra;

import math.contract.FieldAlgebra;
import math.struct.generic.Complex;

public class ComplexAlgebra implements FieldAlgebra<Complex> {

	@Override
	public Complex additiveIdentity() {
		return new Complex();
	}

	@Override
	public Complex multiplicativeIdentity() {
		return new Complex(1);
	}

	@Override
	public Complex add(Complex a, Complex b) {
		return a.plus(b);
	}

	@Override
	public Complex substract(Complex a, Complex b) {
		return a.minus(b);
	}

	@Override
	public Complex multiply(Complex a, Complex b) {
		return a.times(b);
	}

	@Override
	public Complex multiply(Complex k, double lambda) {
		return k.times(lambda);
	}

	@Override
	public Complex inverse(Complex k) {
		return k.inversed();
	}

	@Override
	public Complex divide(Complex a, Complex b) {
		return a.dividedBy(b);
	}

}
