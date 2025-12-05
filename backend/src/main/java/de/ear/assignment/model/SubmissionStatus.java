package de.ear.assignment.model;

public enum SubmissionStatus {
    PENDING,   // lokal gespeichert, noch nicht an EAR gesendet
    OK,        // erfolgreich an EAR gesendet
    ERROR      // Versand an EAR fehlgeschlagen
}
