package math.derivation.ast;

import math.derivation.builder.Visitor;
import math.derivation.interpreter.Type;
import struct.tree.binary.Node;
public class Log extends UnaryOperator {

	public Log(Expression expression) {
		super(Type.LOG, "LOG", expression);
	}

	@Override
	public Double eval(Double x) {
		return Math.log10(expression.eval(x));
	}

	@Override
	public Expression derivative() {
		return new Division(expression.derivative(), expression);
	}

	public String toString() {
		return "log("+expression+")";
	}

}
