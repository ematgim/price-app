package com.emigm.price.rate.domain;

import com.emigm.price.rate.domain.value_objects.RateDateRange;
import com.emigm.price.shared.domain.DomainError;


public class InvalidDateRange extends DomainError {
    public InvalidDateRange(RateDateRange data) {
        super("InvalidDateRange", String.format("Date %s is before %s", data.endDate(), data.startDate()));
    }
}
