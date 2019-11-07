package struct.tree.binary;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import struct.tree.generic.wysiwyg.TreeView;

public class Test {

	public static void main(String[] args) {
		List<Node<Integer>> nodes = new ArrayList<>();
		for (int i=0; i<7; i++)
			nodes.add(new Node<>(i));
		nodes.get(0).setLeft(nodes.get(1)).setRight(nodes.get(2));
		nodes.get(1).setLeft(nodes.get(3)).setRight(nodes.get(4));
		nodes.get(2).setLeft(nodes.get(5));
		nodes.get(4).setRight(nodes.get(6));
		nodes.get(0).setValue(10);
		System.out.println(nodes.get(5).getParent());
		TreeView.displayValues(new Dimension(1000, 1000), nodes.get(0));
	}

}
