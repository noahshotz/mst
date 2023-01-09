package com.mst;
import graph.*;

/**
 * Hello world!
 */
public final class App {
    public static void main(String[] args) {

         // generate new graph object
         primsalgorithm newObj = new primsalgorithm();
         // create graph from txt-file
         Graph<Vertex, Edge<Vertex>> newGraph = newObj.readGraph("src/main/java/com/mst/BspMST/bsp_mst_2.txt");
         // run solve method
         newObj.solveToMST(newGraph);        
    }
}