package math.derivation.ast;

import math.derivation.builder.Visitor;
import math.derivation.interpreter.Type;
import struct.tree.binary.Node;
public class Tan extends UnaryOperator {

	public Tan(Expression expression) {
		super(Type.TAN, "TAN", expression);
	}

	@Override
	public Double eval(Double x) {
		return Math.atan(expression.eval(x));
	}

	@Override
	public Expression derivative() {
		return new Division(expression.derivative(), new Power(new Cos(expression), new Constant("2")));
	}

	public String toString() {
		return "tan("+expression+")";
	}
}
