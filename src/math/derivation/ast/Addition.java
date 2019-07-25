package math.derivation.ast;

public class Addition extends BinaryOperator {

	public Addition(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public Double eval(Double x) {
		return left.eval(x) + right.eval(x);
	}

	@Override
	public Expression derivative() {
		return new Addition(left.derivative(), right.derivative());
	}

	public String toString() {
		return toString("+");
	}
}
