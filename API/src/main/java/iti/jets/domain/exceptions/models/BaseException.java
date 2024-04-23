package iti.jets.domain.exceptions.models;

import org.glassfish.jersey.internal.Errors;

public class BaseException extends RuntimeException {
    private String message;

    public BaseException(String message) {
        super(message);
    }
}
