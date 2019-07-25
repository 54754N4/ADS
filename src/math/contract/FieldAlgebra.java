package math.contract;

public interface FieldAlgebra<K> extends RingAlgebra<K> {
	K multiply(K k, double lambda);	// not in ring cause it's an external law ? i forgot
	K inverse(K k);
	K divide(K a, K b);
}
