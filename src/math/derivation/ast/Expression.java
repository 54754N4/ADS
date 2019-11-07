package math.derivation.ast;

import math.derivation.builder.Visitable;
import math.derivation.interpreter.Token;
import struct.tree.binary.BinaryTree;
import struct.tree.binary.Node;

public abstract class Expression extends BinaryTree<Token> implements Visitable {
	public final Token token;
	
	protected Expression(Token token) {
		super(new Node<>(token));
		this.token = token;
	}
	
	public abstract Double eval(Double x);
	public abstract Expression derivative();

}
