package math.contract;

public interface RingAlgebra<K> {
	K additiveIdentity();
	K multiplicativeIdentity();
	K add(K a, K b) throws RuntimeException;
	K substract(K a, K b) throws RuntimeException;
	K multiply(K a, K b) throws RuntimeException;
}
