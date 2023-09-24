package com.emigm.price.rate.domain.value_objects;

import com.emigm.price.shared.domain.mother.DoubleMother;

public class RatePriceMother {


    public static RatePrice random(){

        return new RatePrice(DoubleMother.random());


    }

}
