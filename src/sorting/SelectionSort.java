package sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public abstract class SelectionSort {

	public static void main(String[] args) {
		List<Integer> nums = new ArrayList<>();
		for (int i=0; i<15; i++) 
			nums.add(new Random().nextInt(100));
		System.out.println(nums);
		System.out.println(Arrays.toString(SelectionSort.sort(nums).toArray()));
	}
	
	@SuppressWarnings("unused")
	private static <K> List<K> swap(List<K> list, int i, int j) {
		K temp = list.get(i);
		list.set(i, list.get(j));
		list.set(j, temp);
		return list;
	}

	public static <K extends Comparable<K>> List<K> sort(List<K> unsorted) {
		int min = 0;
		K x;
		for (int i=0; i<unsorted.size()-1; i++) {
			for (int j=i+1; j<unsorted.size(); j++)
				if (unsorted.get(j).compareTo(unsorted.get(min)) < 0)
					min = j;
//			if (min != i)				// makes sorting unstable
//				swap(unsorted, i, min);
			x = unsorted.get(min);		// makes stable sorting by
			for (int j=min; j>i; j--)	// shifting elements
				unsorted.set(j, unsorted.get(j-1));	
			unsorted.set(i, x);
		}
		return unsorted;
	}

}
