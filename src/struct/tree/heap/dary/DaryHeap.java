package struct.tree.heap.dary;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import struct.contract.HeapContract;

public class DaryHeap<K> implements HeapContract<K> {
	private int dimension;
	private Comparator<K> comparator;
	private List<K> model;
	
	public DaryHeap(int dimension, List<K> model, Comparator<K> comparator) {
		this.dimension = dimension;
		this.comparator = comparator;
		this.model = new ArrayList<>();
		for (K elem : model)
			push(elem);
	}

	@Override
	public int dimension() {
		return dimension;
	}

	@Override
	public DaryHeap<K> createEmpty() {
		return new DaryHeap<>(dimension, new ArrayList<>(), comparator);
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
	public DaryHeap<K> copy() {
		return new DaryHeap<>(dimension, model, comparator);
	}

	public static <K> DaryHeap<K> heapify(int dimension, List<K> arr, Comparator<K> comparator) {
		return new DaryHeap<>(dimension, arr, comparator);
	}
	
}
