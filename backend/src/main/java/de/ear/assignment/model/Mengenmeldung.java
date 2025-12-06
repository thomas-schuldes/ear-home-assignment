package de.ear.assignment.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
public class Mengenmeldung {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String herstellerId;

    private String kategorie;

    private BigDecimal menge;

    private LocalDate zeitraumVon;

    private LocalDate zeitraumBis;

    @Enumerated(EnumType.STRING)
    private SubmissionStatus status;

    private final OffsetDateTime createdAt;

    private String soapResponseCode;

    public Mengenmeldung() {
        // for JPA
        this.createdAt = OffsetDateTime.now();
    }

    public Mengenmeldung(String herstellerId,
                         String kategorie,
                         BigDecimal menge,
                         LocalDate zeitraumVon,
                         LocalDate zeitraumBis,
                         SubmissionStatus status) {
        this.herstellerId = herstellerId;
        this.kategorie = kategorie;
        this.menge = menge;
        this.zeitraumVon = zeitraumVon;
        this.zeitraumBis = zeitraumBis;
        this.status = status;
        this.createdAt = OffsetDateTime.now();
    }

    public Long getId() { return id; }
    public String getHerstellerId() { return herstellerId; }
    public String getKategorie() { return kategorie; }
    public BigDecimal getMenge() { return menge; }
    public LocalDate getZeitraumVon() { return zeitraumVon; }
    public LocalDate getZeitraumBis() { return zeitraumBis; }
    public SubmissionStatus getStatus() { return status; }
    public OffsetDateTime getCreatedAt() { return createdAt; }

    public void setStatus(SubmissionStatus status) {
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setHerstellerId(String herstellerId) {
        this.herstellerId = herstellerId;
    }

    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }

    public void setZeitraumVon(LocalDate zeitraumVon) {
        this.zeitraumVon = zeitraumVon;
    }

    public void setZeitraumBis(LocalDate zeitraumBis) {
        this.zeitraumBis = zeitraumBis;
    }

    public void setMenge(BigDecimal menge) {
        this.menge = menge;
    }

    public String getSoapResponseCode() {
        return soapResponseCode;
    }

    public void setSoapResponseCode(String soapResponseCode) {
        this.soapResponseCode = soapResponseCode;
    }

}
