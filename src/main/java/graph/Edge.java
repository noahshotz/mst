package graph;

/** Eine Klasse die Kanten eines Graphen repraesentiert
 * 
 * @author ripphausen
 * @version 2.0
 * @param <V> a subclass of the class Vertex; an edge connects two vertices of class V
 */
public class Edge <V extends Vertex> implements Comparable<Edge<V>>{
	private V vertexA;
	private V vertexB;
	private int weight = 1;
	
	public Edge(V vertexA, V vertexB, int weight) {
		super();
		this.vertexA = vertexA;
		this.vertexB = vertexB;
		this.weight = weight;
	}

	public Edge(V a, V b) {
		vertexA = a;
		vertexB = b;
		weight = 1; // default weight
	}
	public V getVertexA() {
		return vertexA;
	}

	public V getVertexB() {
		return vertexB;
	}	
	
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public String toString() {
		return "(" + vertexA.getId() + "," + vertexB.getId() + "; g:" + this.weight + ")";
	}

	@Override	
	/**
	 * compares edge this with edge o w.r.t. the edge weights
	 */
	public int compareTo(Edge<V> o) {
		return this.getWeight() - o.getWeight();
	}
}