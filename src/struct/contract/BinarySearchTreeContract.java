package struct.contract;

import java.util.ArrayList;
import java.util.List;

public interface BinarySearchTreeContract<K extends Comparable<K>, V> extends BinaryTreeContract<K, V> {
	BinarySearchTreeContract<K,V> createNode(K key, V value);
	K getKey();
	V getValue();
	BinarySearchTreeContract<K,V> getLeft();
	BinarySearchTreeContract<K,V> getRight();
	BinarySearchTreeContract<K,V> setValue(V value);
	BinarySearchTreeContract<K,V> setLeft(BinarySearchTreeContract<K,V> node);
	BinarySearchTreeContract<K,V> setRight(BinarySearchTreeContract<K,V> node);
	
	boolean delete(K key); // too hard to make generic =v if u extend, u do it.
	
	default BinarySearchTreeContract<K,V> search(K key) {
		if (getKey().compareTo(key) == 0) 
			return this;
		else if (getKey().compareTo(key) < 0 && getLeft() != null) 
			return getLeft().search(key);
		else if (getKey().compareTo(key) > 0 && getRight() != null) 
			return getRight().search(key);
		else return null;	// not found
	}
	
	default List<BinarySearchTreeContract<K,V>> search(V value) {
		List<BinarySearchTreeContract<K,V>> matches = new ArrayList<>(), submatches;
		if (getValue() == value) 
			matches.add(this);
		if (getLeft() != null && (submatches = getLeft().search(value)) != null) 
			matches.addAll(submatches);
		if (getRight() != null && (submatches = getRight().search(value)) != null) 
			matches.addAll(submatches);
		return matches;
	}	
	
	default void insert(K key, V value) {
		if (getKey().equals(key)) setValue(value);
		else if (getKey().compareTo(key) < 0) {	// handle left-insertion
			if (getLeft() == null) setLeft(createNode(key, value));
			else getLeft().insert(key, value);
		} else if (getKey().compareTo(key) > 0) { // handle right-insertion
			if (getRight() == null) setRight(createNode(key, value));
			else getRight().insert(key, value);
		}
	}
}
