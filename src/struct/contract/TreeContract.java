package struct.contract;

import java.util.ArrayList;
import java.util.List;

import math.lambda.ElementVisitor;
import math.lambda.IndexedVisitor;
import utils.Comparables;

public interface TreeContract<V> {
	V getValue();
	List<? extends TreeContract<V>> getChildren();
	TreeContract<V> getParent();
	TreeContract<V> setParent(TreeContract<V> node);
	
	default boolean isLeaf() {
		return getChildren().size() == 0;
	}
	
	default int size() {
		if (isLeaf()) return 1;
		int size = 1;	// current node
		for (TreeContract<V> child : getChildren())
			size += child.size();
		return size;
	}

	default int height() {
		if (isLeaf()) return 0;
		List<Integer> heights = new ArrayList<>();
		for (TreeContract<V> child : getChildren())
			heights.add(child.height());
		return Comparables.max(heights) + 1;
	}
	
	default String simpleRepresentation() {
		if (isLeaf()) return "("+getValue()+")";
		StringBuilder sb = new StringBuilder(String.format("(%s:", getValue()));
		List<? extends TreeContract<V>> children = getChildren();
		for (TreeContract<V> child : children) 
			sb.append(child.simpleRepresentation()+",");
		sb.deleteCharAt(sb.length()-1).append(")");
		return sb.toString();
	}
	
	default void forEachSizeAware(IndexedVisitor<TreeContract<V>> action) {
		action.visit(size(), this);
		if (isLeaf()) return;
		List<? extends TreeContract<V>> children = getChildren();
		for (TreeContract<V> child : children) 
			child.forEachSizeAware(action);
	}
	
	default void forEachGenerationAware(IndexedVisitor<TreeContract<V>> action) {
		forEachGenerationAware(action, height());
	}
	
	default void forEachGenerationAware(IndexedVisitor<TreeContract<V>> action, int max) {
		action.visit(max-height(), this);
		if (isLeaf()) return;
		List<? extends TreeContract<V>> children = getChildren();
		for (TreeContract<V> child : children) 
			child.forEachGenerationAware(action, max);
	}
	
	default void forEachHeightAware(IndexedVisitor<TreeContract<V>> action) {
		action.visit(height(), this);
		if (isLeaf()) return;
		List<? extends TreeContract<V>> children = getChildren();
		for (TreeContract<V> child : children) 
			child.forEachHeightAware(action);
	}
	
	default void forEach(ElementVisitor<TreeContract<V>> action) {
		action.visit(this);
		if (isLeaf()) return;
		List<? extends TreeContract<V>> children = getChildren();
		for (TreeContract<V> child : children)
			child.forEach(action);
	}
}
