package de.ear.assignment.repository;

import de.ear.assignment.model.Mengenmeldung;
import de.ear.assignment.model.SubmissionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MengenmeldungRepository extends JpaRepository<Mengenmeldung, Long> {

    List<Mengenmeldung> findByStatus(SubmissionStatus status);
}
