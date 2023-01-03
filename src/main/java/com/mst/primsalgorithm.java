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
        // the vertex for which you want to find the minimum weight edge
        System.out.println(startingPoint);

        // für Startknoten alle Nachbarn finden
        // für alle Edges zu den Nachbarn die Edge mit den
        // niedrigsten Kosten finden
        System.out.println("----------");
        System.out.println("Alle Nachbarn:");
        for (Vertex neighbor : myGraph.getNeighbours(startingPoint)) {
            System.out.println(neighbor);
        }

        System.out.println("----------");
        System.out.println("minWeightEdge:");
        System.out.println(findMinWeightEdge(myGraph.getVertex(startingPoint), myGraph));
        
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

    public static void getTotalWeight() {

    }

}