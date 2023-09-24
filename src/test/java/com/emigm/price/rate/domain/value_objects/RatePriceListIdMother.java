package com.emigm.price.rate.domain.value_objects;

import com.emigm.price.shared.domain.InvalidNullValue;
import com.emigm.price.shared.domain.mother.IntegerMother;
import com.emigm.price.shared.domain.value_objects.ValueObject;

import java.util.Objects;

public class RatePriceListIdMother  {


    public static RatePriceListId random() {

        return new RatePriceListId(IntegerMother.random());
    }

}
