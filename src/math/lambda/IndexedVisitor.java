package math.lambda;

@FunctionalInterface
public interface IndexedVisitor<E> {
	void visit(int i, E cell);
}
