package graph;

import java.util.*;

/** A class which represents the data structure graph;
 * internally the graph is represented by adjacency lists 
 * @author ripphausen
 * @version 2.0
 * @param <V> a subclass of class Vertex representing the vertices of a graph 
 * @param <E> a subclass of class Edge<V> representing the edges of a graph
 */
public class Graph <V extends Vertex, E extends Edge<V>> {
	/** 
	 * maps a vertex-id to the index, where the corresponding vertex is positioned in
	 * the list vertices and the incident edges are positioned in adjList 
	 */
	private HashMap<Integer, Integer> vertexIndex; 

	/** 
	 *  a list with all vertices of the graph; notice, if a vertex is removed from the graph
	 *  then the corresponding entry in vertices is set to null 
	 *  */
	private ArrayList<V> vertices;	
	
	/** 
	 *  a list with all incident edges of every vertex;
	 *  if a vertex v is positioned at index i, then the adjacency list of v is  
	 *  positioned at index i, too
	 */
	private ArrayList<LinkedList<E>> adjList; 

	/** 
	 *  true iff graph is directed;
	 *  notice, an edge in a undirected graph is contained in the adjacency lists of both
	 *  vertices (a and b) of the edge
	 */
	private boolean isDirected; 
			
	/** number of vertices in graph
	 * 
	 */
	private int noVertices;
	
	/**
	 * initializes an empty graph 
	 * @param isDirected true iff the graph is directed
	 */
	public Graph(boolean isDirected) {
		vertexIndex = new HashMap<Integer, Integer>();
		vertices = new ArrayList<V> ();
		adjList = new ArrayList<LinkedList<E>> ();
		this.isDirected = isDirected;
		this.noVertices = 0;
	}
	
	/**
	 * initializes an empty graph, which initially contains n vertices;
	 * @param n number of vertices
	 * @param isDirected true iff graph is directed
	 */
	public Graph(int n, boolean isDirected) {
		vertexIndex = new HashMap<Integer, Integer>((int) Math.round(Math.ceil(n * 1.25)));
		// because of performance the hashtable should be larger as necessary
		vertices = new ArrayList<V> (n + 10);
		adjList = new ArrayList<LinkedList<E>> (n + 10);
		this.isDirected = isDirected;
		this.noVertices = 0; // noVertices is updated by addVertex and removeVertex
	}

	/**
	 * initializes a graph with given vertexset and edgeset 
	 * @param vertexset the set of vertices of the graph with pairwise different id's 
	 * @param edgeset the set of edges of the graph
	 * @param isDirected true iff the graph is directed
	 */
	public Graph(Collection<V> vertexset, Collection<E> edgeset, boolean isDirected) {
		int n = vertexset.size();
		vertexIndex = new HashMap<Integer, Integer>((int) Math.round(Math.ceil(n * 1.25)));
		vertices = new ArrayList<V> (n + 10);
		adjList = new ArrayList<LinkedList<E>> (n + 10);
		this.isDirected = isDirected;

		// for every vertex initialize an empty adjacency list
		for (int i = 0; i < n; i++) {
			adjList.add(new LinkedList<E>());
		}
		
		// for every vertex: add vertex in list vertices
		// the index of the vertex in list vertices is put into the hashmap vertexIndex
		int i = 0;
		for (V v : vertexset) {
			Integer idx = vertexIndex.put(v.getId(), i); 
			if (idx != null) throw new RuntimeException("dublicated vertex-id");
			vertices.add(v);	// add vertex in list vertices
			this.noVertices++;
			i++;
		}
		
		// Each edge e = (a,b) is inserted in the adjacency list of vertex a 
		for (E e: edgeset) {
			V a = e.getVertexA();
			V b = e.getVertexB();
			Integer IIndA = vertexIndex.get(a.getId());
			if (IIndA== null) 
				throw new RuntimeException("vertex a of edge doesn't exist");
			Integer IIndB = vertexIndex.get(b.getId());
			if (IIndB== null) 
				throw new RuntimeException("vertex b of edge doesn't exist");

			int indA = IIndA;
			LinkedList<E> neighbours = adjList.get(indA);
			neighbours.add(e);
		}
		
		if (!isDirected) { // for each edge e = (a,b) is also inserted in neighbor list of b
			for (E e: edgeset) {
				V b = e.getVertexB();
				Integer IIndB = vertexIndex.get(b.getId());
				// non existent vertices must be found during inserting (a,b)

				int indB = IIndB;
				LinkedList<E> neighbours = adjList.get(indB);
				neighbours.add(e);
			}
		}
	}


