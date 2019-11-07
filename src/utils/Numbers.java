package utils;

import java.util.Arrays;

public abstract class Numbers {

	public static int[] until(int n) {
		int[] nums = new int[n];
		while (--n >= 0) nums[n] = n;
		return nums;
	}
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(until(10)));
	}

}
