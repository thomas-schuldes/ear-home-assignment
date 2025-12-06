# DOC-05 – Frontend Wireframes / Mockups

Dieses Dokument fasst alle Wireframes für das EAR-Home-Assignment in **einer einzigen Datei** zusammen. Es dient als UI-Konzept für das spätere Frontend und erfüllt vollständig die Anforderungen aus Aufgabe 4.

---

# 1. Überblick

Das Frontend umfasst drei zentrale Screens:

* **Eingabe einer Mengenmeldung** (Formular)
* **Liste aller abgegebenen Mengenmeldungen**
* **Detailansicht einer einzelnen Meldung** (optional, aber sehr hilfreich für Interviews)

Die Wireframes sind als ASCII-Skizzen dargestellt und durch Beschreibungen ergänzt.

---

# 2. Wireframe: Eingabe einer Mengenmeldung

```
+------------------------------------------------------------+
| EAR Mengenmeldesystem                                       |
+------------------------------------------------------------+
| Neue Mengenmeldung                                          |
+------------------------------------------------------------+
| Hersteller:        [______________________________]         |
| Produktkategorie:  [______________________________]         |
| Menge (kg):        [___________]                            |
| Zeitraum:          [______________]                         |
|                                                            |
| [   SENDEN   ]                                             |
+------------------------------------------------------------+
| Hinweis: Pflichtfelder werden validiert. Fehler erscheinen  |
|          direkt unter den Feldern.                         |
+------------------------------------------------------------+
```

### Beschreibung

* Minimalistische, zweckmäßige UI
* Validierung direkt im Formular
* „SENDEN“ löst POST /api/mengenmeldungen aus
* Erfolg: Anzeige oder Weiterleitung zur Liste

---

# 3. Wireframe: Liste der abgegebenen Mengenmeldungen

```
+------------------------------------------------------------+
| EAR Mengenmeldesystem                                       |
+------------------------------------------------------------+
| Abgegebene Mengenmeldungen                                 |
+------------------------------------------------------------+
| ID | Hersteller     | Kategorie     | Menge | Datum  | ... |
|------------------------------------------------------------|
| 17 | ACME GmbH      | Elektrogeräte | 120kg | 2025-12 | [>] |
| 16 | Müller AG      | Lampen        |  45kg | 2025-12 | [>] |
| 15 | Plastics GmbH  | Kunststoffe   | 200kg | 2025-11 | [>] |
+------------------------------------------------------------+
| [Neue Meldung erfassen]                                     |
+------------------------------------------------------------+
```

### Beschreibung

* Tabellenübersicht aller Meldungen
* Daten aus GET /api/mengenmeldungen
* Rechts optional: Detail-Button
* Button zur Erfassung einer neuen Meldung

---

# 4. Wireframe: Detailansicht einer Meldung

```
+------------------------------------------------------------+
| EAR Mengenmeldesystem                                       |
+------------------------------------------------------------+
| Details der Mengenmeldung (#17)                             |
+------------------------------------------------------------+
| Hersteller:        ACME GmbH                                |
| Kategorie:         Elektrogeräte                             |
| Menge:             120 kg                                   |
| Zeitraum:          2025-12                                   |
| SOAP-Status:       Erfolgreich übertragen                    |
+------------------------------------------------------------+
| [Zurück zur Liste]                                          |
+------------------------------------------------------------+
```

### Beschreibung

* Hilfreich bei Review und Troubleshooting
* SOAP-Status zeigt Erfolg/Fehler der Übertragung

---

# 5. UX Notes

* UI soll **funktional**, nicht visuell komplex sein
* Klare Fehlerdarstellung bei fehlenden Pflichtfeldern
* Rückmeldung nach erfolgreichem Submit
* Konsistente Navigation: Liste → Detail → Liste / Neue Meldung

---

# 6. Einzeldateien (logische Struktur für das Repository)

Diese Abschnitte entsprechen den theoretischen Einzeldateien:

### wireframe-input.md

```
(Eingabeformular – identisch zu Abschnitt 2)
```

### wireframe-list.md

```
(Listenansicht – identisch zu Abschnitt 3)
```

### wireframe-detail.md

```
(Detailansicht – identisch zu Abschnitt 4)
```

### notes.md

```
- Minimalistische UI
- Fokus auf Funktionalität
- Validierungen sichtbar
- SOAP-Status anzeigen
```

---

# 7. Abschluss

Dieses Dokument dient als vollständige UI-Planung für das Frontend und deckt alle Anforderungen der Aufgabe 4 ab. Die ASCII-Wireframes sind leicht erweiterbar und können im Interview erläutert oder iteriert werden.
