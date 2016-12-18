### TicTacToe in Java

Es soll, im Rahmen der INF2 Laborübung, ein klassisches Tic Tac Toe Spiel in Java unter Nutzung des Frameworks "Spring" erstellt werden. Die Anwendung soll unter Windows im Fenstermodus laufen und per Maus gesteuert werden.

###Was ist Tic Tac Toe?
Es ist ein klassisches 2-Spieler Spiel welches auf einem 3x3 Felder in quadratischen Spielfeld gespielt wird. 2 Spieler setzen abwechselnd ihr vorher gewähltes Zeichen (X oder O) in eines der leeren Felder. Ziel ist es 3 eigene Symbole in einer Reihe zu setzen (vertikal, horizontal oder diagonal), dieses führt zum Gewinn der Partie. Sind alle 9 Felder belegt ohne dass sich 3 Symbole in Reihe befinden endet die Partie unentschieden.

###Der Programmablauf
Die Anwendung zeigt den Startbildschirm. Der Benutzer wählt zwischen 2-Spieler Modus und Spieler gegen den Computer (optional: wählt dann auch einen Schwierigkeitsgrad). Der oder die Spieler wählen ein Symbol (X oder O) und es wird ausgewählt wer mit der Partie beginnt.

Das Spielfeld wird vom Programm gezeichnet und das Spiel beginnt. Im 2-Spieler Modus beginnt der zuvor gewählte Spieler und setzt sein Symbol durch Mausklick auf eines der leeren Felder, hiermit ist der Spielzug beendet und der zweite Spieler führt seinen Spielzug auf gleiche Weise durch. 

Im 1-Spieler-Modus beginnt, je nach vorheriger Wahl, entweder der Spieler oder der Computer. Der Spielerzug funktioniert wie im 2 Spieler Modus, der Spielzug des Computers wird automatisch ausgeführt.

Das Spiel endet, wenn ein Spieler (menschlicher oder virtueller) 3 eigene Symbole in einer Reihe gesetzt hat (dieser Spieler gewinnt die Partie), oder wenn alle 9 Felder belegt wurden und somit kein Spieler 3 Symbole in Reihe legen kann (die Partie endet mit einem Unentschieden).

Es wird eine Meldung über den Gewinn eingeblendet, die Spielrunde endet und es folgt wieder der Startbildschirm. 

###Anmerkung
Der Aspekt, dass eine Partie Tic Tac Toe nicht gewonnen werden kann wenn der Gegenspieler optimal spielt, im Besten Fall ein Unentschieden entstehen kann sollten beide Spieler optimal spielen, erfordert eine besondere Betrachtung bei der Entwicklung der "künstlichen Intelligenz" des virtuellen Gegenspielers. Die Wahl eines Schwierigkeitsgrades bei der virtuellen KI ist ein optional geplantes Feature, hier muss zunächst die Notwendigkeit untersucht werden.

