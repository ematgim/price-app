package com.emigm.price.shared.domain.criteria;

import com.emigm.price.shared.domain.value_objects.ValueObject;

import java.io.Serializable;

public final class FilterValue extends ValueObject<Serializable> {

    public FilterValue(Serializable value) {
        super(value);
    }
}
