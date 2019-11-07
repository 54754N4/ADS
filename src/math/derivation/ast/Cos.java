package math.derivation.ast;

import math.derivation.builder.Visitor;
import math.derivation.interpreter.Type;
import struct.tree.binary.Node;
public class Cos extends UnaryOperator {

	public Cos(Expression expression) {
		super(Type.COS, "COS", expression);
	}

	@Override
	public Double eval(Double x) {
		return Math.cos(expression.eval(x));
	}

	@Override
	public Expression derivative() {
		return new Negation(new Product(new Sin(expression), expression.derivative()));
	}
	
	public String toString() {
		return "cos("+expression.toString()+")";
	}

}
