package math.algebra;

import math.contract.FieldAlgebra;

public class StringAlgebra implements FieldAlgebra<String> {

	public static void main(String[] args) {
		StringAlgebra a = new StringAlgebra();
		System.out.println(a.inverse("abcd"));
	}
	
	@Override
	public String additiveIdentity() {
		return "";
	}

	@Override
	public String multiplicativeIdentity() {
		return additiveIdentity();
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
		String temp, result = temp = additiveIdentity();
		for (int i=0; i<a.length(); i++, result += temp, temp = additiveIdentity())
			for (int j=0; j<b.length(); j++) 
				temp += (""+a.charAt(i))+(""+b.charAt(j));	// convert to string explicitly
		return result;
	}

	@Override
	public String multiply(String k, double lambda) {
		String result = additiveIdentity();
		for (int i=0; i<(int) lambda; i++) result += k;
		// get percentage left to add
		int chars = (int) ((lambda - (int) lambda)*k.length());	
		for (int i=0; i<chars; i++) result += ""+k.charAt(i);
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
