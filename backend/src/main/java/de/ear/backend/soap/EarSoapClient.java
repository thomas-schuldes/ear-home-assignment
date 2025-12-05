package de.ear.backend.soap;

import de.ear.assignment.model.Mengenmeldung;

public interface EarSoapClient {

    /**
     * Übermittelt eine Mengenmeldung über den EAR MitteilungsService.
     */
    SoapResult submitIstInput(Mengenmeldung mengenmeldung);
}
