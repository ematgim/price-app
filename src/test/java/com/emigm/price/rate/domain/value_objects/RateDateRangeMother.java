package com.emigm.price.rate.domain.value_objects;

import com.emigm.price.rate.domain.value_objects.date_range.DateRangeDate;
import com.emigm.price.rate.domain.value_objects.date_range.DateRangeDateMother;
import com.emigm.price.shared.domain.mother.LocalDateTimeMother;
import net.bytebuddy.asm.Advice;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class RateDateRangeMother {

    public static RateDateRange random(){
        return new RateDateRange(DateRangeDateMother.randomPastDate(),DateRangeDateMother.randomFutureDate());
    }

    public static RateDateRange expired() {


        LocalDateTime date = LocalDateTimeMother.randomPastDate();
        return new RateDateRange(new DateRangeDate(date.minus(3, ChronoUnit.DAYS)),new DateRangeDate(date));

    }
}
