package math.derivation.ast;

public class Division extends BinaryOperator {

	public Division(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public Double eval(Double x) {
		return left.eval(x) / right.eval(x);
	}

	@Override
	public Expression derivative() {
		Expression numerator = new Substraction(new Product(left.derivative(), right), new Product(left, right.derivative())),
				denominator = new Power(right, new Constant("2"));
		return new Division(numerator, denominator);
	}
	
	public String toString() {
		return toString("/");
	}

}
