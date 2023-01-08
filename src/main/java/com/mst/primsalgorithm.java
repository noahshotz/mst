package com.mst;

import java.util.Collection;
import java.util.List;
import java.util.PriorityQueue;
import graph.*;

public class primsalgorithm {

    // solve graph using prims algorithm
    public void solveToMST(Graph<Vertex, Edge<Vertex>> myGraph) {

        // Alle Knoten in einer Collection speichern
        Collection<Vertex> allVertices = myGraph.getVertices();
        // Casten der Collection zu einer List
        List<Vertex> vertexList = (List<Vertex>) allVertices;

        // MST initialisieren
        Graph<Vertex, Edge<Vertex>> myMST = new Graph<>(false);

        // Startknoten finden
        Vertex startingPoint = findInitialVertex(myGraph, myMST);
        findMinWeightEdge(startingPoint, myGraph, myMST);

        for (Vertex vertex : vertexList) {
            if (!vertexInGraph(myMST, vertex)) {
                System.out.println(vertex);
                myMST.addVertex(vertex);
                myMST.addEdge(findMinWeightEdge(vertex, myGraph, myMST));
            }
        }

        System.out.println("---------");
        System.out.println("myMST:");
        System.out.print(myMST);

        System.out.println("---------");
        System.out.println("Gesamtkantengewicht:");
        System.out.println(getTotalWeight(myMST));
    }

    public boolean vertexInGraph(
            Graph<Vertex, Edge<Vertex>> myGraph,
            Vertex vertex
    ) {
        Collection<Vertex> vertices = myGraph.getVertices();
        for (Vertex v : vertices) {
            if (v.equals(vertex)) {
                return true;
            }
        }
        return false;
    }

    // read graph from txt-file
    public Graph<Vertex, Edge<Vertex>> readGraph(String dat) {
        Graph<Vertex, Edge<Vertex>> myGraph = GraphRead.FileToGraph(
                dat,
                false,
                false,
                true);
        System.out.println(myGraph);
        return myGraph;
    }

    // Methode um Startknoten zu finden
    public static Vertex findInitialVertex(
            Graph<Vertex, Edge<Vertex>> myGraph,
            Graph<Vertex, Edge<Vertex>> myMST) {
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
        // Reset i
        i = 0;

        Vertex start = myGraph.getVertex(startingPoint);
        myMST.addVertex(start);
        System.out.println("New MST:" + myMST);
        return start;
    }

    // Methode um Kante mit niedrigstem Gewicht finden
    public static Edge<Vertex> findMinWeightEdge(
            Vertex vertex,
            Graph<Vertex, Edge<Vertex>> myGraph,
            Graph<Vertex, Edge<Vertex>> myMST) {
        // Erstelle eine PriorityQueue mit der Anzahl der ausgehenden Kanten des Knotens
        // als Initialkapazität und einem Comparator, der die Kanten nach Gewicht
        // sortiert
        PriorityQueue<Edge<Vertex>> queue = new PriorityQueue<>(
                myGraph.getIncidentEdges(vertex).size(),
                (a, b) -> a.getWeight() - b.getWeight());

        // Füge alle ausgehenden Kanten des Knotens zur PriorityQueue hinzu
        queue.addAll(myGraph.getIncidentEdges(vertex));

        // Die Kante mit dem niedrigsten Gewicht ist die erste in der Queue
        Edge<Vertex> minWeightEdge = queue.poll();

        System.out.println(minWeightEdge);

        // Endknoten der Kante dem MST hinzufügen, falls noch nicht enthalten
        addNextVertex(myMST, minWeightEdge);

        return minWeightEdge;
    }

    public static void addNextVertex(
            Graph<Vertex, Edge<Vertex>> myMST,
            Edge<Vertex> minWeightEdge) {
        // minWeight-Kante und entsprechenden Endknoten hinzufügen
        Vertex newVertex = minWeightEdge.getVertexB();
        myMST.addVertex(newVertex);
    }

    // get total weight of new graph
    public static int getTotalWeight(Graph<Vertex, Edge<Vertex>> myMST) {
        // Alle Kantengewichte addieren
        // Gesamtsumme initialisieren
        int totalWeight = 0;

        // Collection von Kanten im MST-Graphen holen
        Collection<Edge<Vertex>> edges = myMST.getEdges();

        // For-Schleife über alle Kanten
        for (Edge<Vertex> edge : edges) {
            // Kantengewicht hinzufügen
            totalWeight += edge.getWeight();
        }
        return totalWeight;
    }
}