package math.lambda;

@FunctionalInterface
public interface ElementUpdateVisitor<E> {
	E visit(E item);
}