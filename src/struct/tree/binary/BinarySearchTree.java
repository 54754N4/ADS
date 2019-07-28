/**
 * 
 */
package struct.tree.binary;

import struct.contract.BinarySearchTreeContract;

public class BinarySearchTree<K extends Comparable<K>, V> implements BinarySearchTreeContract<K, V> {
	private Node<K, V> root;
	
	public static void main(String[] args) {
		Integer[] ints = {1,2,3,4,5,6};
		String[] strs = {"b","c","b","e","b","g"};
		Node<Integer, String> root = new Node<>(0, "a");
		for (int i=0; i<ints.length; i++) root.insert(ints[i], strs[i]); // creates degenerate trees (~ linked lists)
		BinarySearchTree<Integer, String> tree = new BinarySearchTree<>(root);
		System.out.println("size =\t "+tree.size());
		System.out.println("height = "+tree.height());
		System.out.println("key[4] = "+tree.search(4));
		System.out.println("vals[b]= "+tree.search("b"));
		/*	0,a
		 * 		1,b
		 * 			2,c
		 * 				3,b
		 * 					4,e
		 * 						5,b
		 * 						 	6,g
		 */
	}

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
	public Node<K, V> setValue(V value) {
		return root.setValue(value);
	}

	@Override
	public Node<K, V> createNode(K key, V value) {
		return root.createNode(key, value);
	}
	
	@Override
	public Node<K, V> setLeft(BinarySearchTreeContract<K, V> node) {
		return root.setLeft(node);
	}

	@Override
	public Node<K, V> setRight(BinarySearchTreeContract<K, V> node) {
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