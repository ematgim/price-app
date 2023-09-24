package com.emigm.price.rate.domain.value_objects;

import com.emigm.price.rate.domain.InvalidCurrencyError;
import com.emigm.price.shared.domain.InvalidNullValue;
import com.emigm.price.shared.domain.value_objects.ValueObject;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class RateCurrency extends ValueObject<String> {

    public RateCurrency() {
    }

    public RateCurrency(String value) {
        super(value);
        validateNotNull();
        validateCurrencyISO();
    }

    private void validateCurrencyISO() {
        List<String> validCurrencies = Arrays.asList("EUR", "USD");
        if (!validCurrencies.contains(value())) {
            throw new InvalidCurrencyError(this);
        }


    }

    void validateNotNull() {
        if (Objects.isNull(value())) {
            throw new InvalidNullValue("The currency id can not be null.");
        }
    }
}
