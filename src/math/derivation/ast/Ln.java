package math.derivation.ast;

import math.derivation.builder.Visitor;
import math.derivation.interpreter.Type;
import struct.tree.binary.Node;
public class Ln extends UnaryOperator {

	public Ln(Expression expression) {
		super(Type.LN, "LN", expression);
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
		return "ln("+expression+")";
	}

}
