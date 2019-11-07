package math.derivation.ast;

import math.derivation.builder.Visitor;
import math.derivation.interpreter.Type;
import struct.tree.binary.Node;
public class Arccos extends UnaryOperator {

	public Arccos(Expression expression) {
		super(Type.ACOS, "ACOS", expression);
	}

	@Override
	public Double eval(Double x) {
		return Math.acos(expression.eval(x));
	}

	@Override
	public Expression derivative() {
		return new Negation(new Division(expression.derivative(), new SquareRoot(new Substraction(new Constant("1"), expression))));
	}

}
