package math.derivation.ast;

public class Negation extends UnaryOperator {

	public Negation(Expression expression) {
		super(expression);
	}

	@Override
	public Double eval(Double x) {
		return -expression.eval(x);
	}

	@Override
	public Expression derivative() {
		return new Negation(expression.derivative());
	}

	public String toString() {
		return "-"+expression;
	}
}
