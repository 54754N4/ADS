package struct.tree.binary;

import struct.contract.BinaryTreeContract;

class Node<K extends Comparable<K>, V> implements BinaryTreeContract<K, V, Node<K, V>> {
	private K key;
	private V value;
	private Node<K, V> left, right;
	
	public Node(K key, V value) {
		this(key, value, null, null);
	}
	
	public Node(K key, V value, Node<K, V> l, Node<K, V> r) {
		this.key = key;
		this.value = value;
		left = l;
		right = r;
	}
	
	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return value;
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
	public Node<K, V> setLeft(Node<K, V> node) {
		this.left = node;
		return this;
	}
	
	@Override
	public Node<K, V> setRight(Node<K, V> node) {
		this.right = node;
		return this;
	}
	
	@Override
	public Node<K, V> createNode(K key, V value) {
		return new Node<>(key, value);
	}
	
	@Override
	public boolean delete(K key) {
		return false;
	}
	
}