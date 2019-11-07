package struct.tree.bst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Test {
	
	public static void main(String[] args) {
		testBT();
	}
	
	private static void testBT() {
		List<Integer> ints = new ArrayList<>(Arrays.asList(2,3,4,6,7,8));
		List<String> strs = new ArrayList<>(Arrays.asList("b","c","d","e","f","g"));
		Collections.shuffle(ints);
		Collections.shuffle(strs);
		Node<Integer, String> root = new Node<>(5, "a");
		// if u add sorted keys incrementally it will create degenerate trees !
		for (int i=0; i<ints.size(); i++) 
			root.insert(ints.get(i), strs.get(i));	
		BinarySearchTree<Integer, String> tree = new BinarySearchTree<>(root);
		System.out.println("size =\t "+tree.size());		// test tree methods
		System.out.println("height = "+tree.height());
		System.out.println("key[4] = "+tree.search(4));		// test key search
		System.out.println("vals[b]= "+tree.search("b"));	// test value search
		System.out.println(tree);
		Node<Integer, String> node = (Node<Integer, String>) tree.findLeftwiseMinimum();	//test min 
		// test parents
		System.out.println(node);
		System.out.println(node = node.getParent());
		// test delete
//		tree.delete(8);
//		System.out.println("size =\t "+tree.size());
//		System.out.println(tree);
	}

}
