/**
 * 
 */
package struct.tree.binary;

import struct.contract.BinaryTreeContract;

public class BinaryTree<K extends Comparable<K>, V> implements BinaryTreeContract<K, V> {
	private Node<K, V> root;
	
	public static void main(String[] args) {
		Integer[] ints = {1,2,3,4,5,6};
		String[] strs = {"b","c","b","e","b","g"};
		Node<Integer, String> root = new Node<>(0, "a");
		for (int i=0; i<ints.length; i++) root.insert(ints[i], strs[i]); // creates degenerate trees (~ linked lists)
		BinaryTree<Integer, String> tree = new BinaryTree<>(root);
		System.out.println(tree.size());
		System.out.println(tree.height());
		System.out.println(tree.search(4));
		System.out.println(tree.search("b"));
		/*			1
		 * 		2		3
		 * 					4
		 */
	}

	public BinaryTree(Node<K, V> root) {
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
	public Node<K, V> setValue(V value) {
		return root.setValue(value);
	}

	@Override
	public Node<K, V> createNode(K key, V value) {
		return root.createNode(key, value);
	}
	
	@Override
	public Node<K, V> setLeft(BinaryTreeContract<K, V> node) {
		return root.setLeft(node);
	}

	@Override
	public Node<K, V> setRight(BinaryTreeContract<K, V> node) {
		return root.setRight(node);
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
	public String toString() {
		return root.toString();
	}
}