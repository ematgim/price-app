package com.emigm.price.shared.domain.mother;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.TimeUnit;

public class LocalDateTimeMother {


    public static LocalDateTime randomPastDate(){

        return MotherCreator.random().date().past(1, TimeUnit.DAYS).toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public static LocalDateTime randomFutureDate(){

        return MotherCreator.random().date().future(1,TimeUnit.DAYS).toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

}
