package math.lambda;

@FunctionalInterface
public interface Indexed2DUpdateVisitor<E> {
	E visit(int i, int j, E cell);
}