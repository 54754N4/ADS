package math.derivation.ast;

import math.derivation.builder.Visitor;
import math.derivation.interpreter.Type;
import struct.tree.binary.Node;
public class Negation extends UnaryOperator {

	public Negation(Expression expression) {
		super(Type.MINUS, "-", expression);
	}

	@Override
	public Double eval(Double x) {
		return -expression.eval(x);
	}

	@Override
	public Expression derivative() {
		return new Negation(expression.derivative());
	}

	public String toString() {
		return "-"+expression;
	}

}
