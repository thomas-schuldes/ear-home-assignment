package de.ear.assignment.service;

import de.ear.assignment.dto.MengenmeldungCreateDto;
import de.ear.assignment.dto.MengenmeldungResponseDto;
import de.ear.assignment.model.Mengenmeldung;
import de.ear.assignment.model.SubmissionStatus;
import de.ear.assignment.repository.MengenmeldungRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class MengenmeldungService {

    private final MengenmeldungRepository repository;
    private final DateTimeFormatter DATE_FMT = DateTimeFormatter.ISO_LOCAL_DATE;

    public MengenmeldungService(MengenmeldungRepository repository) {
        this.repository = repository;
    }

    public MengenmeldungResponseDto submit(MengenmeldungCreateDto dto) {
        LocalDate von = LocalDate.parse(dto.getZeitraumVon(), DATE_FMT);
        LocalDate bis = LocalDate.parse(dto.getZeitraumBis(), DATE_FMT);

        Mengenmeldung entity = new Mengenmeldung(
                dto.getHerstellerId(),
                dto.getKategorie(),
                dto.getMenge(),
                von,
                bis,
                SubmissionStatus.PENDING
        );

        Mengenmeldung saved = repository.save(entity);

        // TODO: später asynchronen SOAP-Aufruf triggern

        return toResponse(saved);
    }

    public List<MengenmeldungResponseDto> getAll() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    private MengenmeldungResponseDto toResponse(Mengenmeldung m) {
        MengenmeldungResponseDto dto = new MengenmeldungResponseDto();
        dto.setId(m.getId());
        dto.setHerstellerId(m.getHerstellerId());
        dto.setKategorie(m.getKategorie());
        dto.setMenge(m.getMenge());
        dto.setZeitraumVon(m.getZeitraumVon().toString());
        dto.setZeitraumBis(m.getZeitraumBis().toString());
        dto.setStatus(m.getStatus().name());
        dto.setCreatedAt(m.getCreatedAt().toString());
        return dto;
    }
}
