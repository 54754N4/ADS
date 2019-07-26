package math.contract;

public interface FieldAlgebra<K> extends RingAlgebra<K> {
	K multiply(K k, double lambda);	// not in ring cause it's an external law ? i forgot
	K inverse(K k) throws RuntimeException;
	K divide(K a, K b) throws RuntimeException;
	
	default K opposite(K k) {
		return multiply(k, -1);
	}
}
