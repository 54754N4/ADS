package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Util {

	public static <K extends Comparable<K>> K max(List<K> values) {
		K max = values.get(0);
		for (K value : values) if (value.compareTo(max) > 0) max = value;
		return max;
	} 
	
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>(Arrays.asList(41,62,34,24,35,64,87));
		System.out.println(max(list));
	}

}
