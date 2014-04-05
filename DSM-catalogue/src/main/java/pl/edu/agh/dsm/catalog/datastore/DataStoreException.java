package pl.edu.agh.dsm.catalog.datastore;

import org.springframework.http.HttpStatus;

public class DataStoreException extends Exception {
    private final HttpStatus errorCodeToReport;

    public DataStoreException(String message, Throwable reason, HttpStatus errorCodeToReport) {
        super(message, reason);
        this.errorCodeToReport = errorCodeToReport;
    }

    public HttpStatus getErrorCode() {
        return errorCodeToReport;
    }
}
