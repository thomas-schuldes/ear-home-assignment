# DOC-03 – Architektur-Dokument.md

# EAR – Architekturbeschreibung

Diese Architekturbeschreibung erfüllt die Anforderungen der EAR-Entwicklungsaufgabe und umfasst eine **statische** und eine **dynamische Sicht** auf das System.

---

# 1. Statische Architektur

Das System besteht aus vier zentralen Komponenten:

* **Frontend (Web UI)**
  UI zur Erfassung und Anzeige von Mengenmeldungen.

* **Backend (Java / Spring Boot)**
  Bietet REST-Endpunkte, validiert Daten, persistiert Meldungen und steuert die Verarbeitung.

* **Persistenz (H2 im Prototyp)**
  Speichert sämtliche Mengenmeldungen inkl. Status und SOAP-Response.

* **SOAP-Adapter → EAR SOAP-Service**
  Verantwortlich für die Übermittlung an die offizielle EAR-Schnittstelle (`submitIstInput`).

## 1.1 Komponentenübersicht (Diagramm)

```
+-----------------+        +-------------------+        +------------------------+
|    Frontend     | -----> |    REST Backend   | -----> |      Persistenz (DB)    |
+-----------------+        +-------------------+        +------------------------+
                               |        |
                               |        v (async)
                               |   +------------------+
                               +-> |   SOAP-Adapter    | -----> EAR SOAP Service
                                   +------------------+          submitIstInput
```

---

# 2. Dynamische Sicht – Sequenz der Abgabe einer Mengenmeldung

## 2.1 Frontend sendet die Mengenmeldung über POST /mengenmeldungen.

## 2.2 Backend validiert die Daten:

* Pflichtfelder vorhanden
* Menge > 0
* Zeitraum ist logisch korrekt

## 2.3 Die Meldung wird mit Status **PENDING** gespeichert.

## 2.4 Ein asynchroner Worker liest alle ungesendeten Meldungen (Status PENDING).

## 2.5 Der SOAP-Adapter ruft den EAR SOAP-Webservice `submitIstInput` anhand der bereitgestellten WSDL auf.

## 2.6 Die SOAP-Response wird in der Persistenz gespeichert:

* Erfolg / Fehler
* Fehlercodes (vom EAR-System oder technisch)

## 2.7 Die Meldung erhält einen finalen Status:

* **OK** → erfolgreich übertragen
* **ERROR** → nach max. Retries fehlgeschlagen

## 2.8 Das Frontend kann über GET /mengenmeldungen jederzeit den Status abrufen.

---

# 3. Fehlerbehandlung

Die Architektur verwendet klare Fehlerbehandlungsregeln:

## 3.1 Validierungsfehler

Ungültige Daten → **HTTP 400 Bad Request**

## 3.2 Backend-/Datenbankfehler

Persistenzfehler → **HTTP 500 Internal Server Error**

## 3.3 SOAP-Verbindungsfehler

* Meldung verbleibt im Status **PENDING**
* Worker führt automatische Retries durch

## 3.4 Maximalversuche überschritten

* Meldung wird auf **ERROR** gesetzt
* SOAP-Fehlermeldungen werden persistiert und können eingesehen werden

---

# 4. Lastspitzenstrategie

Die Anforderungen verlangen, dass Mengenmeldungen jederzeit abgegeben werden können – auch bei erhöhtem Lastaufkommen. Das System folgt daher diesen Prinzipien:

## 4.1 Entkopplung von REST und SOAP

Die REST-Annahme der Daten ist **nicht abhängig** von der SOAP-Übertragung.
→ Das System bleibt reaktionsfähig.

## 4.2 Asynchrone Verarbeitung

Ein Workerprozess verarbeitet Meldungen im Hintergrund.
→ Lastspitzen können sauber abgefedert werden.

## 4.3 Persistenz als Puffer

Alle Meldungen werden sofort gespeichert.
→ Keine Meldung geht verloren.

## 4.4 Retry-Mechanismus

SOAP-Fehler sind nicht kritisch:

* automatische Wiederholungsversuche
* konsistente Fehlerzustände (**ERROR** nach Max-Retries)

---

# 5. Zusammenfassung

Die Architektur erfüllt folgende Ziele:

* Robuste und skalierbare Verarbeitung von Mengenmeldungen
* Hohe Verfügbarkeit durch asynchrone Verarbeitung
* Saubere Entkopplung zwischen REST und SOAP
* Klare Fehler- und Retry-Strategie
* Eindeutige Statusführung (PENDING / OK / ERROR)

Damit ist die Architektur vollständig und entspricht der Aufgabenstellung.
