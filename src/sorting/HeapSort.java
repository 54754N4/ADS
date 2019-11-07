package sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import struct.tree.heap.binary.BinaryHeap;

public class HeapSort {

	public static void main(String[] args) {
		List<Integer> nums = new ArrayList<>();
		for (int i=0; i<15; i++) 
			nums.add(new Random().nextInt(100));
		System.out.println(nums);
		System.out.println(Arrays.toString(HeapSort.sort(nums, true).toArray()));
	}

	public static <K extends Comparable<K>> List<K> sort(List<K> unsorted, boolean min) {
		BinaryHeap<K> heap = new BinaryHeap<>(unsorted, 
				(min) ? 
						Comparator.<K>naturalOrder() 
						: Comparator.<K>reverseOrder());
		List<K> sorted = new ArrayList<>();
		while (!heap.isEmpty()) 
			sorted.add(heap.pop());
		return sorted;
	}
	
}
