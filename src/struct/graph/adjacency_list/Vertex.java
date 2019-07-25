package struct.graph.adjacency_list;

import java.util.ArrayList;
import java.util.List;

public class Vertex<D> {
	private D data;
	private List<Edge<D>> edges;
	
	public static void main(String[] args) {
		Vertex<String> x = new Vertex<>("node0"), y;
		x.connect(y = new Vertex<>("node1"));
		System.out.println(x);
		System.out.println(x.disconnect(y));
		System.out.println(x);
	}
	
	private Vertex(D data, List<Vertex<D>> vertices) {
		setData(data);
		edges = new ArrayList<>();
		for (Vertex<D> vertex : vertices)
			edges.add(new Edge<>(1, vertex));
	}
	
	public Vertex(D data) {
		this(data, new ArrayList<>());
	}
	
	public Vertex<D> connect(Vertex<D> to) {
		edges.add(new Edge<>(1, to));
		return this;
	}
	
	public Vertex<D> disconnect(Vertex<D> to) {
		edges.remove(getEdge(to));
		return to;
	}
	
	public Vertex<D> setData(D data) {
		this.data = data;
		return this;
	}
	
	public D getData() {
		return data;
	}
	
	public Double getEdgeData(Vertex<D> x) {
		Edge<D> edge = getEdge(x);
		return (edge != null) ? edge.getData() : null;
	}
	
	public Vertex<D> setEdgeData(Vertex<D> x, double data) {
		getEdge(x).setData(data);
		return this;
	}
	
	public List<Edge<D>> getEdges() {
		return edges;
	}
	
	private Edge<D> getEdge(Vertex<D> v) {
		for (Edge<D> edge : edges)
			if (edge.equals(v))
				return edge;
		return null;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("("+data+"):(");
		for (Edge<D> edge : edges) 
			sb.append(edge+",");
		return sb.deleteCharAt(sb.length()-1) 	// delete last comma
				.append(")")
				.toString();
	}
}