package com.mst;

import java.util.PriorityQueue;
import graph.*;

public class primsalgorithm {

    // solve graph using prims algorithm
    public void solve(Graph<Vertex, Edge<Vertex>> myGraph) {

        // 1. Wähle den Knoten mit der geringsten ID als Startknoten
        // 2. Füge alle ausgehenden Kanten des Startknotens zu einer Priority Queue hinzu.
        // 3. Wähle die Kante mit dem niedrigsten Gewicht aus der Priority Queue und füge den damit verbundenen Knoten zum MST hinzu. Wenn der Knoten bereits im MST enthalten ist, ignoriere die Kante.
        // 4. Füge alle ausgehenden Kanten des neu hinzugefügten Knotens zu der Priority Queue hinzu, falls sie nicht bereits enthalten sind.
        // 5. Wiederhole Schritt 3 und 4, bis alle Knoten im MST enthalten sind.

        // 1. : Startknoten finden
        int startingPoint = findInitialVertex(myGraph);
        // print the vertex for which you want to find the minimum weight edge to console
        System.out.println("----------");
        System.out.println("Startknoten: " + startingPoint);

        // 2. & 3. : minimale Kante finden
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

    // read graph from txt-file
    public Graph<Vertex, Edge<Vertex>> readGraph(String dat) {

        Graph<Vertex, Edge<Vertex>> myGraph = GraphRead.FileToGraph(
            "src/main/java/com/mst/BspMST/bsp_mst_1.txt", 
            false, 
            false,
            true
        );

        System.out.println(myGraph);

        return myGraph;
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

        Graph<Vertex, Edge<Vertex>> myMST = new Graph<>(false);
        Vertex start = myGraph.getVertex(i);
        myMST.addVertex(start);
        System.out.println("New MST:" + myMST);

        return startingPoint;
    }

    // Methode um Kante mit niedrigstem Gewicht finden
    public static Edge<Vertex> findMinWeightEdge(Vertex vertex, Graph<Vertex, Edge<Vertex>> graph) {
        // Erstelle eine PriorityQueue mit Initialkapazität 10 und
        // einem Comparator, der die Kanten nach Gewicht sortiert
        // TODO: Initialkapazität auf Zahl ausgehender Kanten des Knotens festlegen
        PriorityQueue<Edge<Vertex>> queue = new PriorityQueue<>(10, (a, b) -> a.getWeight() - b.getWeight());
    
        // Füge alle ausgehenden Kanten des Knotens zur PriorityQueue hinzu
        queue.addAll(graph.getIncidentEdges(vertex));
        
        // Die Kante mit dem niedrigsten Gewicht ist die erste in der Queue
        Edge<Vertex> minWeightEdge = queue.poll();;
        return minWeightEdge;
    }

    // get total weight of new graph
    public static void getTotalWeight() {
        // Alle Kantengewichte addieren
    }

}