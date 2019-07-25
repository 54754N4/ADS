package struct.list.skip;

class Node {
	private Node above, under, next;
	private int i;
	
	public Node(int i) {
		this.i = i;
	}
	
	public int getNumber() {
		return i;
	}
	
	public Node getAbove() {
		return above;
	}

	public Node getUnder() {
		return under;
	}

	public Node getNext() {
		return next;
	}

	public void setNumber(int i) {
		this.i = i;
	}
	
	public void setAbove(Node above) {
		this.above = above;
	}

	public void setUnder(Node under) {
		this.under = under;
	}

	public void setNext(Node next) {
		this.next = next;
	}
}