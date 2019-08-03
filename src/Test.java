import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Test {

	public static void main(String[] args) {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int h, size = 500,
			x = (int) ((screen.getWidth()-size)/2),
			y = (int) ((screen.getHeight()-size)/2),
			w = h = size;
		JFrame frame = new JFrame();
		frame.setBounds(x, y, w, h);
		frame.setTitle("a title");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(new APanel());
		
		frame.setVisible(true);
	}
	
	static class APanel extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.draw3DRect(50, 50, 100, 100, true);
		}
	}
 
	private static void test3() {
		test1(1d);
		test2(1);
		
		test1(1);
//		test2(1d);		// error ~~
	}
	
	private static void test1(double x) {
		
	}
	
	private static void test2(int x) {
		
	}

}
