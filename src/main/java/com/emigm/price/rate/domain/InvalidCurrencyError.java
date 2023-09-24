package com.emigm.price.rate.domain;

import com.emigm.price.rate.domain.value_objects.RateCurrency;
import com.emigm.price.shared.domain.DomainError;

public class InvalidCurrencyError extends DomainError {
    public InvalidCurrencyError(RateCurrency rateCurrency) {
        super("InvalidCurrencyError", String.format("The %s is not a valid currency", rateCurrency.value()));
    }
}
