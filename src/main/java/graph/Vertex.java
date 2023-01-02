package graph;

/** A class which represents a vertex of a graph
 * 
 * @author ripphausen
 * @version 2.0
 */
public class Vertex {
	/**
	 * a unique number for the vertex
	 */
	private int id;
	/**
	 * a name for the vertex; if vertices have no name then name is set to null
	 */
	private String name;

	public Vertex(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Vertex(int id) {
		this.id = id;
		this.name = null;
	}
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		String addName = null;
		if (name != null) {
			addName = " (" + name + ")";
			return new Integer(id).toString() + addName;
		}	
		else
			return new Integer(id).toString();
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		if (id != other.id)
			return false;
		return true;
	}	
}