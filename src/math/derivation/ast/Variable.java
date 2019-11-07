package math.derivation.ast;

import java.util.Arrays;
import java.util.List;

import math.derivation.builder.Visitor;
import math.derivation.interpreter.Token;
import math.derivation.interpreter.Type;
import struct.tree.binary.Node;
public class Variable extends Expression {
	private String name;
	
	public Variable() {
		this("x");
	}
	
	public Variable(String name) {
		super(new Token(Type.VAR, name));
		this.name = name;
	}
	
	@Override
	public Double eval(Double x) {
		return x;
	}

	@Override
	public Expression derivative() {
		return new Constant("1");
	}
	
	public String toString() {
		return name;
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
