package struct.contract;

import java.util.List;

// 3 types listed as examples here : https://en.wikipedia.org/wiki/Graph_(abstract_data_type)#Representations
public interface GraphContract<V, N, D extends Number> {
	V addVertex(V x);
	V removeVertex(V x);
	GraphContract<V, N, D> addEdge(V x, V y);
	GraphContract<V, N, D> removeEdge(V x, V y);
	N getVertexValue(V x);
	D getEdgeValue(V x, V y);
	V setVertexValue(V x, N data);
	GraphContract<V, N, D> setEdgeValue(V x, V y, D data);
	
	boolean adjacent(V x, V y);
	List<V> neighbours(V x);
}
