package com.mst;

import graph.*;

/**
 * Hello world!
 */
public final class App {
    public static void main(String[] args) {

        // generate new graph object
        primsalgorithm newObj = new primsalgorithm();
        // generate sample graph to solve
        Graph<Vertex, Edge<Vertex>> newGraph = newObj.createGraph();
        // run solve method
        newObj.solve(newGraph);
    }
}
