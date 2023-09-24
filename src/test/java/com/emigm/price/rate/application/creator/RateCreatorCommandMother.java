package com.emigm.price.rate.application.creator;

import com.emigm.price.rate.domain.value_objects.date_range.DateRangeDate;
import com.emigm.price.shared.domain.mother.DoubleMother;
import com.emigm.price.shared.domain.mother.IntegerMother;
import com.emigm.price.shared.domain.mother.LocalDateTimeMother;
import com.emigm.price.shared.domain.mother.WordMother;

import java.time.LocalDateTime;

public class RateCreatorCommandMother {

    public static RateCreatorCommand randomWithExchangedDates(){
        return new RateCreatorCommand(LocalDateTimeMother.randomFutureDate(),LocalDateTimeMother.randomPastDate(), IntegerMother.random(),IntegerMother.random(),IntegerMother.random(), DoubleMother.random(),"EUR");
    }

    public static RateCreatorCommand randomWithInvalidCurrency(){
        return new RateCreatorCommand(LocalDateTimeMother.randomPastDate(),LocalDateTimeMother.randomFutureDate(), IntegerMother.random(),IntegerMother.random(),IntegerMother.random(), DoubleMother.random(), WordMother.random());
    }


    public static RateCreatorCommand random() {
        return new RateCreatorCommand(LocalDateTimeMother.randomPastDate(),LocalDateTimeMother.randomFutureDate(), IntegerMother.random(),IntegerMother.random(),IntegerMother.random(), DoubleMother.random(),"EUR");

    }

    public static RateCreatorCommand create(LocalDateTime startDate, LocalDateTime endDate,    Integer priceListId,
                                            Integer productId,
                                            Integer priority,
                                            Double price,
                                            String currency) {
        return new RateCreatorCommand(startDate, endDate, priceListId, productId, priority, price, currency);

    }

    public static RateCreatorCommand randomWithNoProductId() {
        return new RateCreatorCommand(LocalDateTimeMother.randomPastDate(),LocalDateTimeMother.randomFutureDate(), IntegerMother.random(),null,IntegerMother.random(), DoubleMother.random(), "EUR");

    }

    public static RateCreatorCommand randomWithNoPriority() {
        return new RateCreatorCommand(LocalDateTimeMother.randomPastDate(),LocalDateTimeMother.randomFutureDate(), IntegerMother.random(),IntegerMother.random(),null, DoubleMother.random(), "EUR");

    }

    public static RateCreatorCommand randomWithNegtivePriority() {
        return new RateCreatorCommand(LocalDateTimeMother.randomPastDate(),LocalDateTimeMother.randomFutureDate(), IntegerMother.random(),IntegerMother.random(),-1, DoubleMother.random(), "EUR");

    }


    public static RateCreatorCommand randomWithNoPrice() {
        return new RateCreatorCommand(LocalDateTimeMother.randomPastDate(),LocalDateTimeMother.randomFutureDate(), IntegerMother.random(),IntegerMother.random(),IntegerMother.random(), null, "EUR");

    }

    public static RateCreatorCommand randomWithNegativePrice() {
        return new RateCreatorCommand(LocalDateTimeMother.randomPastDate(),LocalDateTimeMother.randomFutureDate(), IntegerMother.random(),IntegerMother.random(),IntegerMother.random(), -1.00, "EUR");

    }

    public static RateCreatorCommand randomWithNoPriceListId() {
        return new RateCreatorCommand(LocalDateTimeMother.randomPastDate(),LocalDateTimeMother.randomFutureDate(), null,IntegerMother.random(),IntegerMother.random(), DoubleMother.random(), "EUR");

    }
}
