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

        // Startknoten finden & dem MST hinzufügen
        Vertex initialVertex = findInitialVertex(myGraph, myMST);
        myMST.addVertex(initialVertex);

        // vom Startknoten die erste minWeightEdge
        Edge<Vertex> nextEdge = findMinWeightEdge(initialVertex, myGraph, myMST);
        myMST.addVertex(nextEdge.getVertexB());
        myMST.addEdge(nextEdge);

        // Alle Knoten in einer Collection speichern & in Liste casten
        Collection<Vertex> allVertices = myGraph.getVertices();
        List<Vertex> vertexList = (List<Vertex>) allVertices;

        // Liste mit verbleibenden Knoten erstellen
        List<Vertex> remainingVertices = new ArrayList<>();
        for (Vertex vertex : vertexList) {
            if (!vertexInGraph(myMST, vertex)) {
                remainingVertices.add(vertex);
            }
        }

        // Für jeden Knoten, der noch nicht im MST enthalten ist, suche nach der Kante
        // mit dem niedrigsten Gewicht und füge sie dem MST hinzu
        while (!remainingVertices.isEmpty()) {
            // Vertex mit der niedrigsten ID finden, der noch nicht im MST enthalten ist
            Vertex minVertex = null;
            for (Vertex vertex : remainingVertices) {
                if (minVertex == null || vertex.getId() < minVertex.getId()) {
                    minVertex = vertex;
                    myMST.addVertex(minVertex);
                }
            }

            // Kante mit dem niedrigsten Gewicht von minVertex finden, deren Zielknoten noch
            // nicht im MST enthalten ist
            Edge<Vertex> minWeightEdge = findMinWeightEdge(minVertex, myGraph, myMST);
            if (!vertexInGraph(myMST, minWeightEdge.getVertexB())) {
                myMST.addVertex(minWeightEdge.getVertexB());
                
            }
            myMST.addEdge(minWeightEdge);
            
            // Vertex aus der Liste der verbleibenden Knoten entfernen
            remainingVertices.remove(minVertex);
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

    public Edge<Vertex> findMinWeightEdge(
        Vertex vertex,
        Graph<Vertex, Edge<Vertex>> myGraph,
        Graph<Vertex, Edge<Vertex>> myMST
    ) {

        // Prioritätswarteschlange, um Kanten nach ihrem Gewicht zu sortieren
        PriorityQueue<Edge<Vertex>> queue = new PriorityQueue<>();

        // Füge alle Kanten hinzu, die von dem übergebenen Knoten ausgehen
        for (Edge<Vertex> edge : myGraph.getIncidentEdges(vertex)) {
            queue.add(edge);
        }

        // Wähle die Kante mit dem niedrigsten Gewicht aus der Warteschlange
        Edge<Vertex> minWeightEdge = queue.poll();

        while (minWeightEdge != null) {
            // Überprüfe, ob der Zielknoten der Kante schon im MST enthalten ist
            if (!vertexInGraph(myMST, minWeightEdge.getVertexB())) {
                // Wenn der Zielknoten noch nicht im MST enthalten ist, gib die Kante zurück
                return minWeightEdge;
            }
            // Wenn der Zielknoten schon im MST enthalten ist, entferne die Kante aus der
            // Warteschlange und betrachte die nächste
            minWeightEdge = queue.poll();
            return minWeightEdge;
        }

        // Keine Kante gefunden, die von dem übergebenen Knoten ausgeht und deren
        // Zielknoten noch nicht im MST enthalten ist
        return null;
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