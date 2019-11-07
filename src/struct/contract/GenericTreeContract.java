package struct.contract;

public interface GenericTreeContract<K, V> extends TreeContract<V> {
	GenericTreeContract<K, V> add(GenericTreeContract<K, V> child);
	GenericTreeContract<K, V> add(K key, V value);
}
