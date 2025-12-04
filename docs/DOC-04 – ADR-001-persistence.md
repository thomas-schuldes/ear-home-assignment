# DOC-01 – Entscheidung Persistenztechnologie

## Kontext
Das Backend muss Mengenmeldungen speichern und abrufen.  
Für die Aufgabe genügt eine leichtgewichtige, schnelle Lösung ohne externe Infrastruktur.

## Optionen
- H2 (In-Memory oder File-basiert)
- Postgres
- SQLite

## Entscheidung
→ Für den Prototyp wird **H2** verwendet.

## Begründung
- Keine Installation nötig
- Sehr schnelle Startzeiten
- Ideal für automatisierte Tests
- JPA-basierter Wechsel zu Postgres später trivial

## Konsequenzen
- Nicht für Produktion gedacht
- Bei Bedarf kann Postgres per Spring-Konfiguration aktiviert werden
