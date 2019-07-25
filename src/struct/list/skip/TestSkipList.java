package struct.list.skip;

public class TestSkipList {
	public static void main(String[] args) {
		SkipList list = new SkipList(0);
		Node head = list.getHead();
		System.out.println(head);
		for (int i=1; i<10; i++)
			list.insert(i);
		System.out.println(list);
		
	}
}
