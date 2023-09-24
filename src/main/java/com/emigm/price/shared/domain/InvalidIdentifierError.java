package com.emigm.price.shared.domain;

public class InvalidIdentifierError extends DomainError {
    public InvalidIdentifierError() {
        super("InvalidIdentifierError", "Invalid identifier format");
    }
}
