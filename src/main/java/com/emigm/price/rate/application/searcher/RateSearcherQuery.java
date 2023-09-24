package com.emigm.price.rate.application.searcher;

import com.emigm.price.rate.domain.value_objects.RateProductId;
import com.emigm.price.rate.domain.value_objects.date_range.DateRangeDate;

import java.time.LocalDateTime;

public class RateSearcherQuery {

    private Integer productId;
    private LocalDateTime date;


    public RateSearcherQuery(Integer productId, LocalDateTime date) {
        this.productId = productId;
        this.date = date;
    }

    public static RateSearcherQuery create(Integer productId, LocalDateTime date) {
        return new RateSearcherQuery(productId, date);
    }

    public RateProductId productId() {
        return new RateProductId(productId);
    }

    public DateRangeDate date() {
        return new DateRangeDate(date);
    }


    public LocalDateTime dateValue() {
        return date().value();
    }

    public Integer productIdValue() {
        return productId().value();
    }
}
