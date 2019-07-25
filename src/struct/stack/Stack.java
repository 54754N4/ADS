package struct.stack;

import java.util.ArrayList;
import java.util.List;

import struct.contract.StackContract;

class Stack<E> implements StackContract<E> {
	private List<E> array;
	
	public Stack() {
		array = new ArrayList<>();
	}

	@Override
	public void push(E e) {
		array.add(e);
	}

	@Override
	public E pop() {
		return array.remove(array.size() - 1);
	}

	@Override
	public E peek() {
		return array.get(array.size() - 1);
	}
}