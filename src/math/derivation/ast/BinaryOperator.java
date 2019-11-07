package math.derivation.ast;

import java.util.Arrays;
import java.util.List;

import math.derivation.builder.Visitor;
import math.derivation.interpreter.Token;
import math.derivation.interpreter.Type;
import struct.contract.BinaryTreeContract;
import struct.tree.binary.Node;

public abstract class BinaryOperator extends Expression {
	public Expression left, right;
	public final String op;
	
	public BinaryOperator(Type type, String op, Expression left, Expression right) {
		super(new Token(type));
		this.left = left;
		this.right = right;
		this.op = op;
//		left.setParent(this);
//		right.setParent(this);
	}
	
	public String toString(Constant neutral) {
		if (left instanceof Constant || right instanceof Constant) {
			if (left.equals(neutral)) 
				return right.toString();
			else if (right.equals(neutral)) 
				return left.toString();
		}
		return left + op + right;
	}
	
	public String toString(Constant neutral, Constant absorbant) {
		if (left instanceof Constant || right instanceof Constant) {
			if (left.equals(neutral) || right.equals(neutral)) 
				return toString(neutral);
			if (left.equals(absorbant)) 
				return right.toString();
			else if (right.equals(absorbant)) 
				return left.toString();
		}
		return left + op + right;
	}
	
	public String toString() {
		return left + op + right;
	}
	
	@Override
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
	@Override
	public List<BinaryTreeContract<Token>> getChildren() {
		return Arrays.asList(left, right);
	}
//	
//	@Override
//	protected void setChild(int at, Expression child) {
//		if (at < 0 || at > 1) return;
//		else if (at == 0) left = child;
//		else if (at == 1) right = child;
//		child.setParent(this);
//	}
}
