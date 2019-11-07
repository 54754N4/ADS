package math.derivation.ast;

import math.derivation.builder.Visitor;
import math.derivation.interpreter.Type;
import struct.tree.binary.Node;

public class Addition extends BinaryOperator {

	public Addition(Expression left, Expression right) {
		super(Type.PLUS, "+", left, right);
	}

	@Override
	public Double eval(Double x) {
		return left.eval(x) + right.eval(x);
	}

	@Override
	public Expression derivative() {
		return new Addition(left.derivative(), right.derivative());
	}

	public String toString() {
		return toString(Constant.ZERO);
	}
	
}
