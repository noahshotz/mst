# mst

## Aufgabe 5.6 (*, Programmierpflichtaufgabe Minimale Spannbäume, 18 Punkte)

Implementieren Sie Prims-Algorithmus zur Bestimmung von minimalen Spannbäumen. Ihr Algorithmus soll die Kanten des minimalen Spannbaums ermitteln. Wählen Sie als Startknoten immer den Knoten mit der kleinsten id, d.h. für bsp_mst_1 und bsp_mst_3 den Knoten mit der id 0 und für bsp_mst_2 den Knoten mit id 5. Schreiben Sie weiterhin eine Methode, die das Gesamtgewicht des minimalen Spannbaums ermittelt.

Als Datenstruktur für die Graphen müssen Sie die Klassen Graph, Vertex, Edge und GraphRead verwenden. Sie finden diese Klassen unter GraphBib.zip sowie deren Dokumentation unter GraphBibDoc.zip. Dabei sollten diese Klassen nicht verändert werden! Falls Sie bspw. in der Klasse Vertex zusätzliche Attribute oder Methoden ergänzen, gibt es einen Punkt Abzug. Für die Priority-Queue dürfen Sie die entsprechende Java-Klasse aus der Java-API verwenden. Zum Testen des Algorithmus werden einige Beispiele vorgegeben. Sie finden die Beispiele unter BspMST.zip. Selbstverständlich können Sie weitere Beispiele zum Testen selber erstellen. Das Format der Testdateien entnehmen Sie der Klasse GraphRead. Zum Aufruf der Methode zum Lesen
der Graphen sind folgende Parameter zu verwenden: GraphRead.FileToGraph("datei_name.txt", false, false, true).

Hinweis: Der minimale Spannbaum aus Datei bsp_mst_1.txt hat das Gesamtgewicht 37, der minimale Spannbaum aus Datei bsp_mst_2.txt das Gesamtgewicht 9 und der minimale Spannbaum aus Datei bsp_mst_3.txt das Gesamtgewicht 17.

Für die Abgabe ist das Programm (ausführlich kommentiert) inklusive dem Testprogramm sowie den Ergebnissen der Tests für alle Beispiele in BspMST.zip hochzuladen. Bitte laden Sie die Programme nur als Java-Dateien hoch.

## About minimum spanning trees

A minimum spanning tree (MST) is a weighted, undirected, connected graph whose total edge weight has been minimized by removing heavier edges. In other words, we keep all the vertices of the graph intact, but we may remove some edges so that the sum of all edges is at a minimum.

We start with a weighted graph since it makes no sense to minimize the total edge weight if those edges have no weight at all. Let's take a look at an example graph:

![image](https://user-images.githubusercontent.com/28689532/210186123-bb11b3f5-743a-4a44-aadd-cb68a15cbdbe.png)

The above graph is a weighted, undirected, connected graph. Here is an MST of that graph:

![image](https://user-images.githubusercontent.com/28689532/210186129-389beec3-b8e2-469a-af91-c8ce2835e214.png)

An MST of a graph is not unique, though. If a graph has more than one MST, then each MST has the same total edge weight.

## About Prim's Algorithm

Prim's algorithm takes a weighted, undirected, connected graph as input and returns an MST of that graph as output.

It works in a greedy manner. In the first step, it selects an arbitrary vertex. Thereafter, each new step adds the nearest vertex to the tree constructed so far until there is no disconnected vertex left.

Let's run Prim's algorithm on this graph step-by-step:

![image](https://user-images.githubusercontent.com/28689532/210186153-dbfb07ba-f95c-4f36-b731-0f95e688871a.png)

Assuming the arbitrary vertex to start the algorithm is B, we have three choices A, C, and E to go. The corresponding weights of the edges are 2, 2, and 5, therefore the minimum is 2. In this case, we have two edges weighing 2, so we can choose either of them (it doesn't matter which one). Let's choose A:

![image](https://user-images.githubusercontent.com/28689532/210186157-82f61c36-2136-4676-ab08-75e11a86f2e8.png)

Now we have a tree with two vertices A and B. We can select any of A or B's edges not yet added that lead to an unadded vertex. So, we can pick AC, BC, or BE. Prim's algorithm chooses the minimum, which is 2, or BC:

![image](https://user-images.githubusercontent.com/28689532/210186170-f8fe6df5-264a-482d-93c5-c6197c0639d2.png)

Now we have a tree with three vertices and three possible edges to move forward: CD, CE, or BE. AC isn't included since it wouldn't add a new vertex to the tree. The minimum weight amongst these three is 1.

However, there are two edges both weighing 1. Consequently, Prim's algorithm chooses one of them (again doesn't matter which one) in this step:

![image](https://user-images.githubusercontent.com/28689532/210186175-32c8577f-a21e-4cd5-8794-6a2246184eca.png)

There is only one vertex left to join, so we can pick from CE and BE. The minimum weight that can connect our tree to it is 1, and Prim's algorithm will choose it:

![image](https://user-images.githubusercontent.com/28689532/210186179-c2ab5f2a-0cdf-473d-b603-1cfbe55ed6c2.png)

As all vertices of the input graph are now present in the output tree, Prim's algorithm ends. Therefore, this tree is an MST of the input graph.

