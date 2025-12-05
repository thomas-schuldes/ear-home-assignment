package de.ear.backend.soap;

public class SoapResult {

    private final boolean success;
    private final String code;
    private final String message;

    private SoapResult(boolean success, String code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public static SoapResult ok(String code, String message) {
        return new SoapResult(true, code, message);
    }

    public static SoapResult error(String code, String message) {
        return new SoapResult(false, code, message);
    }

    public boolean isSuccess() { return success; }
    public String getCode() { return code; }
    public String getMessage() { return message; }
}
