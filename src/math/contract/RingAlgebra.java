package math.contract;

public interface RingAlgebra<K> {
	K additiveIdentity();
	K multiplicativeIdentity();
	K add(K a, K b);
	K substract(K a, K b);
	K multiply(K a, K b);
}
