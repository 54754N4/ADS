package math.derivation.ast;

import math.derivation.builder.Visitor;
import math.derivation.interpreter.Type;
import struct.tree.binary.Node;
public class Arctan extends UnaryOperator {

	public Arctan(Expression expression) {
		super(Type.ATAN, "ATAN", expression);
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
