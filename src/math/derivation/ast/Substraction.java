package math.derivation.ast;

public class Substraction extends BinaryOperator {

	public Substraction(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public Double eval(Double x) {
		return left.eval(x) - right.eval(x);
	}

	@Override
	public Expression derivative() {
		return new Substraction(left.derivative(), right.derivative());
	}

	public String toString() {
		return toString("-");
	}
}
