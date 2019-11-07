package struct.contract;

import java.util.ArrayList;
import java.util.List;

public interface BinaryTreeContract<V> extends TreeContract<V> {
	BinaryTreeContract<V> getLeft();
	BinaryTreeContract<V> getRight();
	@Override BinaryTreeContract<V> getParent();
	BinaryTreeContract<V> setValue(V value);
	BinaryTreeContract<V> setLeft(BinaryTreeContract<V> node);
	BinaryTreeContract<V> setRight(BinaryTreeContract<V> node);
	@Override BinaryTreeContract<V> setParent(TreeContract<V> node);
	
	@Override
	default List<BinaryTreeContract<V>> getChildren() {
		List<BinaryTreeContract<V>> children = new ArrayList<>();
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