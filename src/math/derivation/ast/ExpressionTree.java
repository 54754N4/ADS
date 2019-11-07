package math.derivation.ast;

import math.derivation.builder.Builder;
import math.derivation.factorizer.Factorizer;
import math.derivation.interpreter.Token;
import struct.tree.binary.BinaryTree;

public class ExpressionTree extends BinaryTree<Token> {
	
	private ExpressionTree(Expression exp) {
		super(new Builder(exp).root);
	}
	
	public static ExpressionTree from(Expression exp) {
		return new ExpressionTree(exp);
	}
	
	// Simplifies tree in case we have 0s or 1s with +-*/
	public void simplify() {
		Factorizer.factorize(this);
	}
}