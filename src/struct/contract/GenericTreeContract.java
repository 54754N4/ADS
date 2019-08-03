package struct.contract;

import java.util.ArrayList;
import java.util.List;

import utils.Comparables;

public interface GenericTreeContract<K, V> extends TreeContract<K, V> {
	GenericTreeContract<K, V> add(GenericTreeContract<K, V> child);
	GenericTreeContract<K, V> add(K key, V value);
	List<? extends GenericTreeContract<K, V>> getChildren();
	
	@Override
	default boolean isLeaf() {
		return getChildren().size() == 0;
	}
	
	@Override
	default int size() {
		if (isLeaf()) return 1;
		int size = 1;	// current node
		for (GenericTreeContract<K, V> child : getChildren())
			size += child.size();
		return size;
	}

	@Override
	default int height() {
		if (isLeaf()) return 0;
		return Comparables.max(getChildrenSizes()) + 1;
	}
	
	default List<Integer> getChildrenSizes() {
		List<Integer> sizes = new ArrayList<>();
		for (GenericTreeContract<K, V> child : getChildren())
			sizes.add(child.size());
		return sizes;
	}
}
