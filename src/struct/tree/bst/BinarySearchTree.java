/**
 * 
 */
package struct.tree.bst;

import struct.contract.BinarySearchTreeContract;
import struct.contract.BinaryTreeContract;
import struct.contract.TreeContract;

public class BinarySearchTree<K extends Comparable<K>, V> implements BinarySearchTreeContract<K, V> {
	private Node<K, V> root;

	public BinarySearchTree(Node<K, V> root) {
		this.root = root;
	}
	
	@Override
	public K getKey() {
		return root.getKey();
	}
	
	@Override
	public V getValue() {
		return root.getValue();
	}
	
	@Override
	public Node<K, V> getLeft() {
		return root.getLeft();
	}

	@Override
	public Node<K, V> getRight() {
		return root.getRight();
	}
	
	@Override
	public Node<K, V> getParent() {
		return root.getParent();
	}
	
	@Override
	public Node<K, V> setLeft(BinaryTreeContract<K, V> left) {
		return root.setLeft(left);
	}

	@Override
	public Node<K, V> setRight(BinaryTreeContract<K, V> right) {
		return root.setRight(right);
	}

	@Override
	public Node<K, V> setParent(TreeContract<K, V> node) {
		root = (Node<K, V>) node;
		return root.setParent(node);
	}

	@Override
	public Node<K, V> setKey(K key) {
		return root.setKey(key);
	}
	
	@Override
	public Node<K, V> setValue(V value) {
		return root.setValue(value);
	}

	@Override
	public Node<K, V> createNode(K key, V value) {
		return root.createNode(key, value);
	}
	
	@Override
	public void insert(K key, V value) {
		root.insert(key, value);
	}

//	@Override
//	public void delete(K key) {
//		root.delete(key);
//	}
//	
	@Override
	public String toString() {
		return root.simpleRepresentation();
	}
}