package math.derivation.factorizer;

import math.derivation.ast.BinaryOperator;
import math.derivation.ast.Constant;
import math.derivation.ast.Expression;
import math.derivation.ast.ExpressionTree;
import math.derivation.ast.UnaryOperator;
import math.derivation.ast.Variable;
import math.derivation.builder.Visitor;
import math.derivation.interpreter.Type;
import struct.tree.binary.Node;

public class Factorizer implements Visitor {
	private static Factorizer factorizer = new Factorizer();
	
	private Factorizer() {}
	
	public static void factorize(ExpressionTree tree) {
		Node<Expression> root = tree.getRoot();
		factorizer.visit(root.getValue(), root);
	}
	
	@Override
	public void visit(BinaryOperator op, Node<Expression> node) {
		factorizer.visit(op.left, node.getLeft());
		factorizer.visit(op.right, node.getRight());
	}

	@Override
	public void visit(UnaryOperator op, Node<Expression> node) {
		factorizer.visit(op.expression, node.getLeft());
	}

	@Override
	public void visit(Constant c, Node<Expression> node) {
		if (c.equals(Constant.ZERO))
			shiftUpZero(node);
		else if (c.equals(Constant.ONE))
			shiftUpOne(node);
	}

	// no need to simplify on variables.. yet, i guess
	@Override public void visit(Variable var, Node<Expression> node) {}
	
	private void shiftUpZero(Node<Expression> node) {
		Node<Expression> father = node.getParent();
		if (father != null) {
			Expression parent = father.getValue();
			if (parent.type.equals(Type.TIMES))		// zero is absorbent for *
				shiftUp(node);
			else if (parent.type.equals(Type.PLUS))	// zero is neutral for +
				shiftUp(oppositeChild(node));
		}
	}
	
	private void shiftUpOne(Node<Expression> node) {
		// u actually 
	}
	
	private void shiftUp(Node<Expression> node) {
		Node<Expression> father = node.getParent();
		if (father != null) {
			father.setLeft(null);
			father.setRight(null);
			father.setValue(node.getValue());	// swap expressions
		}
	}
	
	private Node<Expression> oppositeChild(Node<Expression> node) {
		Node<Expression> father = node.getParent();
		if (father != null) {
			if (father.getLeft().getValue().equals(node.getValue()))
				return father.getRight();
			return father.getLeft();
		}
		return node;
	}
}
