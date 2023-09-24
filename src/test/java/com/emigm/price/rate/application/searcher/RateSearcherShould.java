package com.emigm.price.rate.application.searcher;

import com.emigm.price.rate.application.RateModuleUnitTestCase;
import com.emigm.price.rate.application.RateSearcherResponse;
import com.emigm.price.rate.domain.Rate;
import com.emigm.price.rate.domain.RateMother;
import com.emigm.price.rate.domain.RateNotFound;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

public class RateSearcherShould extends RateModuleUnitTestCase {


    private RateSearcher rateSearcher;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
        rateSearcher = new RateSearcher(rateRepository);
    }


    @Test
    void should_return_a_rate() {
        Rate rate = RateMother.random();
        RateSearcherQuery query = RateSearcherQueryMother.create(rate);
        Mockito.when(rateRepository.findBetwenDateAndProductIdOrderByPriorityAsc(query.dateValue(), query.productIdValue())).thenReturn(List.of(rate));

        RateSearcherResponse rateSearcherResponse = rateSearcher.execute(query);

        Assertions.assertEquals(rate.idValue(), rateSearcherResponse.rateId());
        Assertions.assertEquals(rate.endDateValue(), rateSearcherResponse.endDate());
        Assertions.assertEquals(rate.startDateValue(), rateSearcherResponse.startDate());
        Assertions.assertEquals(rate.priceValue(), rateSearcherResponse.price());
        Assertions.assertEquals(rate.productIdValue(), rateSearcherResponse.productId());

    }


    @Test
    void should_return_a_not_found() {
        RateSearcherQuery query = RateSearcherQueryMother.random();
        Mockito.when(rateRepository.findBetwenDateAndProductIdOrderByPriorityAsc(query.dateValue(), query.productIdValue())).thenReturn(Collections.emptyList());

        Assertions.assertThrows(RateNotFound.class, () -> rateSearcher.execute(query));
    }


}
