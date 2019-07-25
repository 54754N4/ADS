package struct.list.linked;

public class TestLinkedList {

	public static void main(String[] args) {
		LinkedList<Integer> ll = new LinkedList<>();
		for (int i=0; i<10; i++)
			ll.add(i);
		
		for (int i=0; i<10; i++)
			System.out.println(ll.get(i));
	}

}