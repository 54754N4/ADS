package struct.list.linked;

import struct.contract.ListContract;

class LinkedList<E> implements ListContract<E> {
	private Node<E> head;
	
	public LinkedList() {
		head = new Node<E>(null, null);
	}

	@Override
	public void add(E e) {
		if (head.getData() == null) 
			head = new Node<E>(e, null);
		
		Node<E> node = head;
		while (node.getNext() != null) 
			node = node.getNext();
		node.setNext(new Node<E>(e, null));
	}
	
	@Override
	public E get(int index) {
		if (index < 0 || index > size())
			throw new IllegalArgumentException();
		Node<E> node = head;
		while (index-- >= 0) 
			node = node.getNext();
		return node.getData();
	}
	
	@Override
	public int size() {
		if (head.getNext() == null)
			return 1;
		int size = 0;
		Node<E> node = head;
		while ((node = node.getNext()) != null)
			size++;
		return size;
	}

	@Override
	public E remove(int index) {
		if (index < 0 || index > size())
			throw new IllegalArgumentException();
		int i=0;
		Node<E> node = head, deleted;
		while (i++ < index - 1)
			node = node.getNext();
		node.setNext((deleted = node.getNext()).getNext());			
		return deleted.getData();
	}
	
}