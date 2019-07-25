package struct.graph.adjacency_list;

public class Edge<D> {
	private Double data;
	private Vertex<D> to;
	
	public Edge(double data, Vertex<D> to) {
		this.data = data;
		this.to = to;
	}
	
	public Edge(Vertex<D> to) {
		this(1, to);
	}
	
	public double getData() {
		return data;
	}

	public Vertex<D> getVertex() {
		return to;
	}

	public void setData(double data) {
		this.data = data;
	}
	
	public boolean equals(Vertex<D> to) {
		return this.to.equals(to);
	}
	
	public boolean equals(Edge<D> e) {
		return equals(e.to);
	}
	
	@Override
	public String toString() {
		return to.getData().toString()+"="+data;
	}
}
