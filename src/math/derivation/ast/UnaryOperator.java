package math.derivation.ast;

import java.util.Arrays;
import java.util.List;

import math.derivation.builder.Visitor;
import math.derivation.interpreter.Token;
import math.derivation.interpreter.Type;
import struct.tree.binary.Node;

public abstract class UnaryOperator extends Expression {
	public Expression expression;
	public final String op;
	
	public UnaryOperator(Type type, String op, Expression expression) {
		super(new Token(type));
		this.expression = expression;
		this.op = op;
//		expression.setParent(this);
	}
	
	public void accept(Visitor v, Node<Token> parent) {
		v.visit(this, parent);
	}
	
//	@Override
//	public String getKey() {
//		return op;
//	}
//
//	@Override
//	public String getValue() {
//		return toString();
//	}
//
//	@Override
//	public List<Expression> getChildren() {
//		return Arrays.asList(expression);
//	}
//	
//	@Override
//	protected void setChild(int at, Expression child) {
//		expression = child;
//		child.setParent(this);
//	}
}
