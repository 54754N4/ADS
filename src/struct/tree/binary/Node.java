package struct.tree.binary;

import struct.contract.BinaryTreeContract;

class Node<K extends Comparable<K>, V> implements BinaryTreeContract<K, V> {
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
	public boolean isLeaf() {
		return left == null && right == null;
	}

	@Override
	public int size() {
		if (isLeaf()) return 1;
		int size = 0;
		if (left != null) size += left.size();
		if (right != null) size += right.size();
		return size;
	}

	@Override
	public int height() {
		if (isLeaf()) return 0;
		return Math.max(left.height(), right.height()) + 1;
	}

	@Override
	public V search(K key) {
		if (this.key.compareTo(key) == 0) return value;
		else if (this.key.compareTo(key) < 0 && left != null) return left.search(key);
		else if (this.key.compareTo(key) > 0 && right != null) return right.search(key);
		else return null;
	}
	
	@Override
	public K search(V value) {
		K match = this.key;
		if (this.value == value) return match;
		else if ((match = left.search(value)) != null) return match;
		else if ((match = right.search(value)) != null) return match;
		else return null;	// not found
	}

	@Override
	public void insert(K key, V value) {	// Stupid strategy (creates degenerate trees)
		Node<K, V> node = this;
		while (!node.isLeaf()) node = node.right;
		node.right = new Node<K, V>(key, value);
	}

	@Override
	public boolean delete(K key) {
		// TODO Auto-generated method stub
		return false;
	}
}