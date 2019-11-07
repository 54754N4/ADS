package math.derivation.ast;

import math.derivation.builder.Visitor;
import math.derivation.interpreter.Type;
import struct.tree.binary.Node;
public class Power extends BinaryOperator {

	public Power(Expression left, Expression right) {
		super(Type.POWER, "^", left, right);
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

}
