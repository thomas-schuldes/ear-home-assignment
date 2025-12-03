package de.ear.assignment.repository;

import de.ear.assignment.model.Mengenmeldung;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MengenmeldungRepository extends JpaRepository<Mengenmeldung, Long> {
}
