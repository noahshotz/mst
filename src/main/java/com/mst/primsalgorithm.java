package com.mst;

import graph.*;

public class primsalgorithm {

    public void solve() {

        // graphen nehmen
        Graph<Vertex, Edge<Vertex>> myGraph = new Graph<>(false);
        Vertex a = new Vertex(3, "A");
        Vertex b = new Vertex(1, "B");
        Vertex c = new Vertex(2, "C");

        myGraph.addVertex(a);
        myGraph.addVertex(b);
        myGraph.addVertex(c);

        Edge<Vertex> ab = new Edge<>(a, b, 10);
        Edge<Vertex> ac = new Edge<>(a, c, 20);
        Edge<Vertex> bc = new Edge<>(b, c, 30);

        myGraph.addEdge(ab);
        myGraph.addEdge(ac);
        myGraph.addEdge(bc);

        System.out.println("----------");
        System.out.println("Current graph: ");
        System.out.println(myGraph);

        // startknoten finden
        System.out.println("----------");
        System.out.println("Alle Knoten: ");
        System.out.println(myGraph.getVertices());

        System.out.println("----------");
        System.out.println("Startknoten: ");

        // startknoten: knoten mit der kleinsten id finden
        boolean startFound = false;
        int i = 0;
        int startingPoint = 0;
        while (!startFound) {
            Vertex start = myGraph.getVertex(i);
            if (start != null) {
                startingPoint = i;
                startFound = true;
            }
            i++;
        }
        System.out.println(startingPoint);

        // für Startknoten alle Nachbarn finden
        // für alle Edges zu den Nachbarn die Edge mit den
        // niedrigsten Kosten finden
        System.out.println("----------");
        
        // the vertex for which you want to find the minimum weight edge
        // Vertex currentVertex = 
        // initialize the minimum weight to the maximum possible value
        int minWeight = Integer.MAX_VALUE;
        // initialize the minimum weight edge to null
        Edge<Vertex> minWeightEdge = null;  

        System.out.println("Alle Nachbarn:");
        for (Vertex neighbor : myGraph.getNeighbours(startingPoint)) {
            System.out.println(neighbor);
            System.out.println("Incident edges:");
            System.out.println(myGraph.getIncidentEdges(startingPoint));
        }

        for (Edge<Vertex> neighborE : myGraph.getIncidentEdges(startingPoint)) {
            System.out.println(
                "For edge from " +
                neighborE.getVertexA() + " to " +
                neighborE.getVertexB() + " get weight: ");
            System.out.println(neighborE.getWeight());

            if (neighborE.getWeight() < minWeight) {
                minWeight = neighborE.getWeight();
                minWeightEdge = neighborE;
            }

            System.out.println("Final lowest weight is: ");
            System.out.println(minWeight);
            System.out.println("Respective edge is: ");
            System.out.println(minWeightEdge);

        }
        
    }

    public static void getTotalWeight() {

    }

}