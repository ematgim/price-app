package com.emigm.price.rate.application.searcher;

import com.emigm.price.rate.domain.Rate;
import com.emigm.price.shared.domain.mother.IntegerMother;
import com.emigm.price.shared.domain.mother.LocalDateTimeMother;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public class RateSearcherQueryMother {


    public static RateSearcherQuery create(Rate rate){

        return new RateSearcherQuery(rate.productIdValue(),rate.endDateValue().minus(2, ChronoUnit.MINUTES));
    }

    public static RateSearcherQuery random() {

        return new RateSearcherQuery(IntegerMother.random(),LocalDateTimeMother.randomPastDate());
    }
}
