package com.tanvi.tech.tanvitechbe.exception.model;

public class UserFoundException extends RuntimeException {

    private static final long serialVersionUID = 2219911166504759400L;

    public UserFoundException() {
        super();
    }

    public UserFoundException(final String message) {
        super(message);
    }

    public UserFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
