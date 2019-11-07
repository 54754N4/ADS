package math.derivation.ast;

import math.derivation.builder.Visitor;
import math.derivation.interpreter.Type;
import struct.tree.binary.Node;
public class Substraction extends BinaryOperator {

	public Substraction(Expression left, Expression right) {
		super(Type.MINUS, "-", left, right);
	}

	@Override
	public Double eval(Double x) {
		return left.eval(x) - right.eval(x);
	}

	@Override
	public Expression derivative() {
		return new Substraction(left.derivative(), right.derivative());
	}
}
