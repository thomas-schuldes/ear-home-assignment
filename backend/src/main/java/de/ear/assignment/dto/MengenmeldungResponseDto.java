package de.ear.assignment.dto;

import java.math.BigDecimal;

public class MengenmeldungResponseDto {

    private Long id;
    private String herstellerId;
    private String kategorie;
    private BigDecimal menge;
    private String zeitraumVon;
    private String zeitraumBis;
    private String status;
    private String createdAt;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getHerstellerId() { return herstellerId; }
    public void setHerstellerId(String herstellerId) { this.herstellerId = herstellerId; }

    public String getKategorie() { return kategorie; }
    public void setKategorie(String kategorie) { this.kategorie = kategorie; }

    public BigDecimal getMenge() { return menge; }
    public void setMenge(BigDecimal menge) { this.menge = menge; }

    public String getZeitraumVon() { return zeitraumVon; }
    public void setZeitraumVon(String zeitraumVon) { this.zeitraumVon = zeitraumVon; }

    public String getZeitraumBis() { return zeitraumBis; }
    public void setZeitraumBis(String zeitraumBis) { this.zeitraumBis = zeitraumBis; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}
