/**
 * 
 */
package struct.tree.binary;

import struct.contract.BinaryTreeContract;

public class BinaryTree<K extends Comparable<K>, V> implements BinaryTreeContract<K, V, Node<K,V>> {
	private Node<K, V> root;
	
	public static void main(String[] args) {
		Node<Integer, String>  node3 = new Node<>(4, "fourth"),
			node1 = new Node<>(2, "second"), 
			node2 = new Node<>(3, "third", node3, null),
			root = new Node<>(1, "first", node1, node2);
		BinaryTree<Integer, String> tree = new BinaryTree<>(root);
		System.out.println(tree.size());
		System.out.println(tree.height());
		/*			1
		 * 		2		3
		 * 					4
		 */
	}

	public BinaryTree(Node<K, V> root) {
		this.root = root;
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
	public K getKey() {
		return root.getKey();
	}

	@Override
	public V getValue() {
		return root.getValue();
	}

	@Override
	public void insert(K key, V value) {
		root.insert(key, value);
	}

	@Override
	public boolean delete(K key) {
		return root.delete(key);
	}

	@Override
	public Node<K, V> setLeft(Node<K, V> node) {
		return root.setLeft(node);
	}

	@Override
	public Node<K, V> setRight(Node<K, V> node) {
		return root.setRight(node);
	}

	@Override
	public Node<K, V> setValue(V value) {
		return root.setValue(value);
	}

	@Override
	public Node<K, V> createNode(K key, V value) {
		return root.createNode(key, value);
	}
	
}