# mst

## Aufgabe 5.6 (*, Programmierpflichtaufgabe Minimale Spannbäume, 18 Punkte)

Implementieren Sie Prims-Algorithmus zur Bestimmung von minimalen Spannbäumen. Ihr Algorithmus soll die Kanten des minimalen Spannbaums ermitteln. Wählen Sie als Startknoten immer den Knoten mit der kleinsten id, d.h. für bsp_mst_1 und bsp_mst_3 den Knoten mit der id 0 und für bsp_mst_2 den Knoten mit id 5. Schreiben Sie weiterhin eine Methode, die das Gesamtgewicht des minimalen Spannbaums ermittelt.

Als Datenstruktur für die Graphen müssen Sie die Klassen Graph, Vertex, Edge und GraphRead verwenden. Sie finden diese Klassen unter GraphBib.zip sowie deren Dokumentation unter GraphBibDoc.zip. Dabei sollten diese Klassen nicht verändert werden! Falls Sie bspw. in der Klasse Vertex zusätzliche Attribute oder Methoden ergänzen, gibt es einen Punkt Abzug. Für die Priority-Queue dürfen Sie die entsprechende Java-Klasse aus der Java-API verwenden. Zum Testen des Algorithmus werden einige Beispiele vorgegeben. Sie finden die Beispiele unter BspMST.zip. Selbstverständlich können Sie weitere Beispiele zum Testen selber erstellen. Das Format der Testdateien entnehmen Sie der Klasse GraphRead. Zum Aufruf der Methode zum Lesen
der Graphen sind folgende Parameter zu verwenden: GraphRead.FileToGraph("datei_name.txt", false, false, true).

Hinweis: Der minimale Spannbaum aus Datei bsp_mst_1.txt hat das Gesamtgewicht 37, der minimale Spannbaum aus Datei bsp_mst_2.txt das Gesamtgewicht 9 und der minimale Spannbaum aus Datei bsp_mst_3.txt das Gesamtgewicht 17.

Für die Abgabe ist das Programm (ausführlich kommentiert) inklusive dem Testprogramm sowie den Ergebnissen der Tests für alle Beispiele in BspMST.zip hochzuladen. Bitte laden Sie die Programme nur als Java-Dateien hoch.
