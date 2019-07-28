package struct.tree.generic;

import java.util.List;

import struct.contract.GenericSearchTreeContract;
import struct.contract.GenericTreeContract;

public class GenericSearchTree<K, V> implements GenericSearchTreeContract<K, V> {
	private Node<K, V> root;
	
	public static void main(String[] args) {
		GenericSearchTree<Integer, String> tree = new GenericSearchTree<>();
		tree.add(new Node<>());
	}
	
	public GenericSearchTree() {
		root = new Node<>();
	}

	@Override
	public Node<K, V> add(GenericTreeContract<K, V> child) {
		return root.add(child);
	}

	@Override
	public List<Node<K, V>> getChildren() {
		return root.getChildren();
	}
}
