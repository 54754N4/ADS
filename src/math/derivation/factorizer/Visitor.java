package math.derivation.factorizer;

import math.derivation.ast.BinaryOperator;
import math.derivation.ast.Constant;
import math.derivation.ast.Expression;
import math.derivation.ast.UnaryOperator;
import math.derivation.ast.Variable;
import struct.tree.binary.Node;

public interface Visitor {
	void visit(BinaryOperator op, Node<Expression> node);
	void visit(UnaryOperator op, Node<Expression> node);
	void visit(Constant c, Node<Expression> node);
	void visit(Variable var, Node<Expression> node);
	// dispatcher
	default void visit(Expression e, Node<Expression> node) {			
		if (e instanceof BinaryOperator) visit((BinaryOperator) e, node);
		else if (e instanceof UnaryOperator) visit((UnaryOperator) e, node);
		else if (e instanceof Constant) visit((Constant) e, node);
		else if (e instanceof Variable) visit((Variable) e, node);
	}
}
