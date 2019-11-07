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
	private static final int TEXT_SIZE = 50, LEVEL_HEIGHT = 150, SCROLL_UNITS = 16;
	public static final Dimension SCREEN = Toolkit.getDefaultToolkit().getScreenSize(); 
	
	private JScrollPane scrollPane;
	private Draw draw;
	
	private TreeContract<V> tree;
	
	private TreeView(Draw draw, Dimension size, TreeContract<V> tree) {
		this.draw = draw;
		this.tree = tree;
		int w = (int) size.getWidth(), 
			h = (int) size.getHeight(), 
			x = (int) (SCREEN.getWidth()-w),
			y = 0;
		if (size.getWidth() > SCREEN.getWidth()) 
			x = (int) (SCREEN.getWidth() - (w = (int) SCREEN.getWidth()/2));
		setBounds(x, y, w, h);
		setTitle("Generic Tree View");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		add(scrollPane = new JScrollPane(new TreePanel(size),
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		scrollPane.getVerticalScrollBar().setUnitIncrement(SCROLL_UNITS);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(SCROLL_UNITS);
		setVisible(true);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension((int) SCREEN.getWidth(), (int) SCREEN.getHeight());
	}
	
	public class TreePanel extends JPanel {
		private static final long serialVersionUID = -6548858684587265230L;
		private Dimension size;
		
		public TreePanel(Dimension size) {
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
				TreeContract<V> node
		) {
			int xNode = levelWidthStart+levelWidth/2,
				yNode = levelHeightStart+levelHeight/2,
				xChild, yChild;	// to draw line
			drawCenteredString(g, String.format(draw.format, node.getValue()), xNode, yNode);
			if (node.isLeaf()) return;		// stop propagating
			List<? extends TreeContract<V>> children = node.getChildren();
			int childWidth = levelWidth/children.size(), childWidthStart, childHeightStart;
			for (int i=0; i<children.size(); i++) {
				childWidthStart = levelWidthStart + childWidth*i;	// Subdivide width based on children
				childHeightStart = levelHeightStart + levelHeight;  // next start height is always gonna be next level
				xChild = childWidthStart + childWidth/2;
				yChild = childHeightStart + levelHeight/2;
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
		VALUES("%1$s"), PAIRS("(%s, %s)"), FORMAT("%s");
		
		public static final Draw[] immutables = { VALUES, PAIRS };
		public String format;
		      
		private Draw(String format) {
			this.format = format;
		}
		
		public Draw setFormat(String format) {	// Only FORMAT is mutable
			for (Draw type : immutables)			
				if (equals(type))				// run-time mutability check
					return this;						
			this.format = format;
			return this;
		}
	}
	
	public static <K, V> TreeView<K, V> display(Draw draw, Dimension size, TreeContract<V> tree) {
		return new TreeView<>(draw, size, tree);
	}
	
	public static <K, V> TreeView<K, V> display(Draw draw, TreeContract<V> tree) {
		return display(draw, SCREEN, tree);
	}
	
	public static <K, V> TreeView<K, V> displayValues(Dimension size, TreeContract<V> tree) {
		return display(Draw.VALUES, size, tree);
	}
	
	public static <K, V> TreeView<K, V> displayFormat(String format, Dimension size, TreeContract<V> tree) {
		return display(Draw.FORMAT.setFormat(format), size, tree);
	}
	
	public static <K, V> TreeView<K, V> displayValues(TreeContract<V> tree) {
		return display(Draw.VALUES, tree);
	}
	
	public static <K, V> TreeView<K, V> displayFormat(String format, TreeContract<V> tree) {
		return display(Draw.FORMAT.setFormat(format), tree);
	}
}
