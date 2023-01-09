package com.mst;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;
import graph.*;

public class primsalgorithm {

    // solve graph using prims algorithm
    public void solveToMST(Graph<Vertex, Edge<Vertex>> myGraph) {

        // MST initialisieren
        Graph<Vertex, Edge<Vertex>> myMST = new Graph<>(false);

        Vertex initialVertex = findInitialVertex(myGraph, myMST);
        Edge<Vertex> nextEdge = findMinWeightEdge(initialVertex, myGraph, myMST);
        myMST.addVertex(initialVertex);
        myMST.addVertex(nextEdge.getVertexB());
        myMST.addEdge(nextEdge);

        // Alle Knoten in einer Collection speichern & in Liste casten
        Collection<Vertex> allVertices = myGraph.getVertices();
        List<Vertex> vertexList = (List<Vertex>) allVertices;

        List<Vertex> remainingVertices = new ArrayList<>();
        for (Vertex vertex : vertexList) {
            if (!vertexInGraph(myMST, vertex)) {
                remainingVertices.add(vertex);
            }
        }

        // Für jeden Knoten, der noch nicht im MST enthalten ist, suche nach der Kante
        // mit dem niedrigsten Gewicht
        // und füge sie dem MST hinzu
        for (Vertex vertex : remainingVertices) {
            Edge<Vertex> minWeightEdge = findMinWeightEdge(vertex, myGraph, myMST);
            if (minWeightEdge == null) {
                // Es gibt keine Kante, die von diesem Knoten ausgeht und deren Zielknoten noch
                // nicht im MST enthalten ist
                continue;
            }
            myMST.addVertex(minWeightEdge.getVertexB());
            if (!edgeInGraph(myMST, minWeightEdge)) {
                myMST.addEdge(minWeightEdge);
            }
        }

        System.out.println("---------");
        System.out.println("myMST:");
        System.out.print(myMST);

        System.out.println("---------");
        System.out.println("myMST alle Kanten:");
        System.out.println(myMST.getEdges());

        System.out.println("---------");
        System.out.println("Gesamtkantengewicht:");
        System.out.println(getTotalWeight(myMST));
    }

    // read graph from txt-file
    public Graph<Vertex, Edge<Vertex>> readGraph(String dat) {
        Graph<Vertex, Edge<Vertex>> myGraph = GraphRead.FileToGraph(dat, false, false, true);
        System.out.println(myGraph);
        return myGraph;
    }

    // Methode um Startknoten zu finden
    public Vertex findInitialVertex(Graph<Vertex, Edge<Vertex>> myGraph, Graph<Vertex, Edge<Vertex>> myMST) {
        // find initial vertex to start from by lowest ID
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
        i = 0; // Reset i

        Vertex start = myGraph.getVertex(startingPoint);
        myMST.addVertex(start);
        return start;
    }

    // check if vertex in graph
    public boolean vertexInGraph(Graph<Vertex, Edge<Vertex>> myGraph, Vertex vertex) {
        Collection<Vertex> vertices = myGraph.getVertices();
        for (Vertex v : vertices) {
            if (v.equals(vertex)) {
                return true;
            }
        }
        return false;
    }

    // check if edge in graph
    public boolean edgeInGraph(Graph<Vertex, Edge<Vertex>> myGraph, Edge<Vertex> edge) {
        Collection<Edge<Vertex>> edges = myGraph.getEdges();
        for (Edge<Vertex> e : edges) {
            if (e.equals(edge)) {
                return true;
            }
        }
        return false;
    }

    // Methode um Kante mit niedrigstem Gewicht finden
    public Edge<Vertex> findMinWeightEdge(Vertex vertex, Graph<Vertex, Edge<Vertex>> myGraph,
            Graph<Vertex, Edge<Vertex>> myMST) {

        // Kanten temporär speichern
        List<Edge<Vertex>> edges = (List<Edge<Vertex>>) myGraph.getIncidentEdges(vertex);
        // PQ mit Initialkapazität = Kantenzahl
        PriorityQueue<Edge<Vertex>> queue = new PriorityQueue<>(
                edges.size(),
                (a, b) -> a.getWeight() - b.getWeight());

        // Alle Kanten hinzufügen, deren Zielknoten noch nicht im MST enthalten sind
        for (Edge<Vertex> edge : edges) {
            if (!vertexInGraph(myMST, edge.getVertexB())) {
                queue.add(edge);
            }
        }

        Edge<Vertex> minWeightEdge = null;
        while (!queue.isEmpty()) {
            minWeightEdge = queue.poll();
            break;
        }
        return minWeightEdge;
    }

    // get total weight of new graph
    public static int getTotalWeight(Graph<Vertex, Edge<Vertex>> myMST) {
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