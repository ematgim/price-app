package com.emigm.price.rate.domain;

import com.emigm.price.rate.domain.value_objects.BrandId;
import com.emigm.price.shared.domain.DomainError;

public class IncorrectBrandId extends DomainError {

    public IncorrectBrandId(BrandId brandId) {
        super("IncorrectBrand", String.format("The brand %s is not correct", brandId.value()));
    }
}
