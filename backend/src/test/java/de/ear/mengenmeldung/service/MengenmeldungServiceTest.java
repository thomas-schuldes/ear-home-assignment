package de.ear.mengenmeldung.service;

import de.ear.assignment.model.Mengenmeldung;
import de.ear.assignment.model.SubmissionStatus;
import de.ear.assignment.repository.MengenmeldungRepository;
import de.ear.assignment.service.MengenmeldungService;
import de.ear.backend.soap.EarSoapClient;
import de.ear.backend.soap.SoapResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class MengenmeldungServiceTest {

    private MengenmeldungService service;
    private MengenmeldungRepository repository;
    private EarSoapClient soapClient;

    @BeforeEach
    void setUp() {
        repository = mock(MengenmeldungRepository.class);
        soapClient = mock(EarSoapClient.class);
        service = new MengenmeldungService(repository, soapClient);
    }

    @Test
    void createMengenmeldung_setsPendingAndPersists() {
        // given: Eingabedaten
        String hersteller = "HERST-123";
        String kategorie = "ELEKTRO";
        BigDecimal menge = BigDecimal.valueOf(120.0);
        LocalDate von = LocalDate.of(2025, 1, 1);
        LocalDate bis = LocalDate.of(2025, 12, 31);

        ArgumentCaptor<Mengenmeldung> captor = ArgumentCaptor.forClass(Mengenmeldung.class);

        when(repository.save(any(Mengenmeldung.class)))
                .thenAnswer(invocation -> {
                    Mengenmeldung saved = invocation.getArgument(0);
                    saved.setId(1L);
                    return saved;
                });

        // when
        Mengenmeldung result =
                service.createMengenmeldung(hersteller, kategorie, menge, von, bis);

        // then
        verify(repository).save(captor.capture());
        Mengenmeldung persisted = captor.getValue();

        assertThat(persisted.getStatus()).isEqualTo(SubmissionStatus.PENDING);
        assertThat(persisted.getHerstellerId()).isEqualTo("HERST-123");
        assertThat(result.getId()).isEqualTo(1L);
    }

    @Test
    void processPendingMeldungen_callsSoapAndUpdatesStatus() {
        // given
        Mengenmeldung pending = new Mengenmeldung();
        pending.setId(1L);
        pending.setStatus(SubmissionStatus.PENDING);

        when(repository.findByStatus(SubmissionStatus.PENDING))
                .thenReturn(List.of(pending));

        when(soapClient.submitIstInput(any(Mengenmeldung.class)))
                .thenReturn(SoapResult.ok("OK-123", "OK"));

        // when
        service.processPendingMeldungen();

        // then
        assertThat(pending.getStatus()).isEqualTo(SubmissionStatus.OK);
        assertThat(pending.getSoapResponseCode()).isEqualTo("OK-123");
        verify(repository, atLeastOnce()).save(pending);
        verify(soapClient, times(1)).submitIstInput(pending);
    }

    @Test
    void processPendingMeldungen_whenSoapFails_setsErrorStatus() {
        Mengenmeldung pending = new Mengenmeldung();
        pending.setId(1L);
        pending.setStatus(SubmissionStatus.PENDING);

        when(repository.findByStatus(SubmissionStatus.PENDING))
                .thenReturn(List.of(pending));

        when(soapClient.submitIstInput(any()))
                .thenReturn(SoapResult.error("ERR-500", "INTERNAL"));

        service.processPendingMeldungen();

        assertThat(pending.getStatus()).isEqualTo(SubmissionStatus.ERROR);
        assertThat(pending.getSoapResponseCode()).isEqualTo("ERR-500");
    }

}
