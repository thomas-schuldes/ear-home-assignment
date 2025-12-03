package de.ear.assignment.controller;

import de.ear.assignment.dto.MengenmeldungCreateDto;
import de.ear.assignment.dto.MengenmeldungResponseDto;
import de.ear.assignment.service.MengenmeldungService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mengenmeldungen")
@CrossOrigin
public class MengenmeldungController {

    private final MengenmeldungService service;

    public MengenmeldungController(MengenmeldungService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MengenmeldungResponseDto> submit(
            @RequestBody @Valid MengenmeldungCreateDto dto) {
        return ResponseEntity.ok(service.submit(dto));
    }

    @GetMapping
    public List<MengenmeldungResponseDto> list() {
        return service.getAll();
    }
}
