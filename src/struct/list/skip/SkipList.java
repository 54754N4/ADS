package struct.list.skip;

import java.util.Random;

public class SkipList {
	private Node head;
	
	private static final int LAYERS = 4;
	
	public SkipList(int initial) {
		head = new Node(initial);
		Node node, previous = head;
		for (int i=1; i<LAYERS; i++) {
			node = new Node(initial);
			previous.setUnder(node);
			previous = node;
		}
	}
	
	public Node getHead() {
		return head;
	}
	
	private boolean coinFlip() {
		return new Random().nextBoolean();
	}
	
	public void insert(int i) {
		int layer = 0;
		boolean added = true;
		Node node=head;
		while (node.getNext() != null) {
			if (node.getNext() == null) {
				node = node.getUnder();
				if (node == null)
					added = false;
				layer++;
				continue;
			}
			if (i <= node.getNext().getNumber())
				addNode(i, layer, node);
			node=node.getNext();
		}
		if (!added) 
			addNode(i, layer, node);
	}
	
	private void addNode(int i, int layer, Node at) {
		Node newNode = new Node(i);
		newNode.setNext(at.getNext());
		at.setNext(newNode);
		for (int c=layer+1; c<LAYERS; c++) { 
			if (coinFlip()) {
				Node above = new Node(i);
				newNode.setAbove(above);
				newNode = above;
			} else
				break;
		}
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		Node head = this.head;
		while (head.getUnder() != null)
			head = head.getUnder();
		for (Node node=head; node != null; node=node.getNext())
			result.append(node.getNumber()+",");
		return result.toString();
	}
}