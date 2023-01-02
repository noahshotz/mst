package graph;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;



/** class for constructing graphs which are read of files
 * 
 * @author ripphausen
 * @version 2.0
 */
public class GraphRead {
	/**
	 * Constructs a graph from a file
	 * @param dat a file with values in the following format: 
	 *   	  1. row: number of vertices (n)
	 *   	  2. row: number of edges (m)
	 *        only if standardIds = false: The subsequent n rows: the ids of the vertices and the name of the vertices, separated by a blank
	 *        The subsequent m rows: start- and endvertex of the edge and the weight of the edge (only if weighted = true), separated by blanks	 
	 * @param directed true iff the graph is directed
	 *        in the case directed = true: each edge e = (a,b) in dat is only added to the adjacency list of a
	 *        in the case directed = false: each edge e = (a,b) is added into the adjacency list of a and in the adjacency list of b 
	 * @param standardIds true if the vertices have ids between 0 and n-1 and no names
	 * @param weighted true if the edges are weighted
	 * @return the graph
	 */
	public static Graph<Vertex,Edge<Vertex>> FileToGraph(String dat, boolean directed, boolean standardIds, boolean weighted) {
		FileInputStream fis = null;
		Graph<Vertex,Edge<Vertex>> G = null;
		try {
		  fis = new FileInputStream(dat);
		}
		catch ( Exception e) {
			System.out.println(dat + " couldn't be opened");
			System.out.println(e.getMessage());
		}
		try {
		      InputStreamReader isr   = new InputStreamReader(fis);
		      BufferedReader    br = new BufferedReader   (isr);

		      // read number of vertices
		      String aRow;
		      aRow = br.readLine();
		      int n = new Integer(aRow);

		      // read number of edges
		      aRow = br.readLine();
		      int m = new Integer(aRow);
		      
		      // construct graph
			  G = new Graph<Vertex,Edge<Vertex>>(n, directed);
		      
		      if (!standardIds) { // vertices have arbitrary ids and get a name
				  // read vertices (1. substring: id, 2. substring: name)
			      for (int i = 1; i <= n; i++) {
			    	  aRow = br.readLine();
			    	  int sepIndex1 = aRow.indexOf(' ');
			    	  String vId = aRow.substring(0, sepIndex1);
			    	  String vStr = aRow.substring(sepIndex1+ 1, aRow.length());
			    	  int id = new Integer(vId);
			    	  Vertex v = new Vertex(id,vStr);
			    	  G.addVertex(v);
			      }
		      }
		      else {
		  		// add vertices with standard ids (0 .. n-1)
		  		for (int i = 0; i < n; i++) {
		  			G.addVertex(new Vertex(i));
		  		}
		      }
		      // read edges with weight
		      if (weighted) {
			      for (int i = 1; i <= m; i++) {
			    	  aRow = br.readLine();
			    	  int sepIndex1 = aRow.indexOf(' ');
			    	  int sepIndex2 = aRow.indexOf(' ', sepIndex1+1);
			    	  String vStr = aRow.substring(0, sepIndex1);
			    	  String uStr = aRow.substring(sepIndex1+ 1, sepIndex2);
			    	  String wStr = aRow.substring(sepIndex2+ 1, aRow.length());
			    	  int vId = new Integer(vStr);
			    	  int uId = new Integer(uStr);
			    	  int w = new Integer(wStr);
			    	  
			    	  Vertex v = G.getVertex(vId);
			    	  Vertex u = G.getVertex(uId);
			    	  
			    	 G.addEdge(new Edge<Vertex>(v,u,w));
			      }
		      }
		      else { // read edges without weight; 
			      for (int i = 1; i <= m; i++) {
			    	  aRow = br.readLine();
			    	  int sepIndex1 = aRow.indexOf(' ');
			    	  String vStr = aRow.substring(0, sepIndex1);
			    	  String uStr = aRow.substring(sepIndex1+ 1, aRow.length());
			    	  int vId = new Integer(vStr);
			    	  int uId = new Integer(uStr);
			    	  
			    	  Vertex v = G.getVertex(vId);
			    	  Vertex u = G.getVertex(uId);
			    	  
			    	 G.addEdge(new Edge<Vertex>(v,u));
			      }  
		      }
			fis.close();
		  }
		  catch (Exception e) {
				System.out.println("Reading was not successful");
				System.out.println(e.getMessage());
		  }	

		return G;	

	}
}