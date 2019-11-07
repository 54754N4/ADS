package math.derivation.ast;

import math.derivation.builder.Visitor;
import math.derivation.interpreter.Type;
import struct.tree.binary.Node;
public class Sin extends UnaryOperator {

	public Sin(Expression expression) {
		super(Type.SIN, "SIN", expression);
	}
	
	@Override
	public Double eval(Double x) {
		return Math.sin(expression.eval(x));
	}

	@Override
	public Expression derivative() {
		return new Product(new Cos(expression), expression.derivative());
	}
	
	public boolean equals(Sin s) {
		return expression.equals(s.expression);
	}
	
	public String toString() {
		return "sin("+expression.toString()+")";
	}
}
