package com.emigm.price.rate.infrastructure;

import com.emigm.price.rate.domain.Rate;
import com.emigm.price.rate.domain.RateMother;
import com.emigm.price.shared.domain.criteria.Criteria;
import com.emigm.price.shared.domain.criteria.Filters;
import com.emigm.price.shared.domain.criteria.Order;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

public class RatePostControllerShould extends RateApplicationTestCase{


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
    void should_create_rate() throws Exception {


        assertRequestWithBody("POST", "/api/rate/create",buildValidRateBody("2024-12-09T18:25:58+01:00[Europe/Paris]", "2021-12-09T18:25:58.6037597+01:00[Europe/Paris]","1","1","1","10.00","EUR"),200);


    }
}
