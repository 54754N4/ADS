package struct.list.linked;

class Node<E> {
	private Node<E> next;
	private E data;
	
	public Node(E data, Node<E> next) {
		this.next = next;
		this.data = data;
	}

	public Node<E> getNext() {
		return next;
	}

	public E getData() {
		return data;
	}

	public void setNext(Node<E> next) {
		this.next = next;
	}

	public void setData(E data) {
		this.data = data;
	}
}