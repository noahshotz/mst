package com.mst;

import graph.*;

/**
 * Hello world!
 */
public final class App {
    public static void main(String[] args) {

        primsalgorithm newObj = new primsalgorithm();
        Graph<Vertex, Edge<Vertex>> myGraph = newObj.createGraph();
        newObj.solve(myGraph);
    }
}
