package math.derivation.ast;

public class Power extends BinaryOperator implements Expression {

	public Power(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public Double eval(Double x) {
		return Math.pow(left.eval(x), right.eval(x));
	}

	@Override
	public Expression derivative() {
		return new Product(
				new Power(left, right),
				new Addition(new Product(right.derivative(), new Log(left)), new Division(new Product(right, left.derivative()), left)));
	}

	public String toString() {
		return toString("^");
	}
}
