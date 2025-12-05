package de.ear.assignment.service;

import de.ear.assignment.model.Mengenmeldung;
import de.ear.assignment.model.SubmissionStatus;
import de.ear.assignment.repository.MengenmeldungRepository;
import de.ear.backend.soap.EarSoapClient;
import de.ear.backend.soap.SoapResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class MengenmeldungService {

    private static final Logger log = LoggerFactory.getLogger(MengenmeldungService.class);

    private final MengenmeldungRepository repository;
    private final EarSoapClient earSoapClient;

    public MengenmeldungService(MengenmeldungRepository repository,
                                EarSoapClient earSoapClient) {
        this.repository = repository;
        this.earSoapClient = earSoapClient;
    }

    @Transactional
    public Mengenmeldung createMengenmeldung(String herstellerId,
                                             String kategorie,
                                             BigDecimal menge,
                                             LocalDate von,
                                             LocalDate bis) {

        Mengenmeldung mm = new Mengenmeldung(
                herstellerId,
                kategorie,
                menge,
                von,
                bis,
                SubmissionStatus.PENDING
        );
        Mengenmeldung saved = repository.save(mm);
        log.info("Neue Mengenmeldung angelegt, id={}, status={}", saved.getId(), saved.getStatus());
        return saved;
    }

    @Transactional
    public void sendeMengenmeldung(Long id) {
        Mengenmeldung mm = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Mengenmeldung nicht gefunden: " + id));

        log.info("Starte SOAP-Versand für Mengenmeldung id={}, status={}", mm.getId(), mm.getStatus());

        if (mm.getStatus() != SubmissionStatus.PENDING) {
            log.warn("Mengenmeldung {} hat Status {} – wird nicht gesendet.", mm.getId(), mm.getStatus());
            return;
        }

        SoapResult result = earSoapClient.submitIstInput(mm);

        if (result.isSuccess()) {
            mm.setStatus(SubmissionStatus.OK);
            log.info("Mengenmeldung {} erfolgreich gesendet. SOAP code={}, message={}",
                    mm.getId(), result.getCode(), result.getMessage());
        } else {
            mm.setStatus(SubmissionStatus.ERROR);
            log.warn("Mengenmeldung {} FEHLER beim Senden. SOAP code={}, message={}",
                    mm.getId(), result.getCode(), result.getMessage());
        }

        repository.save(mm);
    }

    public List<Mengenmeldung> findAll() {
        return repository.findAll();
    }
}