	/**
	 * gives the number of the vertices of the graph
	 * @return number of vertices of the graph
	 */
	public int getNumberVertices() {
		return this.noVertices;
	}
	
	/**
	 * determines the vertex with given id 
	 * @param id an id (unique number)
	 * @return vertex with id, if exists; null otherwise
	 */
	public V getVertex(int id) {
		Integer idx = vertexIndex.get(id);
		if (idx != null) {
			V v = vertices.get(idx);
			return v;
		}
		else // vertex with id doesn't exist
			return null;
	}

	/**
	 * determines the set of vertices of the graph 
	 * @return a collection with the vertices of the graph
	 */
	public Collection<V> getVertices() {
		ArrayList<V> vertices = new ArrayList<V> (this.vertices.size());
		for (V v : this.vertices) {
			if (v != null) { // vertex wasn't removed in the meantime 
				vertices.add(v);
			}
		}
		return vertices;
	}
	
	/**
	 * determines the set of edges of the graph
	 * @return the set of edges of the graph 
	 */
	public Collection<E> getEdges() {
		ArrayList<E> edges = new ArrayList<E>();
		Collection<V> vertices = this.getVertices(); // get all vertices
		for (V v: vertices) {
			int index = vertexIndex.get(v.getId());
			LinkedList<E> neighborEdges= adjList.get(index); // get neighborlist of v
			
			for (E e: neighborEdges) {
				// if graph is not directed, only edges in the neigbor list of "start vertex" is considered
				if (isDirected || !isDirected && e.getVertexA().getId()== v.getId()) {
					edges.add(e);
				}
			}
		}

		return edges;
	}

	/**
	 * determines all neighbors of vertex v
	 * @param v a vertex
	 * @return a collection with all vertices adjacent to v - the neighbors 
	 */
	public Collection<V> getNeighbours(V v) {
		int id = v.getId();
		return getNeighbours(id);
	} 

	/**
	 * determines all neighbors of a vertex given by its id 
	 * @param id the id of a vertex
	 * @return a collection with all edges incident to vertex v with id, if exists;
	 *         null otherwise
	 */
	public Collection<V> getNeighbours(int id) {
		LinkedList<V> neighbours = new LinkedList<V>();
		Integer IIndex = vertexIndex.get(id);
		if (IIndex == null) return null;
		int index = IIndex;
		LinkedList<E>	neighbourEdges = adjList.get(index);
		for (E e : neighbourEdges) {
			V b = e.getVertexB();
			if (b.getId() == id) { 
				// can only occur in the case of an undirected graph
				// where the corresponding edge is in the adjacency lists
				// of both vertices of the edge;
				b = e.getVertexA();
			}
			neighbours.addLast(b);
		}
		return neighbours;
	}

	/**
	 * determines all edges incident to vertex v
	 * @param v the vertex
	 * @return a collection with all incident edges to v 
	 */
	public Collection<E> getIncidentEdges(V v) {
		int id = v.getId();
		return getIncidentEdges(id);
	}

	/**
	 * determines all edges incident to vertex v given by its id 
	 * @param id of a vertex
	 * @return a collection with all edges incident to vertex v with id, if exists
	 */
	public Collection<E> getIncidentEdges(int id) {
		ArrayList<E> edges = new ArrayList<E> ();
		Integer IIdx = vertexIndex.get(id);
		if (IIdx == null) // no vertex with id exists
			return null;
		
		for (E e : adjList.get(IIdx)) {
			edges.add(e);
		}
		return edges;
	}

	/** 
	 * Add vertex v to graph 
	 * @param v vertex which should be added to graph 	
	 * @return true iff vertex was inserted, that is, no other vertex with same id with v exists
	 */
	public boolean addVertex(V v) {	
		// determines if vertex with same id in already contained in graph
		boolean uniqueId = ! vertexIndex.containsKey(v.getId());
		if (!uniqueId) { // vertex is not inserted
			return false;
		}
		// add vertex at the end of list of vertices
		vertices.add(v);
		this.noVertices++;
		// add empty adjacency list for vertex v
		adjList.add(new LinkedList<E>());
		int index = vertices.size() - 1;
		// put index of vertex v in indexlist
		vertexIndex.put(v.getId(), index);

		return true;
	}

