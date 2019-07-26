package math.algebra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import math.contract.FieldAlgebra;

public class ListAlgebra<K> implements FieldAlgebra<List<K>> {
	private final FieldAlgebra<K> algebra;	// for elements
	
	public static void main(String[] args) throws Exception {
		ListAlgebra<Double> la = new ListAlgebra<>(new DoubleAlgebra());
//		System.out.println(la.add(Arrays.asList(0d, 1d), Arrays.asList(1d, 2d, 2d, 3d)));
		System.out.println(la.substract(Arrays.asList(0d, 1d), Arrays.asList(1d, 2d, 2d, 3d)));
		System.out.println(la.multiply(Arrays.asList(0d, 1d, 2d, 3d), 3.5));
	}
	
	public ListAlgebra(FieldAlgebra<K> algebra) {
		this.algebra = algebra;
	}
	
	@Override
	public List<K> additiveIdentity() {
		return new ArrayList<>();
	}

	@Override
	public List<K> multiplicativeIdentity() {
		return Arrays.asList(algebra.multiplicativeIdentity());
	}

	@Override
	public List<K> add(List<K> a, List<K> b) {
		List<K> list = new ArrayList<>();
		Iterator<K> ita = a.iterator(), itb = b.iterator();
		while (ita.hasNext() && itb.hasNext()) list.add(algebra.add(ita.next(), itb.next()));
		for (Iterator<K> it : Arrays.asList(ita, itb)) while (it.hasNext()) list.add(it.next());
		return list;
	}

	@Override
	public List<K> substract(List<K> a, List<K> b) {
		return add(a, multiply(b, -1));
	}

	@Override
	public List<K> multiply(List<K> a, List<K> b) {
		List<K> list = new ArrayList<>();
		for (int i=0; i<a.size(); i++)
			for (int j=0; j<b.size(); j++)
				list.add(algebra.multiply(a.get(i), b.get(j)));
		return list;
	}

	@Override
	public List<K> multiply(List<K> k, double lambda) {
		List<K> list = new ArrayList<>();
		Iterator<K> it = k.iterator();
		while (it.hasNext()) list.add(algebra.multiply(it.next(), lambda));
		return list;
	}

	@Override
	public List<K> inverse(List<K> k) {
		List<K> list = new ArrayList<>();
		for (K item : k) list.add(algebra.inverse(item));
		return list;
	}

	@Override
	public List<K> divide(List<K> a, List<K> b) {
		return multiply(a, inverse(b));
	}

}
