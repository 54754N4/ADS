package struct.tree.binary;

import struct.contract.BinaryTreeContract;
import struct.contract.TreeContract;

public class Node<V> implements BinaryTreeContract<V> {
	private Node<V> parent;
	private Node<V> left, right;
	private V value;
	
	public Node(V value) {
		this.value = value;
	}
	
	public Node(V value, Node<V> left, Node<V> right) {
		this(value);
		setLeft(left);
		setRight(right);
	}

	@Override
	public V getValue() {
		return value;
	}

	@Override
	public Node<V> getLeft() {
		return left;
	}

	@Override
	public Node<V> getRight() {
		return right;
	}

	@Override
	public Node<V> getParent() {
		return parent;
	}

	@Override
	public Node<V> setValue(V value) {
		this.value = value;
		return this;
	}

	@Override
	public Node<V> setLeft(BinaryTreeContract<V> node) {
		left = (Node<V>) node;
		if (node != null) node.setParent(this);
		return this;
	}

	@Override
	public Node<V> setRight(BinaryTreeContract<V> node) {
		right = (Node<V>) node;
		if (node != null) node.setParent(this);
		return this;
	}

	@Override
	public Node<V> setParent(TreeContract<V> node) {
		parent = (Node<V>) node;
		return this;
	}

	@Override
	public String toString() {
		return value.toString();
	}
}
