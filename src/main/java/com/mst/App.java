package com.mst;

import graph.*;

/**
 * Hello world!
 */
public final class App {
    public static void main(String[] args) {

        Graph myGraph = new Graph<>(false);
        Vertex a = new Vertex(0, "A");
        Vertex b = new Vertex(1, "B");
        Vertex c = new Vertex(2, "C");

        myGraph.addVertex(a);
        myGraph.addVertex(b);
        myGraph.addVertex(c);

        Edge ab = new Edge(a, b, 10);
        Edge ac = new Edge(a, c, 20);
        Edge bc = new Edge(b, c, 30);

        myGraph.addEdge(ab);
        myGraph.addEdge(ac);
        myGraph.addEdge(bc);

        System.out.println("------------");
        System.out.println("Current Graph: ");
        System.out.println(myGraph);

    }
}
