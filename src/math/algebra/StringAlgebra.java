package math.algebra;

import math.contract.LatticeAlgebra;

public class StringAlgebra implements LatticeAlgebra<String> {

	public static void main(String[] args) {
		StringAlgebra a = new StringAlgebra();
		System.out.println(a.inverse("abcd"));
		System.out.println(a.multiply("abcd", ""));
		System.out.println(a.multiply("abcd", "*"));
	}
	
	@Override
	public String additiveIdentity() {
		return "";
	}

	@Override
	public String multiplicativeIdentity() {
		return "*";
	}

	@Override
	public String add(String a, String b) {
		return a+b;
	}

	@Override
	public String substract(String a, String b) {
		return a.replaceAll(b, "");
	}

	@Override
	public String multiply(String a, String b) {	// distributes multiplication
		if (b.equals(multiplicativeIdentity())) return a;
		else if (a.equals(multiplicativeIdentity())) return b;
		// Otherwise concatenate every char from a to every char from b
		String temp, result = temp = additiveIdentity();
		for (int i=0; i<a.length(); i++, result += temp, temp = additiveIdentity())
			for (int j=0; j<b.length(); j++)
				temp += (""+a.charAt(i))+(""+b.charAt(j)); // convert to string explicitly
		return result;
	}

	@Override
	public String multiply(String word, double lambda) {
		String result = additiveIdentity();
		for (int i=0; i<(int) lambda; i++) result += word;
		// get percentage left to add
		int chars = (int) ((lambda - (int) lambda)*word.length());	
		for (int i=0; i<chars; i++) result += ""+word.charAt(i);
		return result;
	}

	@Override
	public String inverse(String k) {
		String result = additiveIdentity();
		for (int i=k.length()-1; i >= 0; i--) result += ""+k.charAt(i); 
		return result;
	}
	
	@Override
	public String divide(String a, String b) {
		return multiply(a, inverse(b));
	}

}
