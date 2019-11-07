package struct.tree.heap.binary;

import java.util.Comparator;

import struct.tree.generic.wysiwyg.TreeView;

public class Test {
	
	public static void main(String[] args) throws InterruptedException {
		Integer[] ints = {1,2,3,4,5,6,7,8,9,0,19,234,22};
		BinaryHeap<Integer> heap;
		System.out.println(BinaryHeap.heapify(ints, Comparator.<Integer>naturalOrder()));
		System.out.println(heap = BinaryHeap.heapify(ints, Comparator.<Integer>reverseOrder()));
		while (!heap.isEmpty()) {
			TreeView.displayValues(heap.toTree());
			System.out.println(heap.pop());
			Thread.sleep(1000);
		}
	}

}
