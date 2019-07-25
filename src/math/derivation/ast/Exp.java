package math.derivation.ast;

public class Exp extends UnaryOperator {

	public Exp(Expression expression) {
		super(expression);
	}

	@Override
	public Double eval(Double x) {
		return Math.exp(expression.eval(x));
	}

	@Override
	public Expression derivative() {
		return new Product(new Exp(expression), expression.derivative());
	}
	
	public String toString() {
		return "e^("+expression+")";
	}

}
