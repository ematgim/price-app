package com.emigm.price.shared.domain.criteria.specification;

import com.emigm.price.shared.domain.criteria.*;
import com.emigm.price.shared.domain.value_objects.Identifier;

import java.util.Collections;

public final class CriteriaFilterById extends Criteria {

    public CriteriaFilterById(Identifier id) {
        super(new Filters(Collections
                .singletonList(new Filter(new FilterField("id"), FilterOperator.EQUAL, new FilterValue(id.value())))));
    }

}
