package com.mst;
import graph.*;

/**
 * Hello world!
 */
public final class App {
    public static void main(String[] args) {

         // Neuen Graphen aus txt-Datei erstellen
         primsalgorithm graph = new primsalgorithm();
         Graph<Vertex, Edge<Vertex>> myGraph = graph.readGraph("src/main/java/com/mst/BspMST/bsp_mst_2.txt");

         // MST aus Graphen erstellen
         graph.solveToMST(myGraph);        
    }
}