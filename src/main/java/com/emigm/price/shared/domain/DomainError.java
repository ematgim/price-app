package com.emigm.price.shared.domain;

public class DomainError extends RuntimeException {


    public DomainError(String errorCode, String message) {
        super(String.format("%s-%s", errorCode, message));
    }


}
