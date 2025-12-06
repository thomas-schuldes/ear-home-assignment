# DOC-06 – Entscheidungsvorlage: SonarQube vs. Grafana / Prometheus

## Ziel

Auswahl eines Werkzeugs, das im Rahmen des EAR-Home-Assignments den höchsten Mehrwert bietet.

---

# 1. SonarQube

**Einsatzzweck:** Codequalität, Security, technische Schulden

### Vorteile

* Automatische statische Codeanalyse
* Security-Scanner (OWASP-Regeln, SAST)
* CI/CD Integration sehr einfach
* Qualitäts-Gates verhindern fehlerhafte Builds
* Identifiziert Duplication, Spaghetti-Code, fehlende Tests, Dead Code

### Einsatznutzen für den EAR-Prototyp

* Sehr hoher Nutzen, da der Fokus auf sauberem Java-Backendcode liegt
* Unterstützt die Demonstration professioneller Softwarequalität
* Kann im Interview sichtbar präsentiert werden (Dashboard → „Clean Code“)
* Schnelle Integration über Docker oder lokalen Server

---

# 2. Grafana / Prometheus

**Einsatzzweck:** Runtime-Monitoring, Metriken, asynchrones Systemverhalten

### Vorteile

* Exzellentes Monitoring für produktive Systeme
* Visualisierung von Throughput, Fehlern, Queue-Länge etc.
* Ideal für verteilte Services oder Microservices

### Nachteile für dieses Assignment

* Entfaltet seinen Nutzen erst bei **laufenden Produktivsystemen**, nicht bei Prototypen
* Zusätzliche Infrastruktur nötig (Prometheus + Exporter)
* Für das Home-Assignment **nicht erforderlich** und nicht gefordert
* Monitoring bringt im Interview keinen relevanten Vorteil

---

# 3. Empfehlung

Für den EAR-Prototyp wird **SonarQube** empfohlen.

### Begründung

* Unmittelbarer, sofort sichtbarer Mehrwert für Codequalität
* Starke Argumentationshilfe im Interview („sauber implementiert“, „Quality Gates bestanden“)
* Wenig Aufwand → großer Nutzen
* Leicht in CI/CD integrierbar

---

# 4. Zusammenfassung

| Kriterium          | SonarQube                 | Grafana / Prometheus |
| ------------------ | ------------------------- | -------------------- |
| Fokus              | Codequalität & Sicherheit | Runtime-Monitoring   |
| Nutzen im Prototyp | Sehr hoch                 | Gering               |
| Aufwand            | Niedrig                   | Mittel bis hoch      |
| Interview-Mehrwert | Hoch                      | Niedrig              |

### **Finale Entscheidung: SonarQube**

Das Werkzeug passt optimal zum Ziel des Assignments und erzeugt den größten Nutzen bei minimalem Aufwand.
