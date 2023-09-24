package com.emigm.price.rate.domain.value_objects;

import com.emigm.price.shared.domain.mother.IntegerMother;

public class RatePriorityMother {

    public static RatePriority random(){

        return new RatePriority(IntegerMother.random());


    }
}
