package com.emigm.price.rate.domain.value_objects.date_range;

import com.emigm.price.shared.domain.value_objects.ValueObject;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class DateRangeDate extends ValueObject<LocalDateTime> {

    public DateRangeDate() {
    }

    public DateRangeDate(LocalDateTime value) {
        super(value);
    }
    public DateRangeDate(Timestamp value) {
        super(value.toLocalDateTime());
    }




}
