package com.mst;

import graph.*;
import java.util.Collection;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.HashSet;

public class primsalgorithm {

    // Haupt-Methode um MST aus Graphen zu erstellen
    public Graph<Vertex, Edge<Vertex>> solveToMST(Graph<Vertex, Edge<Vertex>> myGraph) {

        // MST initialisieren
        Graph<Vertex, Edge<Vertex>> myMST = new Graph<>(false);

        // Startknoten 'initialVertex' finden & zum Graphen hinzufügen
        Vertex initialVertex = findInitialVertex(myGraph);
        myMST.addVertex(initialVertex);

        // Neues HashSet 'allEdges' initialisieren um Kanten mit minimalem Gewicht zwischenzuspeichern
        Set<Edge<Vertex>> allEdges = new HashSet<>();

        // Alle Knoten des Graphens durchlaufen
        for (Vertex v : myGraph.getVertices()) {

            // Aktuellen Knoten dem Graphen hinzufügen sofern dieser nicht bereits existiert
            if (!myMST.getVertices().contains(v)) {
                myMST.addVertex(v);
            }

            // Neue PQ initialisieren
            PriorityQueue<Edge<Vertex>> queue = new PriorityQueue<>();

            // Alle Kanten des aktuellen Graphens durchlaufen & zur PQ hinzufügen
            for (Edge<Vertex> edge : myGraph.getIncidentEdges(v)) {
                queue.add(edge);
            }
            // Kanten mit minimalstem Gewicht aus der PQ in HashSet 'allEdges' ablegen
            Edge<Vertex> edge = queue.poll();
            if (!allEdges.contains(edge)) {
                allEdges.add(edge);
            }
        }

        // Durch alle Einträge im HashSet 'allEdges' gehen und dem MST hinzufügen
        for (Edge<Vertex> edge : allEdges) {
            myMST.addEdge(edge);
        }

        // MST und Gesamtgewicht in der Konsole ausgeben
        System.out.println(myMST);
        System.out.println("Gesamtgewicht: " + getTotalWeight(myMST));

        // 'myMST' vom Typ Graph<Vertex, Edge<Vertex>> zurückgeben
        return myMST;
    }

    // Methode um Graphen aus txt-Datei zu lesen
    public Graph<Vertex, Edge<Vertex>> readGraph(String dat) {
        Graph<Vertex, Edge<Vertex>> myGraph = GraphRead.FileToGraph(
            dat,
            false,
            false,
            true
        );
        return myGraph;
    }

    // Methode um Startknoten zu finden
    public Vertex findInitialVertex(Graph<Vertex, Edge<Vertex>> myGraph) {

        boolean startFound = false;
        int i = 0;
        int startingPoint = 0;
        // Alle Knoten des Graphens durchgehen
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

    // Gesamtgewicht des MST berechnen
    public static int getTotalWeight(Graph<Vertex, Edge<Vertex>> myMST) {
        // Gesamtsumme initialisieren
        int totalWeight = 0;

        // Collection von Kanten im MST holen
        Collection<Edge<Vertex>> edges = myMST.getEdges();

        // For-Schleife über alle Kanten
        for (Edge<Vertex> edge : edges) {
            // Kantengewicht hinzufügen
            totalWeight += edge.getWeight();
        }
        return totalWeight;
    }
}