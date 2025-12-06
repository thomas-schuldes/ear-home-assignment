# DOC-01 – README.md

# EAR – Dokumentation

Dieses Verzeichnis enthält alle fachlichen, technischen und architektonischen Dokumente
für die EAR-Entwicklungsaufgabe. Es dient als **zentrale Einstiegspunkt-Dokumentation** für Reviewer, Entwickler und das spätere Interview.

---

## 🎯 Zweck dieses Verzeichnisses

* Überblick über alle relevanten Artefakte
* Einordnung der Dokumente in die Aufgabenstellung
* Orientierung für Reviewer, damit sie schnell finden, was sie prüfen möchten
* Nachweis, dass alle Deliverables vollständig im Repository versioniert wurden

---

## 📁 Struktur der Dokumentation

### **DOC-02 – TICKETS.md**

Enthält alle **User Stories**, Akzeptanzkriterien und technischen Tickets.
Diese wurden aus den Anforderungen der Aufgabe abgeleitet und sinnvoll strukturiert.

### **DOC-03 – Architektur-Dokument.md**

Beinhaltet:

* Statische Architektur (Komponenten, Schichten, Technologien)
* Dynamische Architektur (Ablaufdiagramme: Submit, Fehlerbehandlung, SOAP-Call)
* Begründungen für Designentscheidungen

### **DOC-04 – ADR-001-persistence.md**

Ein **Architecture Decision Record (ADR)** zur Wahl der Persistenztechnologie.
Dokumentiert Motivation, Alternativen, Entscheidung und Konsequenzen.

### **DOC-05 – Frontend Wireframes.md**

Wireframes / Mockups für das Frontend:
Formular, Listenansicht, Detailansicht, inkl. UI-Notizen.

### **DOC-06 – decision-sonarqube-vs-grafana.md**

Management-taugliche Vorlage zur Entscheidung zwischen:

* Code-Qualität und Static Analysis (**SonarQube**)
* Monitoring / Observability (**Grafana + Prometheus**)

---

## 🔗 Bezug zur Aufgabenstellung

Gemäß der EAR-Entwicklungsaufgabe müssen alle Artefakte vollständig dokumentiert
und versioniert abgelegt werden.
Dieses Verzeichnis erfüllt diese Anforderung vollständig und beinhaltet alle Deliverables:

* Analyse der Anforderungen → **User Stories / Tickets**
* Architekturbeschreibung → **statische & dynamische Sicht**
* Architekturentscheidungen → **ADR**
* UI-Konzept → **Wireframes**
* Technische Managemententscheidung → **SonarQube vs. Grafana**

---

## ✔ Status

Alle geforderten Dokumente wurden erstellt oder vorgesehen und sind über dieses
README sauber auffindbar und strukturiert.

---

## 📌 Hinweis

Dieses Dokument dient ausschließlich der Übersicht.
Die technischen Inhalte befinden sich in den entsprechenden Dateien innerhalb dieses Verzeichnisses.
