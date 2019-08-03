package struct.tree.generic;

import java.util.ArrayList;
import java.util.List;

import struct.contract.GenericSearchTreeContract;
import struct.contract.GenericTreeContract;

public class GenericSearchTree<K, V> implements GenericSearchTreeContract<K, V> {
	private Node<K, V> root;
	
	public static void main(String[] args) {
		Integer[] ints = {0,1,2,3,4,5,6,7};
		String[] strs = {"s0","s1","s2","s3","s4","s5","s6","s7"};
		List<Node<Integer, String>> nodes = new ArrayList<>();
		for (int i=0; i<ints.length; i++) nodes.add(new Node<>(ints[i], strs[i]));
		nodes.get(0).add(nodes.get(1)).add(nodes.get(2));
		nodes.get(2).add(nodes.get(4));
		nodes.get(1).add(nodes.get(3)).add(nodes.get(5)).add(nodes.get(6));
		nodes.get(3).add(nodes.get(7));
		GenericSearchTree<Integer, String> tree = new GenericSearchTree<>(nodes.get(0));
		System.out.println(tree);
	}
	
	public GenericSearchTree(Node<K, V> root) { 
		this.root = root;
	}
	
	public GenericSearchTree() {
		this(new Node<>());
	}

	@Override
	public Node<K, V> add(GenericTreeContract<K, V> child) {
		return root.add(child);
	}

	@Override
	public List<Node<K, V>> getChildren() {
		return root.getChildren();
	}

	@Override
	public GenericTreeContract<K, V> add(K key, V value) {
		return root.add(key, value);
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
	public String toString() {
		return root.toString();
	}
}
