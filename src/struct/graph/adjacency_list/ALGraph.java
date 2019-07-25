package struct.graph.adjacency_list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import struct.contract.GraphContract;

/**
 * Implemented using adjacency lists :
 * - Vertices are stored as objects and every vertex stores a list of vertices
 *
 * @oaram Vertex<V> The vertex object used for this graph 
 * @param <V> Type of data for vertices
 * @param <D> Type of data for edges
 */
public class ALGraph<V, D extends Number> implements GraphContract<Vertex<V>, V, D> {
	private List<Vertex<V>> nodes;
	
	public static void main(String[] args) {
		ALGraph<String, Integer> graph = new ALGraph<>();
		Vertex<String> v0, v1, v2, v3;
		graph.addVertex(v0 = new Vertex<String>("node0"));
		graph.addVertex(v1 = new Vertex<String>("node1"));
		graph.addVertex(v2 = new Vertex<String>("node2"));
		graph.addVertex(v3 = new Vertex<String>("node3"));
		graph.addEdge(v0, v1);
		graph.addEdge(v0, v2);
		graph.addEdge(v2, v3);
		System.out.println(graph);
		System.out.println(graph.adjacent(v0, v1));
		System.out.println(graph.neighbours(v0));
	}
	
	// Constructors
	private ALGraph(List<Vertex<V>> nodes) {
		this.nodes = nodes;
	}
	
	public ALGraph() {
		this(new ArrayList<>());
	}

	// Graph methods
	@Override
	public Vertex<V> addVertex(Vertex<V> x) {
		nodes.add(x);
		return x;
	}

	@Override
	public Vertex<V> removeVertex(Vertex<V> x) {
		nodes.remove(x);
		return x;
	}
	
	@Override
	public ALGraph<V, D> addEdge(Vertex<V> x, Vertex<V> y) {
		x.connect(y);
		y.connect(x);
		return this;
	}

	@Override
	public ALGraph<V, D> removeEdge(Vertex<V> x, Vertex<V> y) {
		x.disconnect(y).disconnect(x);
		return this;
	}

	@SuppressWarnings("unchecked")	// cause D extemds Number
	@Override
	public D getEdgeValue(Vertex<V> x, Vertex<V> y) {
		return (D) x.getEdgeData(y);
	}
	
	@Override
	public V getVertexValue(Vertex<V> x) {
		return x.getData();
	}

	@Override
	public Vertex<V> setVertexValue(Vertex<V> x, V data) {
		x.setData(data);
		return x;
	}

	@Override
	public ALGraph<V, D> setEdgeValue(Vertex<V> x, Vertex<V> y, D data) {
		x.setEdgeData(y, (Double) data);
		return this;
	}

	@Override
	public boolean adjacent(Vertex<V> x, Vertex<V> y) {
		return neighbours(x).contains(y);
	}

	@Override
	public List<Vertex<V>> neighbours(Vertex<V> x) {
		List<Vertex<V>> neighbours = new ArrayList<>();
		for (Edge<V> edge : x.getEdges()) neighbours.add(edge.getVertex());
		return neighbours;
	}

	@Override
	public String toString() {
		return Arrays.toString(nodes.toArray());
	}
}
