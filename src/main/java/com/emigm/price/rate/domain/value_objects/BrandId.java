package com.emigm.price.rate.domain.value_objects;

import com.emigm.price.rate.domain.IncorrectBrandId;
import com.emigm.price.shared.domain.value_objects.ValueObject;

public class BrandId extends ValueObject<Integer> {

    public BrandId(Integer value) {
        super(value);
    }

}
