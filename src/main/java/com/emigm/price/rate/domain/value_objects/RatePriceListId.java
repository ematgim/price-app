package com.emigm.price.rate.domain.value_objects;

import com.emigm.price.shared.domain.InvalidNullValue;
import com.emigm.price.shared.domain.value_objects.ValueObject;

import java.util.Objects;

public class RatePriceListId extends ValueObject<Integer> {


    public RatePriceListId() {
    }

    public RatePriceListId(Integer value) {
        super(value);
        validate();
    }

    void validate() {
        if (Objects.isNull(value())) {
            throw new InvalidNullValue("The pricelist id can not be null.");
        }
    }
}
