# Museums-Manager
###Projekt fuer SWE-DHBW-Karlsruhe
Autoren: Theo Roncoletta, Kai Haubrich - TINF18B1

Dieses Projekt ist nicht fertiggestellt worden. Das GUI fehlt. 
Es wurde ein kleines Testskript von Théo Roncoletta zusammengestellt mit dem Sie ein paar Funktionenen des Backends testen können.

Die Jar-Datei können sie wie in der Aufgabenstellung gegeben mit dem -p Parameter starten und den Pfad zu einem Ressourcen-Ordner angeben.
Des weitern wenn sie eigene Default-Elemente generieren wollen die verwendet werden, wenn eine unbekannte Referenz angegeben wird, dann können Sie den Pfad nach dem -d Parameter eingeben.
Für eine Log-Datei geben sie bitte einen Pfad nach dem -l Parameter an.
Alle eingelesenen Dateien müssen zum Zeitpunkt der Abgabe noch den SimpleName der importieren Klasse tragen.  

Leider habe ich in den letzten paar Tagen keinen Kontakt zu Kai mehr aufbauen können und sein Code wurde auch nicht in GitHub gepusht.
Das heißt die Anwendung hat aktuell kein GUI!
Ich (Théo Roncoletta) habe daher ein kurzes Test-Skript zusammengeworfen mit dem das von mir geschriebene Backend auf Teile seiner Funktion überprüft werden kann.
Daher werden Sie von der vorhandene Jar-Datei bei der Ausführung in der Commandline nach verschiedenen Argumenten gefragt werden.

Sie können die Jar-Wie vorgegeben starten und die oben genannte Parameter angeben.
Ihnen stehen dazu die verschiedenen CSV-Dateien im Ordner "/resources/data" zur Verfügung (für -p).
Default-Dateien befinden sich in "/resources/data/default" (für -d).
Das Programm wird erst alle gegebenen CSV-Dateien einlesen und ihnen dan ausgeben wie viele Elemente es von jedem Typ eingelsen hat.
Dann löscht es die gerade geladenen Exponate aus dem Backend, damit Sie manuell die Import-Funktion testen können. 
Wenn die Daten nicht gelöscht werden würden, würde ein Import derselben Daten fehlschlagen, da nur ein Objekt mit 
demselben PrimaryKey auf einmal existieren kann.

Die erste Aufforderung wird : "Bitte geben Sie den Pfad zu der zu importierenden Datei mit den Exponaten ein." sein.
Geben Sie daraufhin den absoluten Pfad zu der zu importierenden Datei mit den Exponaten ein. 
(Sie finden eine Beispieldatei unter "/resources/data/Exponat.csv")

Das Programm importiert nun die in der Datei enthaltenen Daten und gibt sie ihnen mit der toString-Methode in der Konsole aus.

Dann werden Sie gebeten einen Pfad zu einem Export-Ort auf ihrem Computer anzugeben ("Bitte geben Sie den Pfad zum Export-Ort ein. (Ohne Dateiname!)").
Bitte hier nur den Pfad zu dem Überordner eingeben. 
Nach der Nachricht "Bitte geben sie den Dateiname ein." geben sie den Dateinamen ein.

Wenn eine Datei mit gleichem Namen bereits existiert bricht das Programm ab 
(das Überschreiben ist ausgestellt) damit Sie nicht aus Versehen wichtige Dateien überschreiben können.

Nun wird das Programm die vorhin importieren Exponate zu der von Ihnen gegebenen Datei exportieren.
Daraufhin liest es sie wieder und vergleicht die gelesenen Daten mit den vorhin importierten Daten und gibt ihnen das Ergebnis aus.

Leider konnte ich in der kurzen Zeit die mir zur Verfügung stand nichts Besseres zusammenbasteln.
