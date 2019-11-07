package math.derivation.builder;

import math.derivation.ast.BinaryOperator;
import math.derivation.ast.Constant;
import math.derivation.ast.Expression;
import math.derivation.ast.UnaryOperator;
import math.derivation.ast.Variable;
import math.derivation.interpreter.Token;
import math.derivation.interpreter.Type;
import struct.tree.binary.Node;

public class Builder implements Visitor {
	public final Node<Token> root;
	
	public Builder(Expression exp) {
		// store reference to currently built tree's top node
		root = new Node<>(exp.token);	
		visit(exp, root);
	}
	
	// creates + connects child to the left or right of parent
	private Node<Token> childOf(Node<Token> parent, Expression exp, boolean left) {
		Node<Token> node = new Node<>(exp.token);
		node.setParent(parent);
		if (left) parent.setLeft(node);
		else parent.setRight(node);
		return node;
	}

	@Override
	public void visit(BinaryOperator op, Node<Token> parent) {	
		visit(op.left, childOf(parent, op.left, true));
		visit(op.right, childOf(parent, op.right, false));
	}

	@Override
	public void visit(UnaryOperator op, Node<Token> parent) {
		visit(op.expression, childOf(parent, op.expression, true));
	}

	// no need to do anything for leaves since they're already connected to parent nodes
	@Override public void visit(Variable var, Node<Token> parent) {}	
	@Override public void visit(Constant c, Node<Token> parent) {}
}