package math.derivation.factorizer;

import math.derivation.ast.Expression;
import struct.tree.binary.Node;

public interface Visitable {
	void accept(Visitor v, Node<Expression> node);
}
