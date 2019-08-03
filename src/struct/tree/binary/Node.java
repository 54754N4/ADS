package struct.tree.binary;

import struct.contract.BinarySearchTreeContract;

public class Node<K extends Comparable<K>, V> implements BinarySearchTreeContract<K, V> {
	private K key;
	private V value;
	private Node<K, V> left, right, parent;
	
	public Node(K key, V value) {
		this(key, value, null, null);
	}
	
	public Node(K key, V value, Node<K, V> l, Node<K, V> r) {
		this.key = key;
		this.value = value;
		setLeft(l);
		setRight(r);
	}
	
	@Override
	public V getValue() {
		return value;
	}
	
	@Override
	public K getKey() {
		return key;
	}
	
	@Override
	public Node<K, V> setKey(K key) {
		this.key = key;
		return this;
	}
	
	@Override
	public Node<K, V> setValue(V value) {
		this.value = value;
		return this;
	}
	
	@Override
	public Node<K, V> getLeft() {
		return left;
	}
	
	@Override
	public Node<K, V> getRight() {
		return right;
	}
	
	@Override
	public Node<K, V> setLeft(BinarySearchTreeContract<K, V> node) {
		if (node != null) node.setParent(this);
		this.left = (Node<K, V>) node;
		return this;
	}
	
	@Override
	public Node<K, V> setRight(BinarySearchTreeContract<K, V> node) {
		if (node != null) node.setParent(this);
		this.right = (Node<K, V>) node;
		return this;
	}
	
	@Override
	public Node<K, V> getParent() {
		return parent;
	}

	@Override
	public Node<K, V> setParent(BinarySearchTreeContract<K, V> node) {
		parent = (Node<K, V>) node;
		return this;
	}
	
	@Override
	public Node<K, V> createNode(K key, V value) {
		return new Node<>(key, value);
	}
	
	@Override
	public String toString() {
		return simpleRepresentation();
//		return String.format("(%s -> %s)", getKey(), value);
	}
}