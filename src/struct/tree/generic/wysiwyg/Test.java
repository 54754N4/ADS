package struct.tree.generic.wysiwyg;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import struct.tree.binary.BinarySearchTree;
import struct.tree.generic.GenericSearchTree;
import struct.tree.generic.Node;

@SuppressWarnings("unused")
public class Test {

	public static void main(String[] args) {
		testGeneric();
	}
	
	private static void testGeneric() {
		GenericSearchTree<Integer, String> tree = genericTree();
		TreeView.display(
				TreeView.Draw.FORMAT.setFormat("%s: %s"), 
				new Dimension(1000, 500), 
				tree);
	}
	
	private static GenericSearchTree<Integer, String> genericTree() {
		Integer[] ints = {0,1,2,3,4,5,6,7};
		String[] strs = {"s0","s1","s2","s3","s4","s5","s6","s7"};
		List<Node<Integer, String>> nodes = new ArrayList<>();
		for (int i=0; i<ints.length; i++) nodes.add(new Node<>(ints[i], strs[i]));
		nodes.get(0).add(nodes.get(1)).add(nodes.get(2));
		nodes.get(2).add(nodes.get(4));
		nodes.get(1).add(nodes.get(3)).add(nodes.get(5)).add(nodes.get(6));
		nodes.get(3).add(nodes.get(7));
		return new GenericSearchTree<>(nodes.get(0));
	}
	
	private static void testBinary() {
		BinarySearchTree<Integer, String> tree = pseudoRandomBinaryTree(); 
		TreeView.display(TreeView.Draw.PAIRS, new Dimension(1000, 500), tree);
	}

	private static BinarySearchTree<Integer, String> pseudoRandomBinaryTree() {
		List<Integer> ints = new ArrayList<>(Arrays.asList(2,3,4,6,7,8));
		List<String> strs = new ArrayList<>(Arrays.asList("b","c","d","e","f","g"));
		Collections.shuffle(ints);
		Collections.shuffle(strs);
		struct.tree.binary.Node<Integer, String> root = new struct.tree.binary.Node<>(5, "a");
		// if u add sorted keys incrementally it will create degenerate trees !
		for (int i=0; i<ints.size(); i++) 
			root.insert(ints.get(i), strs.get(i));	
		return new BinarySearchTree<>(root);
	}
}
