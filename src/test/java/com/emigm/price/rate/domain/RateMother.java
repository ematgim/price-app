package com.emigm.price.rate.domain;

import com.emigm.price.rate.domain.value_objects.*;
import com.emigm.price.shared.domain.value_objects.InternalId;

public class RateMother {


    public static Rate random(){
        return new Rate(new InternalId(), RateDateRangeMother.random(), RatePriceListIdMother.random(), RateProductIdMother.random(), RatePriorityMother.random(), RatePriceMother.random(),RateCurrencyMother.euro());
    }

    public static Rate randomWithPriority(Integer priority){
        return new Rate(new InternalId(), RateDateRangeMother.random(), RatePriceListIdMother.random(), RateProductIdMother.random(), new RatePriority(priority), RatePriceMother.random(),RateCurrencyMother.euro());
    }

    public static Rate randomWithPriorityAndProductId(Integer priority,Integer productId){
        return new Rate(new InternalId(), RateDateRangeMother.random(), RatePriceListIdMother.random(), new RateProductId(productId), new RatePriority(priority), RatePriceMother.random(),RateCurrencyMother.euro());
    }

    public static Rate expired() {
        return new Rate(new InternalId(), RateDateRangeMother.expired(), RatePriceListIdMother.random(), RateProductIdMother.random(), RatePriorityMother.random(), RatePriceMother.random(),RateCurrencyMother.euro());

    }
}
