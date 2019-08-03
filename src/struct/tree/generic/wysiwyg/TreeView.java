package struct.tree.generic.wysiwyg;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import struct.contract.TreeContract;

public class TreeView<K, V> extends JFrame {
	private static final long serialVersionUID = -560765582426517432L;
	
	private static final int SCROLL_UNITS = 16;
	private static final Dimension SCREEN = Toolkit.getDefaultToolkit().getScreenSize();
	private JScrollPane scrollPane;
	private Draw draw;
	
	private TreeView(Draw draw, Dimension size, TreeContract<K, V> tree) {
		this.draw = draw;
		int x = (int) ((SCREEN.getWidth()-size.getWidth())/2),
			y = (int) ((SCREEN.getHeight()-size.getHeight())/2),
			w = (int) size.getWidth(), 
			h = (int) size.getHeight();
		setBounds(x, y, w, h);
		setTitle("Generic Tree View");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(size);
		add(scrollPane = new JScrollPane(new TreePanel(size, tree),
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		scrollPane.getVerticalScrollBar().setUnitIncrement(SCROLL_UNITS);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(SCROLL_UNITS);
		setVisible(true);
	} 
	
	public static <K, V> TreeView<K, V> display(Draw draw, Dimension size, TreeContract<K, V> tree) {
		return new TreeView<>(draw, size, tree);
	}
	
	public class TreePanel extends JPanel {
		private static final long serialVersionUID = -6548858684587265230L;
		private static final int TEXT_SIZE = 50, LEVEL_HEIGHT = 150;
		private TreeContract<K, V> tree;
		private Dimension size;
		
		public TreePanel(Dimension size, TreeContract<K, V> tree) {
			 this.tree = tree;
			 this.size = size;
		}
		
		@Override
		public Dimension getPreferredSize() {
			return new Dimension((int) size.getWidth(), LEVEL_HEIGHT*(1+tree.height()));
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setFont(new Font("TimesRoman", Font.PLAIN, TEXT_SIZE));
			drawNode(g, 0, 0, (int) (size.getWidth()), LEVEL_HEIGHT, tree);
		}
		
		private void drawNode(
				Graphics g, 
				int levelWidthStart, int levelHeightStart, 
				int levelWidth, int levelHeight, 
				TreeContract<K, V> node
		) {
			int xNode = levelWidthStart+levelWidth/2,
				yNode = levelHeightStart+levelHeight/2,
				xChild, yChild;	// to draw line
			drawCenteredString(g, String.format(draw.format, node.getKey(), node.getValue()), xNode, yNode);
			if (node.isLeaf()) return;		// stop propagating
			List<? extends TreeContract<K, V>> children = node.getChildren();
			int childWidth = levelWidth/children.size(), childWidthStart, childHeightStart;
			for (int i=0; i<children.size(); i++) {
				childWidthStart = levelWidthStart + childWidth*i;	// Subdivide width based on children
				childHeightStart = levelHeightStart + levelHeight;  // next start height is always gonna be next level
				xChild = childWidthStart+childWidth/2;
				yChild = childHeightStart+levelHeight/2;
				g.drawLine(xNode, yNode, xChild, yChild);
				drawNode(g, 
					childWidthStart,
					childHeightStart, 
					childWidth,				// available width that the child node can use
					levelHeight,			// height of each level is predetermined before recursion
					children.get(i));		// divide and conquer
			}
		}
		
		private void drawCenteredString(Graphics g, String string, int x, int y) {
			Rectangle2D text = g.getFontMetrics().getStringBounds(string, g);
			g.drawString(string, (int) (x-text.getWidth()/2), (int) (y+text.getHeight()/4));
		}
	}
	
	public static enum Draw { 
		KEYS("%1$s"), VALUES("%2$s"), PAIRS("(%s, %s)"), FORMAT("%s");
		
		public static final Draw[] immutable = {KEYS, VALUES, PAIRS};
		public String format;
		      
		private Draw(String format) {
			this.format = format;
		}
		
		public Draw setFormat(String format) {	// Only FORMAT is mutable
			for (Draw type : immutable)			
				if (equals(type))				// run-time mutability check
					return this;						
			this.format = format;
			return this;
		}
	}
}
