/**
 * 
 */
package struct.tree.binary;

import struct.contract.BinaryTreeContract;

public class BinaryTree<K extends Comparable<K>, V> implements BinaryTreeContract<K, V> {
	private Node<K, V> root;
	
	@Override
	public boolean isLeaf() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public V search(K key) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public K search(V value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(K key, V value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delete(K key) {
		// TODO Auto-generated method stub
		return false;
	}

}