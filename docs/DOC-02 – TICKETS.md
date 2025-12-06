# DOC-02 – TICKETS.md

# EAR – Ticket-Übersicht (User Stories & Technische Tickets)

Dieses Dokument beschreibt alle fachlichen und technischen Arbeitspakete (Tickets), abgeleitet aus den Anforderungen der EAR-Entwicklungsaufgabe.
Die Tickets sind in **Epics** gruppiert und können direkt in Jira, GitHub oder GitLab übernommen werden.

---

# EPIC FE – Frontend (UI für Mengenmeldungen)

## FE-01 – UI: Formular zur Abgabe von Mengenmeldungen

**Typ:** User Story
**Als** Sachbearbeiter
**möchte ich** eine Eingabemaske zur Abgabe einer Mengenmeldung
**um** strukturierte Daten an das EAR-System übermitteln zu können.

**Akzeptanzkriterien:**

* Felder: Hersteller-ID, Gerätetyp, Menge, Zeitraum (von/bis)
* Pflichtfeldvalidierung im Frontend
* Button „Absenden“ sendet POST `/api/mengenmeldungen`
* Erfolgs- und Fehlermeldungen sichtbar
* Formular blockiert während des Sendens

---

## FE-02 – UI: Anzeige abgegebener Mengenmeldungen

**Typ:** User Story
**Als** Sachbearbeiter
**möchte ich** eine Übersicht aller abgegebenen Mengenmeldungen sehen
**um** nachvollziehen zu können, welche Meldungen bereits erfolgreich übermittelt wurden.

**Akzeptanzkriterien:**

* Tabelle mit: ID, Hersteller-ID, Gerätetyp, Menge, Zeitraum, Status, Timestamp
* Daten via GET `/api/mengenmeldungen`
* Fehleranzeige bei API-Problemen
* Optional: Refresh-Button

---

## FE-03 – Frontend-Grundstruktur & Layout

**Typ:** Technisches Ticket
**Beschreibung:** Aufbau der Basisstruktur (Routing, Komponenten, Layout). Umsetzung gemäß Wireframes.

**Akzeptanzkriterien:**

* Projektstruktur aufgebaut
* Stylesheet/CSS-Basis definiert
* Mock-Daten für UI-Tests

---

# EPIC BE-REST – Backend REST API

## BE-01 – REST-Endpunkt: POST /mengenmeldungen

**Typ:** Technisches Ticket
**Beschreibung:** Implementierung des Endpunkts zur Abgabe einer Mengenmeldung.

**Akzeptanzkriterien:**

* Validierung der Eingaben
* Persistenz der Meldung
* Status initial „PENDING“
* Asynchroner SOAP-Call wird angestoßen
* Response enthält ID + Status

---

## BE-02 – REST-Endpunkt: GET /mengenmeldungen

**Typ:** Technisches Ticket
**Beschreibung:** Rückgabe einer paginierten Liste gespeicherter Mengenmeldungen.

**Akzeptanzkriterien:**

* Pagination (page, size)
* Sortierung nach Timestamp DESC
* Rückgabe enthält Status
* Fehlerbehandlung bei DB-Ausfall

---

## BE-03 – Datenmodell & DTO-Mapping

**Typ:** Technisches Ticket

**Akzeptanzkriterien:**

* Entity „Mengenmeldung“ mit Feldern: Hersteller-ID, Gerätetyp, Menge, Zeitraum, Status, SOAP-Response, Timestamp
* DTOs implementiert
* Mapper-Service vorhanden

---

# EPIC BE-PERSIST – Persistenz

## BE-10 – Persistenztechnologie wählen (ADR)

**Typ:** Architekturentscheidung

**Akzeptanzkriterien:**

* ADR dokumentiert (`DOC-04-ADR-001-persistence.md`)
* Alternativen bewertet
* Konsequenzen dokumentiert

---

## BE-11 – Repository-Implementierung

**Typ:** Technisches Ticket

**Akzeptanzkriterien:**

* JPA Repository bereit
* CRUD Operationen funktionsfähig
* Pagination unterstützt

---

# EPIC BE-SOAP – Integration des EAR SOAP-Services

## BE-20 – SOAP-Client implementieren

**Typ:** Technisches Ticket

**Akzeptanzkriterien:**

* SOAP-Stubs generiert
* Endpoint konfigurierbar
* Timeout und Logging konfiguriert
* Unit-Test mit Mock

---

## BE-21 – Asynchrone Übermittlung von Mengenmeldungen

**Typ:** Technisches Ticket

**Akzeptanzkriterien:**

* Async Worker verarbeitet Queue
* Statuswechsel PENDING → OK/ERROR
* Retry bei Fehlern (max. 3)
* Fehlerpersistenz

---

## BE-22 – SOAP-Fehleranalyse & Logging

**Typ:** Technisches Ticket

**Akzeptanzkriterien:**

* Logging von Request/Response ohne PII
* Fehlercodes klassifiziert
* Dead-Letter-Konzept dokumentiert

---

# EPIC QA – Tests

## QA-01 – Unit Tests Backend

**Akzeptanzkriterien:**

* Service-Layer getestet
* Mapper getestet
* Validierungen getestet

---

## QA-02 – Integrationstests REST

**Akzeptanzkriterien:**

* POST + GET Endpunkte End-to-End testbar
* H2 als Test-Datenbank

---

## QA-03 – SOAP-Adapter Tests (Mock)

**Akzeptanzkriterien:**

* Erfolgs- und Fehlerpfade getestet
* SOAP-Client gemockt

---

# EPIC DOC – Dokumentation

## DOC-01 – README

**Akzeptanzkriterien:**

* Anleitung: Build, Start, Konfiguration
* API-Samples

---

## DOC-02 – ADR Persistenz

**(siehe BE-10)**

---

## DOC-03 – Architektur-Dokument

**Akzeptanzkriterien:**

* Statische Sicht
* Dynamische Sicht
* Textliche Beschreibung

---

# EPIC MGMT – Entscheidungsvorlage

## MGMT-01 – Entscheidung SonarQube vs. Grafana/Prometheus

**Typ:** Management-Vorlage

**Akzeptanzkriterien:**

* Vergleich
* Empfehlung
* Aufwand/Nutzen bewertet
