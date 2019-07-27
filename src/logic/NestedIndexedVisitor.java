package logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

@SuppressWarnings("unchecked")
public class NestedIndexedVisitor<K> {	// multidimensional list looper
	private int dimension;
	private Collection<? extends Collection<?>> collection;
	
	public NestedIndexedVisitor(int dimension, Collection<? extends Collection<?>> collection) {
		this.collection = collection;
		this.dimension = dimension;
	}
	
	public void forEach(IndexedVisitor<K> visitor) {
		forEach(new ArrayList<>(), collection, visitor);
	}
	
	private void forEach(List<Integer> coords, Collection<?> collection, IndexedVisitor<K> visitor) {
		int i = 0; // count iterated elements at each dimension
		if (isLast(coords)) { // reached lowest dimension then visit elements 
			Iterator<K> items = (Iterator<K>) collection.iterator();
			while (items.hasNext()) 
				visitor.visit(addCoord(i++, coords).toArray(new Integer[0]), items.next());
		} else { // otherwise loop into lower dimension
			Iterator<Collection<? extends Collection<?>>> iterator = 
					(Iterator<Collection<? extends Collection<?>>>) collection.iterator();
			coords = addCoord(0, coords); // add coord for current dimension
			while (iterator.hasNext()) 
				forEach(setLastCoord(i++, coords), iterator.next(), visitor); // recurse into oblivion ~
		}
	}	
	
	private List<Integer> addCoord(int newCoord, List<Integer> coords) {
		List<Integer> newCoords = copy(coords);
		newCoords.add(newCoord);
		return newCoords;
	}
	
	private List<Integer> setLastCoord(int newCoord, List<Integer> coords) {
		List<Integer> newCoords = copy(coords);
		newCoords.set(newCoords.size()-1, newCoord);
		return newCoords;
	} 
	
	private List<Integer> copy(List<Integer> coords) {
		List<Integer> copy = new ArrayList<>();
		for (int coord : coords) copy.add(coord);
		return copy;
	}
	
	private boolean isLast(List<Integer> coords) {
		if (coords.size() == 0) return false;
		return coords.size() >= dimension - 1;
	}
	
	public static String print(List<Integer> coords) {
		StringBuilder sb = new StringBuilder();
		for (int component : coords) sb.append(component+",");
		return sb.deleteCharAt(sb.length()-1).toString();
	}
	
	// for debug
	public static List<?> randomNestedArray(int dimension) {
		Random rand = new Random();
		if (dimension == 1) return Arrays.asList(rand.nextInt(100), rand.nextInt(100)); 
		List<List<?>> list = new ArrayList<>(), random = new ArrayList<>();
		int stop = rand.nextInt(4)+1;
		while (stop-->0) random.add(randomNestedArray(dimension-1));
		list.addAll(random);
		return list;
	}

	public static void main(String[] args) {
		int dimension = 10;
		List<List<List<List<List<List<List<List<List<List<Integer>>>>>>>>>> list = 
				(List<List<List<List<List<List<List<List<List<List<Integer>>>>>>>>>>) NestedIndexedVisitor.randomNestedArray(dimension);
		System.out.println(list);
		NestedIndexedVisitor<Integer> looper = new NestedIndexedVisitor<>(dimension, list);
		looper.forEach((coords, elem) -> {
			String niceString = 
					String.format("(%s)->%s", print(Arrays.asList(coords)), elem.toString());
			System.out.println(niceString);
		});
	}
}

@FunctionalInterface
interface IndexedVisitor<K> {
	void visit(Integer[] coords, K elem);
}
