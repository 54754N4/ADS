package math.lambda;

@FunctionalInterface
public interface ElementVisitor<E> {
	void visit(E item);
}