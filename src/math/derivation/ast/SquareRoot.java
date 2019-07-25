package math.derivation.ast;

public class SquareRoot extends UnaryOperator {

	public SquareRoot(Expression expression) {
		super(expression);
	}

	@Override
	public Double eval(Double x) {
		return Math.sqrt(expression.eval(x));
	}

	@Override
	public Expression derivative() {
		return new Division(expression.derivative(), new Product(new Constant("2"), new SquareRoot(expression)));
	}
	
	public String toString() {
		return "sqrt("+expression+")";
	}

}
