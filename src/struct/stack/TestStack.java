package struct.stack;

public class TestStack {
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();
		int max = 10;
		for (int i=1; i<max+1; i++) {
			if (i < max/2+1) stack.push(i);
			else stack.pop();
			System.out.println(stack.peek());
		}
	}
}
