package struct.contract;

public interface StackContract<E> {
	public void push(E e);
	public E pop();
	public E peek();
}