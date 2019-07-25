package math.derivation.ast;

public class Log extends UnaryOperator {

	public Log(Expression expression) {
		super(expression);
	}

	@Override
	public Double eval(Double x) {
		return Math.log(expression.eval(x));
	}

	@Override
	public Expression derivative() {
		return new Division(expression.derivative(), expression);
	}

	public String toString() {
		return "log("+expression+")";
	}
}
