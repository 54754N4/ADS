/**
 * 
 */
package struct.tree.binary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import struct.contract.BinarySearchTreeContract;

public class BinarySearchTree<K extends Comparable<K>, V> implements BinarySearchTreeContract<K, V> {
	private Node<K, V> root;
	
	public static void main(String[] args) {
		List<Integer> ints = new ArrayList<>(Arrays.asList(2,3,4,6,7,8));
		List<String> strs = new ArrayList<>(Arrays.asList("b","c","d","e","f","g"));
		Collections.shuffle(ints);
		Collections.shuffle(strs);
		Node<Integer, String> root = new Node<>(5, "a");
		// if u add sorted keys incrementally it will create degenerate trees !
		for (int i=0; i<ints.size(); i++) 
			root.insert(ints.get(i), strs.get(i));	
		BinarySearchTree<Integer, String> tree = new BinarySearchTree<>(root);
		System.out.println("size =\t "+tree.size());		// test tree methods
		System.out.println("height = "+tree.height());
		System.out.println("key[4] = "+tree.search(4));		// test key search
		System.out.println("vals[b]= "+tree.search("b"));	// test value search
		System.out.println(tree);
		Node<Integer, String> node = (Node<Integer, String>) tree.findLeftwiseMinimum();	//test min 
		// test parents
		System.out.println(node);
		System.out.println(node = node.getParent());
		// test delete
//		tree.delete(8);
//		System.out.println("size =\t "+tree.size());
//		System.out.println(tree);
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
	public Node<K, V> getParent() {
		return root.getParent();
	}

	@Override
	public Node<K, V> setParent(BinarySearchTreeContract<K, V> node) {
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