package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public abstract class Comparables {

	public static <K> int indexOfUltimum(List<K> values, Comparator<K> c) {
		int index = 0;
		K ultimum = values.get(0);
		for (int i=0; i<values.size(); i++) {
			if (c.compare(values.get(i), ultimum) < 0) {
				ultimum = values.get(i);
				index = i;
			}
		}
		return index;
	}
	
	public static <K extends Comparable<K>> K max(List<K> values) {
		K max = values.get(0);
		for (K value : values) if (value.compareTo(max) > 0) max = value;
		return max;
	} 
	
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>(Arrays.asList(41,62,34,24,35,64,87));
		System.out.println(max(list));
		System.out.println(list.get(indexOfUltimum(list, Comparator.<Integer>reverseOrder())));
	}

}
