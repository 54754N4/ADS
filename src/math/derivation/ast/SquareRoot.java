package math.derivation.ast;

import math.derivation.builder.Visitor;
import math.derivation.interpreter.Type;
import struct.tree.binary.Node;
public class SquareRoot extends UnaryOperator {

	public SquareRoot(Expression expression) {
		super(Type.SQRT, "SQRT", expression);
	}

	@Override
	public Double eval(Double x) {
		return Math.sqrt(expression.eval(x));
	}

	@Override
	public Expression derivative() {
		return new Division(expression.derivative(), new Product(new Constant("2"), new SquareRoot(expression)));
	}
	
	public String toString() {
		return "sqrt("+expression+")";
	}
}
