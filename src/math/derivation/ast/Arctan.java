package math.derivation.ast;

public class Arctan extends UnaryOperator {

	public Arctan(Expression expression) {
		super(expression);
	}

	@Override
	public Double eval(Double x) {
		return Math.acos(expression.eval(x));
	}

	@Override
	public Expression derivative() {
		return new Division(expression.derivative(), new Addition(new Constant("1"), new Power(expression, new Constant("2"))));
	}
	
	public String toString() {
		return "atan("+expression+")";
	}
}
