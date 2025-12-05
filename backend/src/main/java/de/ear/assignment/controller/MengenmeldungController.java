package de.ear.assignment.controller;

import de.ear.assignment.model.Mengenmeldung;
import de.ear.assignment.service.MengenmeldungService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/mengenmeldungen")
public class MengenmeldungController {

    private final MengenmeldungService service;

    public MengenmeldungController(MengenmeldungService service) {
        this.service = service;
    }

    // DTO für POST-Request
    public static class CreateMengenmeldungRequest {
        public String herstellerId;
        public String kategorie;
        public BigDecimal menge;
        public LocalDate zeitraumVon;
        public LocalDate zeitraumBis;
    }

    @PostMapping
    public ResponseEntity<Mengenmeldung> create(@RequestBody CreateMengenmeldungRequest req) {
        Mengenmeldung created = service.createMengenmeldung(
                req.herstellerId,
                req.kategorie,
                req.menge,
                req.zeitraumVon,
                req.zeitraumBis
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public List<Mengenmeldung> list() {
        return service.findAll();
    }

    // Manuelles Triggern des SOAP-Versands
    @PostMapping("/{id}/submit")
    public ResponseEntity<Void> submit(@PathVariable Long id) {
        service.sendeMengenmeldung(id);
        return ResponseEntity.accepted().build();
    }
}
