package math.derivation.ast;

import math.derivation.builder.Visitor;
import math.derivation.interpreter.Type;
import struct.tree.binary.Node;

public class Abs extends UnaryOperator {

	public Abs(Expression expression) {
		super(Type.BAR, "ABS", expression);
	}

	@Override
	public Double eval(Double x) {
		return Math.abs(expression.eval(x));
	}

	@Override
	public Expression derivative() {
		return new Division(new Product(new Abs(expression), expression.derivative()), expression);
	}

	public String toString() {
		return "|"+expression+"|";
	}
}
