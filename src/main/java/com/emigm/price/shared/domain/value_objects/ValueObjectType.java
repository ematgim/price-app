package com.emigm.price.shared.domain.value_objects;

public interface ValueObjectType<T> {

    T value();

    void value(T value);

}
