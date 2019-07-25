package struct.contract;

public interface ListContract<E> {
	public int size();
	public void add(E e);
	public E remove(int index);
	public E get(int index);
}