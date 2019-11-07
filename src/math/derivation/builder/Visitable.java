package math.derivation.builder;

import math.derivation.interpreter.Token;
import struct.tree.binary.Node;

public interface Visitable {
	void accept(Visitor v, Node<Token> node);
}
