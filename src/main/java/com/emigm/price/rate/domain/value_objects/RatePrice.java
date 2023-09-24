package com.emigm.price.rate.domain.value_objects;

import com.emigm.price.shared.domain.InvalidNullValue;
import com.emigm.price.shared.domain.InvalidValueError;
import com.emigm.price.shared.domain.value_objects.ValueObject;

import java.util.Objects;

public class RatePrice extends ValueObject<Double> {


    public RatePrice() {
    }

    public RatePrice(Double value) {
        super(value);
        validateNotNull();
        validateNotNegative();
    }

    private void validateNotNegative() {
        if (value() < 0) {
            throw new InvalidValueError("The price can not be negative.");
        }
    }

    void validateNotNull() {
        if (Objects.isNull(value())) {
            throw new InvalidNullValue("The price can not be null.");
        }
    }
}
