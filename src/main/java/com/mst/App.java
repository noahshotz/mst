package com.mst;
import graph.*;

public final class App {
    public static void main(String[] args) {

         // Neuen Graphen aus txt-Datei erstellen
         primsalgorithm newGraph = new primsalgorithm();
         Graph<Vertex, Edge<Vertex>> myGraph = newGraph.readGraph("src/main/java/com/mst/BspMST/bsp_mst_2.txt");

         // MST aus Graphen erstellen
         Graph<Vertex, Edge<Vertex>> myMST = newGraph.solveToMST(myGraph);
         newGraph.getTotalWeight(myMST);
    }
}