package com.tanvi.tech.tanvitechbe.exception.model;

public class EmailFoundException extends RuntimeException {

    private static final long serialVersionUID = -9074290905304307979L;

    public EmailFoundException() {
        super();
    }

    public EmailFoundException(final String message) {
        super(message);
    }

    public EmailFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