	/**
	 * inserts an edge into the graph, whereby the vertices of the edge must already
	 * be contained in the graph

	 * @param e the edge to be inserted
	 * @return true, iff edge was inserted (that is the vertices of the edge are contained in the graph)
	 */
	public boolean addEdge(E e) {
		V a = e.getVertexA();
		if (a == null)
			return false;
		V b = e.getVertexB(); 
		if (b == null)
			return false;
		int idA = a.getId();
		int idB = b.getId();
		/* determines if the vertices of the edge are contained in the vertexset of the graph */
		Integer idxA = vertexIndex.get(idA);
		Integer idxB = vertexIndex.get(idB);
		if (idxA == null || idxB == null) // vertex a oder vertex b is not contained in the graph
			return false;
		if (!isDirected) { // insert edge in adjacency list of Vertex b	
			boolean  success = adjList.get(vertexIndex.get(idB)).add(e);
			if (!success) 
				return false;
		}
		// insert edge e in the adjacency list of vertex a
		return adjList.get(vertexIndex.get(idA)).add(e);
	}
	
	/**
	 * removes vertex v with all incident edges
	 * @param v the vertex to remove
	 * @return true, iff the vertex was removed
	 */
	public boolean removeVertex(V v) {
		// remove all edges with v as start vertex 
		Integer IIndex = vertexIndex.get(v.getId());
		if (IIndex == null) {
			return false;
		}
		int index = IIndex;
		adjList.get(index).clear();
		
		// remove all edges with v as end vertex
		for (LinkedList<E> list: adjList) {
			if (list != null) { // entsprechender Knoten wurde nicht zuvor entfernt
				ListIterator<E> it = list.listIterator();
				while (it.hasNext()) {
					E e = it.next();
					if (e.getVertexB().getId() == v.getId() || e.getVertexA().getId() == v.getId()){
						it.remove();
						break; // has to be removed if parallel edges can occur
					}
				}
			}
		}
		
		// remove vertex v from ArrayList vertices by setting the corresponding entries to null
		vertices.set(index, null);
		adjList.set(index, null);
		
		// remove vertex v from the index list
		vertexIndex.remove(IIndex);
		this.noVertices--;
		return true;
	}

	/**
	 * removes edge e
	 * @param e the edge to be removed
	 * @return true g.d.w. edge e could be removed
	 */
	public boolean removeEdge(E e) {
		Vertex a = e.getVertexA();
		Vertex b = e.getVertexB();
		
		int aId = a.getId();
		Integer aIdx = vertexIndex.get(aId);
		if (aIdx == null) {
			return false; // vertex could not be found
		}
		
		int bId = b.getId();
		Integer bIdx = vertexIndex.get(bId);
		if (bIdx == null) {
			return false;
		}
		
		
		if (!isDirected) {
			// edge must be removed from neighbor list of b 
			// for all edges incident to b
			ListIterator<E> it = adjList.get(bIdx).listIterator();
			while (it.hasNext()) {
				E le = it.next(); 
				if (le.getVertexA().getId() == a.getId() || le.getVertexB().getId() == a.getId()){
					// edge e is found
					it.remove();
					break; // has to be removed if parallel edges can occur
				}
			}
		}
		
		//edge must be removed from neighbor list of a
		// for all edges incident to a
		ListIterator<E> it = adjList.get(aIdx).listIterator();
		while (it.hasNext()) {
			E le = it.next(); 
			if (le.getVertexB().getId() == b.getId() || le.getVertexA().getId() == b.getId()){
				// gesuchte Kante gefunden
				it.remove();
				return true;
			}
		}
		return false;
	}

	public String toString() {
		StringBuffer s = new StringBuffer("Graph with " + this.noVertices + " vertices; isDirected: " + isDirected + "\n");
		int i = 0;
		for (V v: vertices) {
			if (v != null) {	
				s.append(v + ": ");
				for (E e: adjList.get(i)) {
					if (e.getVertexA() == v)
						s.append(e.getVertexB() + " ");
					else
						s.append(e.getVertexA() + " ");
				}
				s.append("\n");
			}
			i++;
		}
		return new String(s);
	}
}


