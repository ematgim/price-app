package com.emigm.price.shared.domain;

public class InvalidValueError extends DomainError {

    public InvalidValueError(String message) {
        super("InvalidValuelError", message);
    }
}
