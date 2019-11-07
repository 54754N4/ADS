package math.derivation.builder;

import math.derivation.ast.BinaryOperator;
import math.derivation.ast.Constant;
import math.derivation.ast.Expression;
import math.derivation.ast.UnaryOperator;
import math.derivation.ast.Variable;
import math.derivation.interpreter.Token;
import struct.tree.binary.Node;

public interface Visitor {
	void visit(BinaryOperator op, Node<Token> node);
	void visit(UnaryOperator op, Node<Token> node);
	void visit(Constant c, Node<Token> node);
	void visit(Variable var, Node<Token> node);
	// dispatcher
	default void visit(Expression e, Node<Token> node) {			
		if (e instanceof BinaryOperator) visit((BinaryOperator) e, node);
		else if (e instanceof UnaryOperator) visit((UnaryOperator) e, node);
		else if (e instanceof Constant) visit((Constant) e, node);
		else if (e instanceof Variable) visit((Variable) e, node);
	}
}
