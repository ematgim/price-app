package com.emigm.price.rate.domain;

import com.emigm.price.shared.domain.DomainError;

public class RateNotFound extends DomainError {
    public RateNotFound() {
        super("RateNotFound", "Rate not found");
    }
}
