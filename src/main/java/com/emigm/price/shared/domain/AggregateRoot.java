package com.emigm.price.shared.domain;

import java.io.Serializable;
import java.util.Map;

public interface AggregateRoot {

    Map<String, Serializable> toPrimitives();

}
