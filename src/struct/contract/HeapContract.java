package struct.contract;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import struct.tree.generic.GenericSearchTree;
import struct.tree.generic.Node;
import utils.Comparables;
import utils.Numbers;

public interface HeapContract<K> {
	static int ROOT = 0;	// zero-based heap
	
	int dimension();
	HeapContract<K> createEmpty();
	List<K> model();
	Comparator<K> comparator();
	HeapContract<K> copy();
	
	default int parent(int i) {
		return (int) Math.floor((i-1)/dimension());
	}
	
	default List<Integer> children(int i) {
		int d = dimension();
		List<Integer> children = new ArrayList<>();
		for (int c=0; c<d; c++)
			children.add(d*i+(c+1));
		return children;
	}
	
	default K peekRoot() {
		return model().get(ROOT);
	}
	
	default public int size() {
		return model().size();
	}

	default boolean isEmpty() {
		return model().isEmpty();
	}
	
	default HeapContract<K> push(K data) {
		List<K> model = model();
		model.add(data);
		siftUp(model.size()-1);
		return this;
	}
	
	default K pop() {
		List<K> model = model();
		K root = model.get(ROOT);
		model.remove(ROOT);
		if (!isEmpty()) {
			model.add(ROOT, model.get(model.size() - 1)); //replaceRoot with last
			model.remove(model.size() - 1);	// remove last
			siftDown(ROOT);
		}
		return root;
	}
	
	default K replaceRoot(K data) {
		List<K> model = model();
		K root = model.get(ROOT);
		model.remove(ROOT);
		model.add(0, data);
		siftDown(ROOT);
		return root;
	}
	
	default K delete(K target) {
		List<K> model = model();
		int index = model.indexOf(target), 
			last = model.size()-1; 
		if (index != -1) {
			swap(index, last);
			model.remove(last);
			siftDown(index);
		}
		return target;
	}
	
	default void swap(int i, int j) {
		K temp = model().get(i);
		model().set(i, model().get(j));
		model().set(j, temp);
	}
	
	default int compare(int i, int j) {
		return comparator().compare(model().get(i), model().get(j));
	}
	
	default HeapContract<K> merge(HeapContract<K> heap) {	// preserves source heaps
		HeapContract<K> c0 = copy(), c1 = heap.copy();
		return c0.meld(c1);		// destroys copied heaps
	}
	
	default HeapContract<K> meld(HeapContract<K> heap) {	// destroys source heaps
		HeapContract<K> merged = createEmpty();
		while (!isEmpty()) merged.push(pop());
		while (!isEmpty()) merged.push(heap.pop());
		return merged;
	}
	
	default void siftUp(int i) {
		int parent = parent(i);
		if (i > 0 && compare(i, parent) < 0) {
			swap(i, parent);
			siftUp(parent);
		}
	}
	
	default void siftDown(int i) {
		List<Integer> indices = new ArrayList<>();
		indices.add(i); indices.addAll(children(i));
		List<K> values = new ArrayList<>(), model = model();
		for (int index : indices)
			if (index < size()) values.add(model.get(index));
			else continue;
		int ultimumIndex = Comparables.indexOfUltimum(values, comparator());
		if (ultimumIndex != 0) {
			int ultimum = indices.get(ultimumIndex);
			swap(i, ultimum);
			siftDown(ultimum);
		}
	}
	
	default GenericSearchTree<Integer, K> toTree() {
		List<K> model = model();
		int[] nums = Numbers.until(model.size());
		List<Node<Integer, K>> nodes = new ArrayList<>();
		for (int i=0; i<nums.length; i++) nodes.add(new Node<>(nums[i], model.get(i)));
		connect(0, nodes);	// recursively map created nodes following the model 
		return new GenericSearchTree<>(nodes.get(0));
	}
	
	default void connect(int i, List<Node<Integer, K>> nodes) {
		Node<Integer, K> parent = nodes.get(i); 
		List<Integer> children = children(i);
		for (int child : children) {
			if (child < size()) {
				parent.add(nodes.get(child));
				connect(child, nodes);
			}
		}
	}
	
	default String simpleRepresentation() {
		return Arrays.toString(model().toArray());
	}
}
