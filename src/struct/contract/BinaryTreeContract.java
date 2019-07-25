package struct.contract;

public interface BinaryTreeContract<K extends Comparable<K>, V> extends TreeContract {
	V search(K key);
	K search(V value);
	void insert(K key, V value);	
	boolean delete(K key);
}
