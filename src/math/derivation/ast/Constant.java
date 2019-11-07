package math.derivation.ast;

import math.derivation.builder.Visitor;
import math.derivation.interpreter.Token;
import math.derivation.interpreter.Type;
import struct.tree.binary.Node;

public class Constant extends Expression {
	public static Constant ZERO = new Constant("0"), ONE = new Constant("1");
	
	public final String c;
	
	public Constant(String c) {
		super(new Token(Type.NUMBER, c));
		this.c = c;
	}
	
	public int value() {
		return Integer.parseInt(c);
	}
	
	@Override
	public Double eval(Double x) {
		return Double.parseDouble(c);
	}

	@Override
	public Expression derivative() {
		return new Constant("0");
	}

	public boolean equals(Constant constant) {
		return c.equals(constant.c);
	}
	
	public String toString() {
		return c;
	}
	
	public void accept(Visitor v, Node<Token> parent) {
		v.visit(this, parent);
	}
	
//	@Override
//	public String getKey() {
//		return toString();
//	}
//
//	@Override
//	public String getValue() {
//		return toString();
//	}
//
//	@Override
//	public List<Expression> getChildren() {
//		return Arrays.asList();
//	}
//	
//	@Override
//	protected void setChild(int at, Expression child) {
//		// No child to set for constants
//	}
}