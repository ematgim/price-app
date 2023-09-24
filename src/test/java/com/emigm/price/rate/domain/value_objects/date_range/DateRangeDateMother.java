package com.emigm.price.rate.domain.value_objects.date_range;

import com.emigm.price.shared.domain.mother.LocalDateTimeMother;
import com.emigm.price.shared.domain.mother.MotherCreator;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.TimeUnit;

public class DateRangeDateMother {
    public static DateRangeDate randomPastDate(){

        return new DateRangeDate(LocalDateTimeMother.randomPastDate());
    }

    public static DateRangeDate randomFutureDate(){

        return new DateRangeDate(LocalDateTimeMother.randomFutureDate());
    }
}
