package struct.contract;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unchecked")
public interface BinaryTreeContract<K extends Comparable<K>, V, N extends BinaryTreeContract<K,V,N>> extends TreeContract {
	N createNode(K key, V value);
	K getKey();
	N getLeft();
	N setLeft(N node);
	N getRight();
	N setRight(N node);
	V getValue();
	N setValue(V value);
	
	boolean delete(K key);
	
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
		if (isLeaf()) return 0;
		else if (getLeft() != null && getRight() != null)
			return Math.max(getLeft().height(), getRight().height()) + 1;
		else if (getLeft() != null) return getLeft().height()+1;
		else if (getRight() != null) return getRight().height()+1;
		return -666;	// unreachable
	}

	
	default N search(K key) {
		if (getKey().compareTo(key) == 0) return (N) this;
		else if (getKey().compareTo(key) < 0 && getLeft() != null) return getLeft().search(key);
		else if (getKey().compareTo(key) > 0 && getRight() != null) return getRight().search(key);
		else return null;	// not found
	}
	
	default List<N> search(V value) {
		List<N> matches = new ArrayList<>(), match;
		if (getValue() == value) return (List<N>) Arrays.asList(this);
		if (getLeft() != null && (match = getLeft().search(value)) != null) matches.addAll(match);
		else if (getRight() != null && (match = getRight().search(value)) != null) matches.addAll(match);
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
