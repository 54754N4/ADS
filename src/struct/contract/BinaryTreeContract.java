package struct.contract;

import java.util.ArrayList;
import java.util.List;

public interface BinaryTreeContract<K extends Comparable<K>, V> extends TreeContract {
	BinaryTreeContract<K,V> createNode(K key, V value);
	K getKey();
	V getValue();
	BinaryTreeContract<K,V> getLeft();
	BinaryTreeContract<K,V> getRight();
	BinaryTreeContract<K,V> setValue(V value);
	BinaryTreeContract<K,V> setLeft(BinaryTreeContract<K,V> node);
	BinaryTreeContract<K,V> setRight(BinaryTreeContract<K,V> node);
	
	boolean delete(K key); // too hard to make generic =v if u extend, u do it.
	
	@Override
	default boolean isLeaf() {
		return getLeft() == null && getRight() == null;
	}
	
	@Override
	default int size() {
		if (isLeaf()) return 1;
		int size = 1;
		if (getLeft() != null) size += getLeft().size();
		if (getRight() != null) size += getRight().size();
		return size;
	}

	@Override
	default int height() {
		if (isLeaf()) 
			return 0;
		else if (getLeft() != null && getRight() != null)
			return Math.max(getLeft().height(), getRight().height()) + 1;
		else if (getLeft() != null) 
			return getLeft().height()+1;
		else if (getRight() != null) 
			return getRight().height()+1;
		return -1;	// unreachable
	}
	
	default BinaryTreeContract<K,V> search(K key) {
		if (getKey().compareTo(key) == 0) 
			return this;
		else if (getKey().compareTo(key) < 0 && getLeft() != null) 
			return getLeft().search(key);
		else if (getKey().compareTo(key) > 0 && getRight() != null) 
			return getRight().search(key);
		else return null;	// not found
	}
	
	default List<BinaryTreeContract<K,V>> search(V value) {
		List<BinaryTreeContract<K,V>> matches = new ArrayList<>(), submatches;
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
