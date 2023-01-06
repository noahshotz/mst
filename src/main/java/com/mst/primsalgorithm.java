package com.mst;

import graph.*;

public class primsalgorithm {

    // read graph from txt-file
    public static void readGraph() {
    }

    // create sample graph to solve
    public Graph<Vertex, Edge<Vertex>> createGraph() {

        // new graph object
        Graph<Vertex, Edge<Vertex>> myGraph = new Graph<>(false);

        // create new vertex objects with ID and name
        Vertex a = new Vertex(3, "A");
        Vertex b = new Vertex(1, "B");
        Vertex c = new Vertex(2, "C");

        // add vertices to graph
        myGraph.addVertex(a);
        myGraph.addVertex(b);
        myGraph.addVertex(c);

        // create new edge object between vertices with specific weights
        Edge<Vertex> ab = new Edge<>(a, b, 10);
        Edge<Vertex> ac = new Edge<>(a, c, 20);
        Edge<Vertex> bc = new Edge<>(b, c, 30);

        // add edges to graph
        myGraph.addEdge(ab);
        myGraph.addEdge(ac);
        myGraph.addEdge(bc);

        return myGraph;
    }

    // solve graph using prims algorithm
    public void solve(Graph<Vertex, Edge<Vertex>> myGraph) {

        // print initial graph to console
        System.out.println("----------");
        System.out.println("Current graph: ");
        System.out.println(myGraph);

        // print all vertices with ID and name
        System.out.println("----------");
        System.out.println("Alle Knoten: ");
        System.out.println(myGraph.getVertices());

        // Startknoten finden
        int startingPoint = findInitialVertex(myGraph);

        // print the vertex for which you want to find the minimum weight edge to console
        System.out.println("----------");
        System.out.println("Startknoten: ");
        System.out.println(startingPoint);

        // loop through graph until all vertices are indexed
        for (int j = 0; j < myGraph.getNumberVertices(); j++) {

        }

        // für Startknoten alle Nachbarn finden
        // für alle Edges zu den Nachbarn die Edge mit den
        // niedrigsten Kosten finden
        System.out.println("----------");
        System.out.println("Alle Nachbarn:");
        for (Vertex neighbor : myGraph.getNeighbours(startingPoint)) {
            System.out.println(neighbor);
        }

        // minimale Kante finden
        System.out.println("----------");
        System.out.println("minWeightEdge:");
        System.out.println(findMinWeightEdge(myGraph.getVertex(startingPoint), myGraph));

        // Knoten vom vorherigen Knoten mit minimaler Kante
        System.out.println("----------");
        System.out.println("nächster Knoten:");
        System.out.println(findMinWeightEdge(myGraph.getVertex(startingPoint), myGraph).getVertexA());

        // für Startknoten alle Nachbarn finden
        // für alle Edges zu den Nachbarn die Edge mit den
        // niedrigsten Kosten finden
        System.out.println("----------");
        System.out.println("Alle neuen Nachbarn:");
        for (Vertex neighbor : myGraph.getNeighbours(findMinWeightEdge(myGraph.getVertex(startingPoint), myGraph).getVertexA())) {
            System.out.println(neighbor);
        }
        
    }

    // Methode um Startknoten zu finden
    public static int findInitialVertex(Graph<Vertex, Edge<Vertex>> myGraph) {

        // find initial vertex to start from
        // initial vertex is defined by lowest ID
        boolean startFound = false;
        int i = 0;
        // the vertex for which you want to find the minimum weight edge
        int startingPoint = 0;
        while (!startFound) {
            Vertex start = myGraph.getVertex(i);
            if (start != null) {
                startingPoint = i;
                startFound = true;
            }
            i++;
        }
        return startingPoint;
    }

    // Methode um Kante mit niedrigstem Gewicht finden
    public Edge<Vertex> findMinWeightEdge(Vertex vertex, Graph<Vertex, Edge<Vertex>> graph) {
        int minWeight = Integer.MAX_VALUE;
        Edge<Vertex> minWeightEdge = null;
    
        for (Edge<Vertex> edge : graph.getIncidentEdges(vertex)) {
            if (edge.getWeight() < minWeight) {
                minWeight = edge.getWeight();
                minWeightEdge = edge;
            }
        }
        return minWeightEdge;
    }

    // get total weight of new graph
    public static void getTotalWeight() {

    }

}