package com.mst;

import java.util.PriorityQueue;
import graph.*;

public class primsalgorithm {

    // solve graph using prims algorithm
    public void solveToMST(Graph<Vertex, Edge<Vertex>> myGraph) {

        Graph<Vertex, Edge<Vertex>> myMST = new Graph<>(false);

        // fertig -- 1. Wähle den Knoten mit der geringsten ID als Startknoten
        // fertig -- 2. Füge alle ausgehenden Kanten des Startknotens zu einer Priority
        // Queue hinzu.
        // 3. Wähle die Kante mit dem niedrigsten Gewicht aus der Priority Queue und
        // füge den damit verbundenen Knoten zum MST hinzu. Wenn der Knoten bereits im
        // MST enthalten ist, ignoriere die Kante.
        // 4. Füge alle ausgehenden Kanten des neu hinzugefügten Knotens zu der Priority
        // Queue hinzu, falls sie nicht bereits enthalten sind.
        // 5. Wiederhole Schritt 3 und 4, bis alle Knoten im MST enthalten sind.

        // 1. : Startknoten finden
        int startingPoint = findInitialVertex(myGraph, myMST);
        // print the vertex for which you want to find the minimum weight edge to
        // console
        System.out.println("----------");
        System.out.println("Startknoten: " + startingPoint);

        // durch alle Knoten loopen
        for (int j = 0; j <= myGraph.getNumberVertices(); j++) {
            // 2. & 3. : minimale Kante finden
            System.out.println("----------");
            System.out.println("minWeightEdge:");
            System.out.println(findMinWeightEdge(myGraph.getVertex(startingPoint), myGraph, myMST, myMST));

            System.out.println("myMST:");
            System.out.print(myMST);
        }
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
    public static int findInitialVertex(
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

        Vertex start = myGraph.getVertex(startingPoint);
        myMST.addVertex(start);
        System.out.println("New MST:" + myMST);
        return startingPoint;
    }

    // Methode um Kante mit niedrigstem Gewicht finden
    public static Edge<Vertex> findMinWeightEdge(
            Vertex vertex,
            Graph<Vertex, Edge<Vertex>> graph,
            Graph<Vertex, Edge<Vertex>> myGraph, Graph<Vertex, Edge<Vertex>> myMST) {
        // Erstelle eine PriorityQueue mit Initialkapazität 10 und
        // einem Comparator, der die Kanten nach Gewicht sortiert
        // TODO: Initialkapazität auf Zahl ausgehender Kanten des Knotens festlegen
        PriorityQueue<Edge<Vertex>> queue = new PriorityQueue<>(10, (a, b) -> a.getWeight() - b.getWeight());

        // Füge alle ausgehenden Kanten des Knotens zur PriorityQueue hinzu
        queue.addAll(graph.getIncidentEdges(vertex));

        // Die Kante mit dem niedrigsten Gewicht ist die erste in der Queue
        Edge<Vertex> minWeightEdge = queue.poll();

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
    public static void getTotalWeight() {
        // Alle Kantengewichte addieren
    }

}