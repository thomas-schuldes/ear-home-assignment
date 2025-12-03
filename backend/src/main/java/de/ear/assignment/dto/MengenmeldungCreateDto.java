package de.ear.assignment.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class MengenmeldungCreateDto {

    @NotBlank
    private String herstellerId;

    @NotBlank
    private String kategorie;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal menge;

    @NotBlank
    private String zeitraumVon; // yyyy-MM-dd

    @NotBlank
    private String zeitraumBis;

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
}
