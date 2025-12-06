# DOC-04 – ADR-001-persistence.md

# Architecture Decision Record – Persistenztechnologie

## 1. Kontext

Das Backend des EAR-Systems muss Mengenmeldungen zuverlässig **persistieren**, um:

* eine asynchrone Verarbeitung (PENDING → OK/ERROR) zu ermöglichen,
* SOAP-Responses zu speichern,
* Statusänderungen transparent und abrufbar zu halten,
* und Lastspitzen abzufedern.

Für das Home Assignment soll eine leichtgewichtige, schnell einsetzbare Lösung verwendet werden, ohne externe Infrastruktur.

---

## 2. Optionen

### **Option A – H2 (In-Memory oder File-basiert)**

* Kein Setup notwendig
* Extrem schnelle Startzeit
* Perfekt für Tests & Prototypen
* SQL kompatibel, unterstützt JPA

### **Option B – PostgreSQL**

* Produktionsreif
* Stabil, weit verbreitet
* Unterstützt starke Transaktionsmodelle
* Benötigt Installation oder Docker

### **Option C – SQLite**

* File-basierte DB
* Einfach einzusetzen
* Gut für Embedded-Szenarien
* Nicht ideal für komplexe JPA-Setups

---

## 3. Entscheidung

✔ **Für den Prototyp wird H2 verwendet.**

Damit ist die Persistenz sofort einsatzbereit, CI-fähig und ohne Zusatzinstallation verwendbar.

---

## 4. Begründung

* Keine Infrastruktur nötig → schneller Einstieg
* Optimal für automatisierte Tests (JUnit + H2)
* Sehr geringe Startzeit
* JPA-basierte Entities bleiben unverändert nutzbar
* Wechsel zu PostgreSQL später problemlos möglich (nur `application.properties` ändern)

---

## 5. Konsequenzen

### **Vorteile**

* Entwicklerfreundlich
* Testbar ohne externe Abhängigkeiten
* Ideal für das Home Assignment

### **Nachteile**

* Nicht für produktive Nutzung geeignet
* Concurrency & Storage begrenzt
* SOAP-Lastszenarien nur eingeschränkt realistisch testbar

### **Folgen für zukünftige Entwicklung**

* Ein Umstieg auf PostgreSQL wird empfohlen, wenn:

    * größere Datenmengen erwartet werden,
    * mehrere Instanzen betrieben werden sollen,
    * robuste Transaktionssicherheit notwendig ist.

---

## 6. Status

**Status: Accepted**
Datum: 2025-12-06

Diese Entscheidung erfüllt die Anforderungen des Assignments und bildet eine solide Grundlage für Entwicklung & Tests.
