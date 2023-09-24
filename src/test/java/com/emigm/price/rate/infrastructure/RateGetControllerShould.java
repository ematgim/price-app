package com.emigm.price.rate.infrastructure;

import com.emigm.price.rate.domain.Rate;
import com.emigm.price.shared.domain.mother.IntegerMother;
import com.emigm.price.shared.domain.mother.LocalDateTimeMother;
import com.emigm.price.shared.infrastructure.Serializer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class RateGetControllerShould extends RateApplicationTestCase{


    @Override
    @BeforeEach
    void setUp() throws Exception {
        super.setUp();

    }

    @Override
    @AfterEach
    void tearDown() {
        super.tearDown();
    }

    @Test
    void should_retrive_the_correct_rate() throws Exception {

        Integer prodId= IntegerMother.random();
        Rate desired = givenAnActiveRateWithPriorityAndProductId(1,prodId);
        givenAnActiveRateWithPriorityAndProductId(2,prodId);
        givenAnActiveRateWithPriorityAndProductId(3,prodId);
        givenAnActiveRateWithPriorityAndProductId(1,IntegerMother.random());
        givenAnExpiredDateRate();

        assertResponse(String.format("/api/rate/search?productId=%s&date=%s",prodId, Serializer.encodeLocalDateTime(desired.endDateValue().minus(1, ChronoUnit.HOURS))),200,buildRateRespose(desired));


    }

    @Test
    void should_not_found_any_rate() throws Exception {
        givenAnExpiredDateRate();
        givenAnExpiredDateRate();
        givenAnExpiredDateRate();
        givenAnExpiredDateRate();

        assertResponse(String.format("/api/rate/search?productId=%s&date=%s",IntegerMother.random(), Serializer.encodeLocalDateTime(LocalDateTimeMother.randomFutureDate())),404);

    }
}
