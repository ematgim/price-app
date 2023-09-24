package com.emigm.price.rate.domain.value_objects;

import com.emigm.price.shared.domain.InvalidNullValue;
import com.emigm.price.shared.domain.InvalidValueError;
import com.emigm.price.shared.domain.value_objects.ValueObject;

import java.util.Objects;

public class RatePriority extends ValueObject<Integer> {

    public RatePriority() {
    }

    public RatePriority(Integer value) {
        super(value);
        validateNotNull();
        validateNotNegative();
    }

    private void validateNotNegative() {
        if (value() < 0) {
            throw new InvalidValueError("The priority can not be negative.");
        }
    }

    void validateNotNull() {
        if (Objects.isNull(value())) {
            throw new InvalidNullValue("The priority id can not be null.");
        }
    }
}
