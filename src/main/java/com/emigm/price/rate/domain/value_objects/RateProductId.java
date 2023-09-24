package com.emigm.price.rate.domain.value_objects;

import com.emigm.price.shared.domain.InvalidNullValue;
import com.emigm.price.shared.domain.value_objects.ValueObject;

import java.util.Objects;

public class RateProductId extends ValueObject<Integer> {


    public RateProductId() {
    }

    public RateProductId(Integer value) {
        super(value);
        validate();
    }

    void validate() {
        if (Objects.isNull(value())) {
            throw new InvalidNullValue("The product id can not be null.");
        }
    }
}
