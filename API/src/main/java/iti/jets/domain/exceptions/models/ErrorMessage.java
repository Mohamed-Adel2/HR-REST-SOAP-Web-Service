package iti.jets.domain.exceptions.models;

import lombok.Getter;

@Getter
public class ErrorMessage {
    private String message;
    private int code;

    public ErrorMessage(String message, int code) {
        this.message = message;
        this.code = code;
    }
}
