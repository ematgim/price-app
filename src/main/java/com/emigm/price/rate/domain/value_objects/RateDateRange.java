package com.emigm.price.rate.domain.value_objects;

import com.emigm.price.rate.domain.InvalidDateRange;
import com.emigm.price.rate.domain.value_objects.date_range.DateRangeDate;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

public class RateDateRange {
    private final DateRangeDate startDate;
    private final DateRangeDate endDate;


    public RateDateRange() {
        this.startDate = new DateRangeDate();
        this.endDate = new DateRangeDate();
    }

    public RateDateRange(DateRangeDate startDate, DateRangeDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        validateDateRange();
    }

    public RateDateRange(Timestamp startDate, Timestamp endDate) {
        this.startDate = new DateRangeDate(startDate);
        this.endDate = new DateRangeDate(endDate);
        validateDateRange();
    }

    private void validateDateRange() {
        if (startDate.value().isAfter(endDate.value())) {
            throw new InvalidDateRange(this);
        }
    }

    public DateRangeDate startDate() {
        return startDate;
    }

    public DateRangeDate endDate() {
        return endDate;
    }

    public LocalDateTime startDateValue() {
        return startDate.value();
    }

    public LocalDateTime endDateValue() {
        return endDate.value();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RateDateRange that = (RateDateRange) o;
        return Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate);
    }
}
