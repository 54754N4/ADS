package struct.tree.heap.dary;

import java.util.Arrays;
import java.util.Comparator;

import struct.tree.generic.GenericSearchTree;
import struct.tree.generic.wysiwyg.TreeView;

public class Test {
	public static void main(String[] args) {
		GenericSearchTree<Integer, Integer> tree;
		int dimension = 4;
		Integer[] ints = {1,2,3,4,5,6,7,8,9,0,19,234,22};
		DaryHeap<Integer> heap;
		System.out.println(heap = DaryHeap.heapify(dimension, Arrays.asList(ints), Comparator.<Integer>naturalOrder()));
		tree = heap.toTree();
		TreeView.displayValues(tree);
		System.out.println(heap = DaryHeap.heapify(dimension, Arrays.asList(ints), Comparator.<Integer>reverseOrder()));
		tree = heap.toTree();
		TreeView.displayValues(tree);
	}
}
