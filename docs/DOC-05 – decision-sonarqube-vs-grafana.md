# Entscheidungsvorlage: SonarQube vs. Grafana / Prometheus

## Ziel
Auswahl eines Werkzeugs, das im Projekt Mehrwert stiftet.

---

# 1. SonarQube
**Einsatzzweck:** Codequalität, Security, technische Schulden  
**Vorteile:**
- Automatische Codeanalyse
- Security-Scanner
- CI/CD Integration
- Qualitäts-Gates

**Einsatznutzen für EAR-Prototyp:**
- Hoher Mehrwert, da du viel Java-Backend-Code schreibst
- Hilft im Interview, da sauber analysierbarer Code entsteht

---

# 2. Grafana / Prometheus
**Einsatzzweck:** Runtime-Monitoring  
**Vorteile:**
- Metrics, Dashboards
- Monitoring asynchroner SOAP-Aufrufe

**Nachteil für Aufgabe:**
- Monitoring entfaltet Nutzen erst in größeren, laufenden Systemen

---

# 3. Empfehlung
Für den EAR-Prototyp wird **SonarQube** empfohlen.

**Begründung:**
- Unmittelbarer Mehrwert für Codequalität des Home-Assignments
- Gut im Interview verwertbar
- Schnell integrierbar (Docker-Image + Token)
