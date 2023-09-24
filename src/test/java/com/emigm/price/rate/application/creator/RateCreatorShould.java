package com.emigm.price.rate.application.creator;

import com.emigm.price.rate.application.RateModuleUnitTestCase;
import com.emigm.price.rate.domain.InvalidCurrencyError;
import com.emigm.price.rate.domain.InvalidDateRange;
import com.emigm.price.rate.domain.Rate;
import com.emigm.price.rate.domain.RateMother;
import com.emigm.price.shared.domain.InvalidNullValue;
import com.emigm.price.shared.domain.InvalidValueError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class RateCreatorShould extends RateModuleUnitTestCase {


    private RateCreator rateCreator;


    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
        rateCreator = new RateCreator(rateRepository);
    }


    @Test
    void should_create_rate() {
        Rate rate = RateMother.random();

        RateCreatorCommand command = RateCreatorCommandMother.create(rate.startDateValue(), rate.endDateValue(), rate.priceListIdValue(), rate.productIdValue(), rate.priorityValue(), rate.priceValue(), rate.currencyValue());

        rateCreator.create(command);

        Mockito.verify(rateRepository).save(rate);
    }

    @Test
    void should_not_create_rate_invalid_date_range() {
        RateCreatorCommand command = RateCreatorCommandMother.randomWithExchangedDates();

        Assertions.assertThrows(InvalidDateRange.class, () -> rateCreator.create(command));

    }


    @Test
    void should_not_create_with_invalid_currency() {
        RateCreatorCommand command = RateCreatorCommandMother.randomWithInvalidCurrency();

        Assertions.assertThrows(InvalidCurrencyError.class, () -> rateCreator.create(command));

    }


    @Test
    void should_not_create_with_no_product_id() {
        RateCreatorCommand command = RateCreatorCommandMother.randomWithNoProductId();

        Assertions.assertThrows(InvalidNullValue.class, () -> rateCreator.create(command));

    }

    @Test
    void should_not_create_with_no_priority() {
        RateCreatorCommand command = RateCreatorCommandMother.randomWithNoPriority();

        Assertions.assertThrows(InvalidNullValue.class, () -> rateCreator.create(command));

    }


    @Test
    void should_not_create_with_negative_priority() {
        RateCreatorCommand command = RateCreatorCommandMother.randomWithNegtivePriority();

        Assertions.assertThrows(InvalidValueError.class, () -> rateCreator.create(command));

    }

    @Test
    void should_not_create_with_no_price() {
        RateCreatorCommand command = RateCreatorCommandMother.randomWithNoPrice();

        Assertions.assertThrows(InvalidNullValue.class, () -> rateCreator.create(command));

    }


    @Test
    void should_not_create_with_negative_price() {
        RateCreatorCommand command = RateCreatorCommandMother.randomWithNegativePrice();

        Assertions.assertThrows(InvalidValueError.class, () -> rateCreator.create(command));

    }


    @Test
    void should_not_create_with_no_price_list() {
        RateCreatorCommand command = RateCreatorCommandMother.randomWithNoPriceListId();

        Assertions.assertThrows(InvalidNullValue.class, () -> rateCreator.create(command));

    }


}
