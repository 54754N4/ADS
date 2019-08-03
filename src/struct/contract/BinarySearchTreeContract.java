package struct.contract;

import java.util.ArrayList;
import java.util.List;

public interface BinarySearchTreeContract<K extends Comparable<K>, V> extends BinaryTreeContract<K, V> {
	BinarySearchTreeContract<K,V> createNode(K key, V value);
	K getKey();
	V getValue();
	BinarySearchTreeContract<K,V> getLeft();
	BinarySearchTreeContract<K,V> getRight();
	BinarySearchTreeContract<K,V> getParent();
	BinarySearchTreeContract<K,V> setKey(K key);
	BinarySearchTreeContract<K,V> setValue(V value);
	BinarySearchTreeContract<K,V> setLeft(BinarySearchTreeContract<K,V> node);
	BinarySearchTreeContract<K,V> setRight(BinarySearchTreeContract<K,V> node);
	BinarySearchTreeContract<K,V> setParent(BinarySearchTreeContract<K,V> node);
	
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
		else if (key.compareTo(getKey()) < 0) {	// handle left-insertion
			if (getLeft() == null) setLeft(createNode(key, value));
			else getLeft().insert(key, value);
		} else if (key.compareTo(getKey()) > 0) { // handle right-insertion
			if (getRight() == null) setRight(createNode(key, value));
			else getRight().insert(key, value);
		}
	}
	
//	default void delete(K key) {
//		// Descend till match
//		if (key.compareTo(getKey()) < 0 && getLeft() != null) 
//			getLeft().delete(key);
//		else if (key.compareTo(getKey()) > 0 && getRight() != null) 
//			getRight().delete(key);
//		// If equals we delete key
//		if (getLeft() != null && getRight() != null) {
//			if (getParent().getLeft().equals(this))  
//		} else if (getLeft() != null) 
//			;
//		else if (getRight() != null)
//			;
//		else {	// no children = delete node
//			if (getParent() != null) {
//				if (getParent().getLeft() == this) getParent().setLeft(null);
//				else getParent().setRight(null);
//			} else ; //delete root ?..
//		}
//	} 
	
	
	default BinarySearchTreeContract<K, V> findLeftwiseMinimum() {
		BinarySearchTreeContract<K, V> minNode = this;
		while (minNode.getLeft() != null) minNode = minNode.getLeft();
		return minNode;
	}
	
	@Override
	default String simpleRepresentation() {
		if (isLeaf()) return "("+getValue()+")";
		String left, value, right;
		left = (getLeft() != null) ? getLeft().simpleRepresentation()+"," : "";
		value = getValue().toString();
		right = (getRight() != null) ? ","+getRight().simpleRepresentation() : "";
		return String.format("(%s%s%s)", left, value, right);
	}
}
