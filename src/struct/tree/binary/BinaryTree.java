package struct.tree.binary;

import struct.contract.BinaryTreeContract;
import struct.contract.TreeContract;

public class BinaryTree<V> implements BinaryTreeContract<V> {
	private Node<V> root;
	
	public BinaryTree(Node<V> root) {
		this.root = root;
	}
	
	public Node<V> getRoot() {
		return root;
	}

	@Override
	public V getValue() {
		return root.getValue();
	}

	@Override
	public Node<V> getLeft() {
		return root.getLeft();
	}

	@Override
	public Node<V> getRight() {
		return root.getRight();
	}

	@Override
	public Node<V> getParent() {
		return root.getParent();
	}

	@Override
	public Node<V> setValue(V value) {
		return root.setValue(value);
	}

	@Override
	public Node<V> setLeft(BinaryTreeContract<V> node) {
		return root.setLeft(node);
	}

	@Override
	public Node<V> setRight(BinaryTreeContract<V> node) {
		return root.setRight(node);
	}

	@Override
	public Node<V> setParent(TreeContract<V> node) {
		Node<V> returnable = root.setParent(node);
		root = (Node<V>) node;
		return returnable;
	}

}
