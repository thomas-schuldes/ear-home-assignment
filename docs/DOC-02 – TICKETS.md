# EAR – Ticket-Übersicht (User Stories & Technische Tickets)
Dieses Dokument beschreibt alle fachlichen und technischen Arbeitspakete (Tickets), abgeleitet aus
den Anforderungen der EAR-Entwicklungsaufgabe.  
Die Tickets sind in Epics gruppiert und können direkt in Jira, GitHub oder GitLab übernommen werden.

---

# EPIC FE – Frontend (UI für Mengenmeldungen)

## FE-01 – UI: Formular zur Abgabe von Mengenmeldungen
**Typ:** User Story  
**Als** Sachbearbeiter  
**möchte ich** eine Eingabemaske zur Abgabe einer Mengenmeldung  
**um** strukturierte Daten an das EAR-System übermitteln zu können.

**Akzeptanzkriterien:**
- Felder: Hersteller-ID, Gerätetyp, Menge, Zeitraum (von/bis)
- Pflichtfeldvalidierung im Frontend
- Button „Absenden“ sendet POST `/api/mengenmeldungen`
- Erfolgs- und Fehlermeldungen visuell sichtbar
- Formular blockiert während des Sendens (Loading Indicator)

---

## FE-02 – UI: Anzeige abgegebener Mengenmeldungen
**Typ:** User Story  
**Als** Sachbearbeiter  
**möchte ich** eine Übersicht aller abgegebenen Mengenmeldungen sehen  
**um** nachvollziehen zu können, welche Meldungen bereits erfolgreich übermittelt wurden.

**Akzeptanzkriterien:**
- Tabelle mit: ID, Hersteller-ID, Gerätetyp, Menge, Zeitraum, Status, Timestamp
- Daten werden via GET `/api/mengenmeldungen` geladen
- Fehler werden angezeigt (Toast/Alert)
- Optional: Refresh-Button

---

## FE-03 – Frontend-Grundstruktur & Layout
**Typ:** Technisches Ticket  
**Beschreibung:**  
Aufbau der Basisstruktur (Routing, Komponenten, State-Management).  
Umsetzung nach dem Wireframe/Mockup.

**Akzeptanzkriterien:**
- Projektstruktur steht (z. B. `src/components`, `src/pages`)
- Stylesheet/CSS-Basis definiert
- Mock-Daten zur ersten Anzeige möglich

---

# EPIC BE-REST – Backend REST API

## BE-01 – REST-Endpunkt: POST /mengenmeldungen
**Typ:** Technisches Ticket  
**Beschreibung:**  
Implementiert den REST-Endpunkt zur Abgabe einer Mengenmeldung.

**Akzeptanzkriterien:**
- Validierung der Eingaben (Menge > 0, Zeitraum konsistent)
- Persistenz der Meldung
- Markierung als „PENDING“
- Triggern der asynchronen SOAP-Übermittlung
- Response enthält ID + Status

---

## BE-02 – REST-Endpunkt: GET /mengenmeldungen
**Typ:** Technisches Ticket  
**Beschreibung:**  
Rückgabe einer paginierten Liste gespeicherter Mengenmeldungen.

**Akzeptanzkriterien:**
- Pagination-Parameter (page, size)
- Sortiert nach Timestamp absteigend
- Rückgabe enthält Status (OK, ERROR, PENDING)
- Fehlerbehandlung (Datenbank nicht erreichbar)

---

## BE-03 – Datenmodell & DTO-Mapping
**Typ:** Technisches Ticket  
**Beschreibung:**  
Definition der Entities & DTOs.

**Akzeptanzkriterien:**
- Entity „Mengenmeldung“ enthält:  
  Hersteller-ID, Gerätetyp, Menge, Zeitraum, Status, SOAP-Response, Timestamp
- DTOs für Request/Response vorhanden
- Mapper-Service implementiert

---

# EPIC BE-PERSIST – Persistenz

## BE-10 – Persistenztechnologie wählen (ADR)
**Typ:** Architektur-Entscheidung  
**Beschreibung:**  
Bewertung und Entscheidung der Persistenz mittels ADR Datei.

**Akzeptanzkriterien:**
- ADR dokumentiert in `docs/adr/adr-001-persistence.md`
- Entscheidung begründet (H2/Postgres/SQLite)
- Konsequenzen beschrieben

---

## BE-11 – Repository-Implementierung
**Typ:** Technisches Ticket  
**Akzeptanzkriterien:**
- JPA Repository angelegt
- CRUD Operationen funktionsfähig
- Pagination unterstützt

---

# EPIC BE-SOAP – Integration des EAR SOAP-Services

## BE-20 – SOAP-Client implementieren
**Typ:** Technisches Ticket  
**Beschreibung:**  
Anbindung an die EAR SOAP-Schnittstelle (`submitIstInput`).

**Akzeptanzkriterien:**
- Generierung der SOAP Stubs via wsimport oder Spring WS
- Konfiguration von Endpoint + Timeout
- Logging konfigurierbar
- Testbarer SOAP-Service-Adapter

---

## BE-21 – Asynchrone Übermittlung von Mengenmeldungen
**Typ:** Technisches Ticket  
**Beschreibung:**  
Mengenmeldungen müssen zu Lastspitzen jederzeit angenommen werden, daher Queue/Async-Verarbeitung.

**Akzeptanzkriterien:**
- Worker-Prozess sendet Meldungen asynchron
- Statuswechsel: PENDING → OK/ERROR
- Retry-Mechanismus bei SOAP-Fehlern (z. B. max 3 Versuche)
- Fehler persistiert

---

## BE-22 – SOAP-Fehleranalyse & Logging
**Typ:** Technisches Ticket  
**Akzeptanzkriterien:**
- SOAP Request/Response werden sicher geloggt (kein PII)
- Fehlercodes sauber klassifiziert
- Dead-Letter-Handhabung dokumentiert

---

# EPIC QA – Tests

## QA-01 – Unit Tests Backend
**Akzeptanzkriterien:**
- Services mit Mocks getestet
- Mapper getestet
- Validation getestet

---

## QA-02 – Integrationstests REST
**Akzeptanzkriterien:**
- POST + GET Endpunkte End-to-End testbar
- In-Memory DB (H2) wird verwendet

---

## QA-03 – SOAP-Adapter Tests (Mock)
**Akzeptanzkriterien:**
- SOAP-Client wird gemockt
- Erfolgs- und Fehlerpfade getestet

---

# EPIC DOC – Dokumentation

## DOC-01 – README
**Akzeptanzkriterien:**
- Build-Anleitung (Backend + Frontend)
- Start-Anleitung
- Beispiel-REST-Aufrufe
- Konfiguration der SOAP-Endpunkte

---

## DOC-02 – ADR Persistenz
**(siehe BE-10)**

---

## DOC-03 – Architektur-Dokument
**Akzeptanzkriterien:**
- Statische Sicht (Komponentendiagramm)
- Dynamischer Ablauf (Sequenzdiagramm)
- Textliche Beschreibung

---

# EPIC MGMT – Entscheidungsvorlage

## MGMT-01 – Entscheidung SonarQube vs. Grafana/Prometheus
**Typ:** Management-Vorlage  
**Akzeptanzkriterien:**
- Vergleich der beiden Lösungen
- Empfehlung anhand des Projektkontexts
- Aufwand/Nutzen/Governance bewertet
