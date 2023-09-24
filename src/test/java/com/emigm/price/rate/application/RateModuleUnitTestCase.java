package com.emigm.price.rate.application;

import com.emigm.price.rate.domain.RateRepository;
import com.emigm.price.shared.infrastructure.UnitTestCase;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;

import static org.mockito.Mockito.mock;

public class RateModuleUnitTestCase extends UnitTestCase {

    protected  RateRepository rateRepository;


    @Override
    @BeforeEach
    public void setUp() {
        rateRepository = mock(RateRepository.class);
    }
}
