# EAR – Home Assignment (Mengenmeldesystem)

Dieses Repository enthält die vollständige Lösung für das EAR‑Home‑Assignment: ein System zur Erfassung, Persistierung und Übermittlung von Mengenmeldungen an einen bestehenden SOAP‑Dienst.

---

## 1. Überblick
Das System besteht aus:
- **Frontend (Web)** zur Erfassung und Anzeige von Mengenmeldungen
- **Backend (Java/Spring Boot)** mit REST‑API, Persistenzschicht und SOAP‑Anbindung
- **Dokumentation** (Tickets, Architektur, ADR, Entscheidungsvorlagen)
- **Automatisierten Tests** und Git‑Versionierung

Ziel ist die prototypische Umsetzung eines durchgängigen End‑to‑End‑Flows inkl. Fehlerbehandlung und Lastspitzen‑Strategie.

---

## 2. Features
- Erfassen einer neuen Mengenmeldung über eine Web‑UI
- Anzeigen aller vorhandenen Mengenmeldungen
- Java-Backend mit REST‑API
- Persistierung der Daten
- Weiterleitung der Daten an den offiziellen EAR‑SOAP-Service (`submitIstInput`)
- Robuste Fehlerbehandlungen
- Unit- und Integrationstests
- Architektur- und Entscheidungsdokumentation

---

## 3. Technologie‑Stack
### Backend
- **Java 17**
- **Spring Boot** (Web, Validation, Data JPA, Error Handling)
- **Hibernate + JPA**
- **H2** (lokale Entwicklung) oder **PostgreSQL** (realistische Umgebung)
- **SOAP‑Client** via Spring Web Services / JAX‑WS
- **Maven** Build
- **JUnit + MockMvc** Tests

### Frontend
- **HTML / CSS / JavaScript** (leichtgewichtiger Prototyp)
- Optional: Node.js für Dev‑Server

### Sonstiges
- Git
- Docker (optional)
- Jenkins/GitHub Actions (optional)

---

## 4. Quickstart
### Voraussetzungen
- Java 17+
- Maven 3.8+
- Optional: Node.js 18+

### Backend starten
```bash
git clone <REPO-URL>
cd ear-home-assignment/backend
mvn clean install
mvn spring-boot:run
```
Backend läuft unter:
```
http://localhost:8080
```

## 5. REST‑API Endpunkte
### POST /api/mengenmeldungen
Erstellt eine neue Mengenmeldung.

### GET /api/mengenmeldungen
Liefert alle gespeicherten Mengenmeldungen.

### GET /api/mengenmeldungen/{id}
Liefert Details zu einer Mengenmeldung.

Fehler werden gemäß Spring Boot Error‑Response strukturiert zurückgegeben.

---

## 6. Projektstruktur
```text
ear-home-assignment/
  backend/                      # Spring Boot Backend
    src/main/java
    src/test/java
    pom.xml

  frontend/                     # Web-Frontend
    index.html
    js/
    css/

  docs/                         # Dokumentation
    README.md      # Internes Entwickler-README (Projektüberblick, Setup, Kontext)
    DOC-01-README.md
    DOC-02-TICKETS.md
    DOC-03-ARCHITEKTUR.md
    DOC-04-ADR-001-persistence.md
    DOC-05-frontend-wireframe/
    DOC-06-decision-sonarqube-vs-grafana.md

  docs/reference/
    ear-assignment.pdf
```

---

## 7. Architekturüberblick
### Statische Sicht
- **Frontend** → REST‑API → **Service-Layer** → **Persistenz** → **SOAP‑Client → EAR-System**
- Modularer Aufbau: Controller / Service / Repository / SOAP‑Adapter
- Klare Trennung: Domain, DTO, Mapping

### Dynamische Sicht (Kurzfassung)
1. User füllt eine Meldung im Frontend aus
2. Frontend sendet POST an REST‑API
3. Backend persistiert Meldung
4. Backend ruft EAR‑SOAP‑Service `submitIstInput` auf
5. SOAP‑Response bestimmt Erfolg/Fehlschlag
6. Meldung wird in UI sichtbar

Ausführliche Darstellung in:  
`docs/DOC-03-ARCHITEKTUR.md`

---

## 8. Persistenzentscheidung (ADR)
Die Auswahl zwischen H2 und PostgreSQL ist dokumentiert im ADR:  
`docs/DOC-04-ADR-001-persistence.md`

Kurzfassung:
- H2 für lokale Entwicklung (schnell, konfigurationsarm)
- PostgreSQL empfohlen für reale Umgebungen (Skalierbarkeit, Tooling, Stabilität)

---

## 9. Tests
```bash
cd backend
mvn test
```
- **Unit‑Tests:** Services, Validierungen, SOAP‑Adapter (mit Mock)
- **Integrationstests:** REST‑API + JPA + H2

Logs & Coverage‑Hinweise siehe:  
`docs/DOC-03-ARCHITEKTUR.md`

---

## 10. Dokumentation
Alle Dokumente sind im Repository enthalten:
- Tickets: `DOC-02-TICKETS.md`
- Architektur: `DOC-03-ARCHITEKTUR.md`
- Persistenz‑ADR: `DOC-04-ADR-001-persistence.md`
- Wireframes: `DOC-05-frontend-wireframe/`
- Entscheidungsvorlage: `DOC-06-decision-sonarqube-vs-grafana.md`

---

## 11. Hinweise
Dieses Projekt wurde als **Home Assignment** erstellt und dient ausschließlich Demonstrations‑ und Bewertungszwecken.
