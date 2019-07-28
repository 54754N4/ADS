package struct.tree.generic;

import java.util.ArrayList;
import java.util.List;

import struct.contract.GenericSearchTreeContract;
import struct.contract.GenericTreeContract;

public class Node<K, V> implements GenericSearchTreeContract<K, V> {
	private List<Node<K, V>> children;
	
	public Node() {
		children = new ArrayList<>();
	}
	
	@Override
	public Node<K, V> add(GenericTreeContract<K,V> child) {
		children.add((Node<K, V>) child);
		return this;
	}
	
	@Override
	public List<Node<K, V>> getChildren() {
		return children;
	}
}
