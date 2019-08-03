package struct.contract;

import java.util.List;

public interface TreeContract<K, V> {
	boolean isLeaf();
	int size();
	int height();
	K getKey();
	V getValue();
	List<? extends TreeContract<K, V>> getChildren();
	
	default String simpleRepresentation() {
		if (isLeaf()) return "("+getValue()+")";
		StringBuilder sb = new StringBuilder(String.format("(%s:", getValue()));
		List<? extends TreeContract<K, V>> children = getChildren();
		for (TreeContract<K, V> child : children) 
			sb.append(child.simpleRepresentation()+",");
		sb.deleteCharAt(sb.length()-1).append(")");
		return sb.toString();
	}
}
