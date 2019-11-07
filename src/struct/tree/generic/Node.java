package struct.tree.generic;

import java.util.ArrayList;
import java.util.List;

import struct.contract.GenericSearchTreeContract;
import struct.contract.GenericTreeContract;
import struct.contract.TreeContract;

public class Node<K, V> implements GenericSearchTreeContract<K, V> {
	private K key;
	private V value;
	private Node<K, V> parent;
	private List<Node<K, V>> children;
	
	public Node() {
		this(null, null);
	}
	
	public Node(K key, V value) {
		this.setKey(key);
		this.setValue(value);
		children = new ArrayList<>();
	}
	
	@Override
	public Node<K, V> add(GenericTreeContract<K,V> child) {
		child.setParent(this);
		children.add((Node<K, V>) child);
		return this;
	}
	
	@Override
	public Node<K, V> add(K key, V value) {
		return add(new Node<>(key, value));
	}
	
	@Override
	public List<Node<K, V>> getChildren() {
		return children;
	}

	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return simpleRepresentation();
	}
	
	public void setKey(K key) {
		this.key = key;
	}

	public void setValue(V value) {
		this.value = value;
	}

	@Override
	public Node<K, V> getParent() {
		return parent;
	}

	@Override
	public Node<K, V> setParent(TreeContract<V> node) {
		this.parent = (Node<K, V>) node;
		return this;
	}
}
