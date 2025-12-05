package de.ear.backend.soap;

import de.ear.assignment.model.Mengenmeldung;
import de.ear.backend.config.EarSoapProperties;
import de.ear.soap.generated.MitteilungsService;
import de.ear.soap.generated.MitteilungsServicePort;
import de.ear.soap.generated.StandardResponse;
import de.ear.soap.generated.ServiceException;
import javax.xml.ws.BindingProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class EarSoapClientImpl implements EarSoapClient {

    private static final Logger log = LoggerFactory.getLogger(EarSoapClientImpl.class);

    private final EarSoapProperties properties;
    private final MitteilungsServicePort port;

    public EarSoapClientImpl(EarSoapProperties properties) {
        this.properties = properties;
        this.port = createPort();
    }

    private MitteilungsServicePort createPort() {
        MitteilungsService service = new MitteilungsService();
        MitteilungsServicePort port = service.getMitteilungsServicePort();

        Map<String, Object> ctx = ((BindingProvider) port).getRequestContext();

        ctx.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, properties.getEndpoint());
        ctx.put(BindingProvider.USERNAME_PROPERTY, properties.getUsername());
        ctx.put(BindingProvider.PASSWORD_PROPERTY, properties.getPassword());

        log.info("EAR SOAP Client initialisiert. endpoint={}", properties.getEndpoint());
        return port;
    }

    @Override
    public SoapResult submitIstInput(Mengenmeldung m) {

        log.info("Sende Mengenmeldung an EAR per SOAP, id={}", m.getId());

        try {
            BigDecimal menge = m.getMenge();
            int einheit = 1; // TODO echtes Mapping (STUECK/KILOGRAM/TONNEN etc.)
            int jahr = m.getZeitraumVon().getYear();
            int monat = m.getZeitraumVon().getMonthValue();
            int geraeteartnummer = mapGeraeteartnummer(m.getKategorie());
            int registrierungsnummer = mapRegistrierungsnummer(m.getHerstellerId());
            boolean wahrheitserklaerung = true; // fürs Assignment: immer true

            StandardResponse response = port.submitIstInput(
                    menge,
                    einheit,
                    jahr,
                    monat,
                    geraeteartnummer,
                    registrierungsnummer,
                    wahrheitserklaerung
            );

            boolean success = isSuccess(response);
            String code = extractCode(response);
            String message = extractMessage(response);

            if (success) {
                log.info("SOAP submitIstInput erfolgreich: code={}, message={}", code, message);
                return SoapResult.ok(code, message);
            } else {
                log.warn("SOAP submitIstInput FEHLER: code={}, message={}", code, message);
                return SoapResult.error(code, message);
            }

        } catch (ServiceException se) {
            log.warn("EAR ServiceException bei submitIstInput: {}", se.getMessage());
            log.debug("Detail-Stacktrace", se);
            return SoapResult.error("SERVICE_EXCEPTION", se.getMessage());
        } catch (Exception ex) {
            log.error("Unerwarteter Fehler beim Aufruf von submitIstInput", ex);
            return SoapResult.error("EXCEPTION", ex.getMessage());
        }
    }

    private int mapGeraeteartnummer(String kategorie) {
        if (kategorie == null) return 0;
        try {
            switch (kategorie) {
                case "KLEIN_GERAET":
                    return 101;
                case "GROSS_GERAET":
                    return 102;
                default: return 4010;
            }
        }
        catch (NumberFormatException e) { return 0; }
    }

    private int mapRegistrierungsnummer(String herstellerId) {
        try { return Integer.parseInt(herstellerId); }
        catch (Exception e) { return 12345678; } // EAR-Testsystem Dummy
    }

    private boolean isSuccess(StandardResponse response) {
        if (response == null) {
            return false;
        }
        return response.getCode() == 0;
    }

    private String extractCode(StandardResponse response) {
        if (response == null) {
            return null;
        }
        return String.valueOf(response.getCode());
    }

    private String extractMessage(StandardResponse response) {
        if (response == null) {
            return null;
        }
        return response.getText();
    }

}
