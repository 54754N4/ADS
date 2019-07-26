package math.algebra;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import math.contract.FieldAlgebra;

public class CollectionAlgebra<K> implements FieldAlgebra<Collection<K>> {

	@Override
	public Collection<K> additiveIdentity() {
		return new ArrayList<>();
	}

	@Override
	public Collection<K> multiplicativeIdentity() {
		return new ArrayList<>();
	}

	@Override
	public Collection<K> add(Collection<K> a, Collection<K> b) {
		List<K> list = new ArrayList<>();
		list.addAll(a);
		list.addAll(b);
		return list;
	}

	@Override
	public Collection<K> substract(Collection<K> a, Collection<K> b) {
		List<K> list = new ArrayList<>();
		list.addAll(a);
		list.removeAll(b);
		return list;
	}

	@Override
	public Collection<K> multiply(Collection<K> a, Collection<K> b) {
		List<K> list = new ArrayList<>();
		Iterator<K> ia = a.iterator(), ib = b.iterator();
		while (ia.hasNext() && ib.hasNext()) {
			list.add(ia.next());
			list.add(ib.next());
		}
		return list;
	}

	@Override
	public Collection<K> multiply(Collection<K> k, double lambda) {
		boolean negative = lambda < 0;
		List<K> list = new ArrayList<>();
		int count = (int) lambda;
		while (count-->0)
			for (K item : k)
				if (negative) list.add(0, item);
				else list.add(item);
		count = k.size()*((int)(lambda - (int) lambda)); // elements * percentage
		Iterator<K> it = k.iterator();
		while (it.hasNext() && count-->0) {
			if (negative) list.add(0, it.next());
			else list.add(it.next());
		}
		return list;
	}

	@Override
	public Collection<K> inverse(Collection<K> k) {
		List<K> list = new ArrayList<>();
		list.addAll(k);
		Collections.reverse(list);
		return list;
	}
}
