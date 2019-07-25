package math.derivation.ast;

public class Arcsin extends UnaryOperator {

	public Arcsin(Expression expression) {
		super(expression);
	}

	@Override
	public Double eval(Double x) {
		return Math.asin(expression.eval(x));
	}

	@Override
	public Expression derivative() {
		return new Division(expression.derivative(), new SquareRoot(new Substraction(new Constant("1"), expression)));
	}
	
	public String toString() {
		return "asin("+expression+")";
	}

}
