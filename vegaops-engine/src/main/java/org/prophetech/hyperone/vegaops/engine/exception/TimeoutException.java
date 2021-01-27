package org.prophetech.hyperone.vegaops.engine.exception;

public class TimeoutException extends RuntimeException {
    public TimeoutException(String message, Throwable cause) {
        super(message, cause);
    }

    public TimeoutException() {
    }

    public TimeoutException(String message) {
        super(message);
    }
}
