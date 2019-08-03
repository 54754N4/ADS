package struct.contract;

import java.util.ArrayList;
import java.util.List;

public interface BinaryTreeContract<K, V> extends TreeContract<K, V> {
	BinaryTreeContract<K,V> getLeft();
	BinaryTreeContract<K,V> getRight();
	
	@Override
	default List<BinaryTreeContract<K, V>> getChildren() {
		List<BinaryTreeContract<K, V>> children = new ArrayList<>();
		if (getLeft() != null) children.add(getLeft());
		if (getRight() != null) children.add(getRight());
		return children;
	}
	
	@Override
	default boolean isLeaf() {
		return getLeft() == null && getRight() == null;
	}
	
	@Override
	default int size() {
		if (isLeaf()) return 1;
		int size = 1;
		if (getLeft() != null) size += getLeft().size();
		if (getRight() != null) size += getRight().size();
		return size;
	}

	@Override
	default int height() {
		if (isLeaf()) 
			return 0;
		else if (getLeft() != null && getRight() != null)
			return Math.max(getLeft().height(), getRight().height()) + 1;
		else if (getLeft() != null) 
			return getLeft().height()+1;
		else if (getRight() != null) 
			return getRight().height()+1;
		return -1;	// unreachable
	}
}