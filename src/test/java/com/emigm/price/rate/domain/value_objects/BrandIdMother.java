package com.emigm.price.rate.domain.value_objects;

import com.emigm.price.rate.domain.IncorrectBrandId;
import com.emigm.price.shared.domain.mother.IntegerMother;
import com.emigm.price.shared.domain.value_objects.ValueObject;

public class BrandIdMother {

    public static BrandId random(){
        return new BrandId(IntegerMother.random());
    }
}
