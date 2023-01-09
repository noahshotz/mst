package com.mst;
import graph.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {
    @Test
    // Soll testen, ob der korrekte Startknoten ausgewählt wird
    void testFindInitialVertex01(){
        // Start-ID Beispiel 1
        int expected = 0;

         // Neuen Graphen aus txt-Datei erstellen
         primsalgorithm newGraph = new primsalgorithm();
         Graph<Vertex, Edge<Vertex>> myGraph = newGraph.readGraph("src/main/java/com/mst/BspMST/bsp_mst_1.txt");

         // ID des Startknoten aus Graphen finden
         int actual = newGraph.findInitialVertex(myGraph).getId();

         assertEquals(expected, actual);
    }

    @Test
    // Soll testen, ob das korrekte Gesamtgewicht des MST ermittelt wird
    void testTotalWeight01(){
        // Gesamtgewicht für Beispiel 1
        int expected = 37;

         // Neuen Graphen aus txt-Datei erstellen
         primsalgorithm newGraph = new primsalgorithm();
         Graph<Vertex, Edge<Vertex>> myGraph = newGraph.readGraph("src/main/java/com/mst/BspMST/bsp_mst_1.txt");

         // MST aus Graphen erstellen
         Graph<Vertex, Edge<Vertex>> myMST = newGraph.solveToMST(myGraph);
         int actual = newGraph.getTotalWeight(myMST);

         assertEquals(expected, actual);
    }

    @Test
    // Soll testen, ob der korrekte Startknoten ausgewählt wird
    void testFindInitialVertex02(){
        // Start-ID Beispiel 2
        int expected = 5;

         // Neuen Graphen aus txt-Datei erstellen
         primsalgorithm newGraph = new primsalgorithm();
         Graph<Vertex, Edge<Vertex>> myGraph = newGraph.readGraph("src/main/java/com/mst/BspMST/bsp_mst_2.txt");

         // ID des Startknoten aus Graphen finden
         int actual = newGraph.findInitialVertex(myGraph).getId();

         assertEquals(expected, actual);
    }

    @Test
    // Soll testen, ob das korrekte Gesamtgewicht des MST ermittelt wird
    void testTotalWeight02(){
        // Gesamtgewicht für Beispiel 2
        int expected = 9;

         // Neuen Graphen aus txt-Datei erstellen
         primsalgorithm newGraph = new primsalgorithm();
         Graph<Vertex, Edge<Vertex>> myGraph = newGraph.readGraph("src/main/java/com/mst/BspMST/bsp_mst_2.txt");

         // MST aus Graphen erstellen
         Graph<Vertex, Edge<Vertex>> myMST = newGraph.solveToMST(myGraph);
         int actual = newGraph.getTotalWeight(myMST);

         assertEquals(expected, actual);
    }

    @Test
    // Soll testen, ob der korrekte Startknoten ausgewählt wird
    void testFindInitialVertex03(){
        // Start-ID Beispiel 3
        int expected = 0;

         // Neuen Graphen aus txt-Datei erstellen
         primsalgorithm newGraph = new primsalgorithm();
         Graph<Vertex, Edge<Vertex>> myGraph = newGraph.readGraph("src/main/java/com/mst/BspMST/bsp_mst_3.txt");

         // ID des Startknoten aus Graphen finden
         int actual = newGraph.findInitialVertex(myGraph).getId();

         assertEquals(expected, actual);
    }

    @Test
    void testTotalWeight03(){
        // Gesamtgewicht für Beispiel 3
        int expected = 9;

         // Neuen Graphen aus txt-Datei erstellen
         primsalgorithm newGraph = new primsalgorithm();
         Graph<Vertex, Edge<Vertex>> myGraph = newGraph.readGraph("src/main/java/com/mst/BspMST/bsp_mst_3.txt");

         // MST aus Graphen erstellen
         Graph<Vertex, Edge<Vertex>> myMST = newGraph.solveToMST(myGraph);
         int actual = newGraph.getTotalWeight(myMST);

         assertEquals(expected, actual);
    }
}
