package com.emigm.price.shared.domain;

public class InvalidNullValue extends DomainError {

    public InvalidNullValue(String message) {
        super("NotNullError", message);
    }
}
