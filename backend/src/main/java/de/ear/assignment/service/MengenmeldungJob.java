package de.ear.assignment.service;

import de.ear.assignment.model.Mengenmeldung;
import de.ear.assignment.model.SubmissionStatus;
import de.ear.assignment.repository.MengenmeldungRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MengenmeldungJob {

    private static final Logger log = LoggerFactory.getLogger(MengenmeldungJob.class);

    private final MengenmeldungRepository repository;
    private final MengenmeldungService service;

    public MengenmeldungJob(MengenmeldungRepository repository,
                            MengenmeldungService service) {
        this.repository = repository;
        this.service = service;
    }

    @Scheduled(fixedDelay = 60_000) // alle 60 Sekunden
    public void sendePendingMeldungen() {
        List<Mengenmeldung> pending = repository.findByStatus(SubmissionStatus.PENDING);
        if (pending.isEmpty()) {
            return;
        }
        log.info("Starte Versand-Job für {} PENDING-Mengenmeldungen", pending.size());
        for (Mengenmeldung mm : pending) {
            service.sendeMengenmeldung(mm.getId());
        }
    }
}
