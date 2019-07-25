package math.derivation.ast;

public class Arccos extends UnaryOperator {

	public Arccos(Expression expression) {
		super(expression);
	}

	@Override
	public Double eval(Double x) {
		return Math.acos(expression.eval(x));
	}

	@Override
	public Expression derivative() {
		return new Negation(new Division(expression.derivative(), new SquareRoot(new Substraction(new Constant("1"), expression))));
	}

}
