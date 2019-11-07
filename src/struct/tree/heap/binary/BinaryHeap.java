package struct.tree.heap.binary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import struct.contract.BinaryHeapContract;

public class BinaryHeap<K> implements BinaryHeapContract<K> {
	private Comparator<K> comparator;
	private List<K> model;
	
	public BinaryHeap(List<K> model, Comparator<K> comparator) {
		this.comparator = comparator;
		this.model = new ArrayList<>();
		for (K elem : model)
			push(elem);
	}
	
	public BinaryHeap(K[] model, Comparator<K> comparator) {
		this(Arrays.asList(model), comparator);
	}
	
	@Override
	public BinaryHeap<K> createEmpty() {
		return new BinaryHeap<>(new ArrayList<>(), comparator);
	}
	
	@Override
	public List<K> model() {
		return model;
	}
	
	@Override
	public Comparator<K> comparator() {
		return comparator;
	} 
	
	@Override
	public BinaryHeap<K> copy() {
		return new BinaryHeap<>(model(), comparator());
	}
	
	@Override
	public String toString() {
		return simpleRepresentation();
	}
	
	public static <K> BinaryHeap<K> createEmpty(Comparator<K> comparator) {
		return new BinaryHeap<>(new ArrayList<>(), comparator);
	}
	
	public static <K> BinaryHeap<K> heapify(K[] model, Comparator<K> comparator) {
		return new BinaryHeap<>(model, comparator);
	}
	
	public static <K> BinaryHeap<K> heapify(List<K> model, Comparator<K> comparator) {
		return new BinaryHeap<>(model, comparator);
	}
}
