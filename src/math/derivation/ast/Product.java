package math.derivation.ast;

import math.derivation.builder.Visitor;
import math.derivation.interpreter.Type;
import struct.tree.binary.Node;
public class Product extends BinaryOperator {

	public Product(Expression a, Expression b) {
		super(Type.TIMES, "*", a,b);
	}

	@Override
	public Double eval(Double x) {
		return left.eval(x) * right.eval(x);
	}

	@Override
	public Expression derivative() { // (uv)' = uv'+u'v = u'v+uv' 
		return new Addition(new Product(left.derivative(), right), new Product(left, right.derivative()));
	}

	public String toString() {
		return toString(Constant.ONE, Constant.ZERO);
	}
	
}
