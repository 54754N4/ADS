package math.derivation.ast;

import math.derivation.builder.Visitor;
import math.derivation.interpreter.Type;
import struct.tree.binary.Node;
public class Exp extends UnaryOperator {

	public Exp(Expression expression) {
		super(Type.E, "EXP", expression);
	}

	@Override
	public Double eval(Double x) {
		return Math.exp(expression.eval(x));
	}

	@Override
	public Expression derivative() {
		return new Product(new Exp(expression), expression.derivative());
	}
	
	public String toString() {
		return "e^("+expression+")";
	}

}
