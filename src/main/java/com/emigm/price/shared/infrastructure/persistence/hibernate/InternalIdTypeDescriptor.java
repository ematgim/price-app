package com.emigm.price.shared.infrastructure.persistence.hibernate;

import com.emigm.price.shared.domain.value_objects.InternalId;
import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.AbstractTypeDescriptor;
import org.hibernate.type.descriptor.java.LongTypeDescriptor;

import java.math.BigDecimal;
import java.math.BigInteger;

public class InternalIdTypeDescriptor extends AbstractTypeDescriptor<InternalId> {

    public static final InternalIdTypeDescriptor INSTANCE = new InternalIdTypeDescriptor();

    public InternalIdTypeDescriptor() {
        super(InternalId.class);
    }

    @Override
    public InternalId fromString(String string) {
        return new InternalId(LongTypeDescriptor.INSTANCE.fromString(string));
    }

    @Override
    public <X> X unwrap(InternalId value, Class<X> type, WrapperOptions options) {
        if (value == null) {
            return null;
        }
        if (Long.class.isAssignableFrom(type)) {
            return (X) value.value();
        }
        if (Byte.class.isAssignableFrom(type)) {
            return (X) Byte.valueOf(value.value().byteValue());
        }
        if (Short.class.isAssignableFrom(type)) {
            return (X) Short.valueOf(value.value().shortValue());
        }
        if (Integer.class.isAssignableFrom(type)) {
            return (X) Integer.valueOf(value.value().intValue());
        }
        if (Double.class.isAssignableFrom(type)) {
            return (X) Double.valueOf(value.value().doubleValue());
        }
        if (Float.class.isAssignableFrom(type)) {
            return (X) Float.valueOf(value.value().floatValue());
        }
        if (BigInteger.class.isAssignableFrom(type)) {
            return (X) BigInteger.valueOf(value.value());
        }
        if (BigDecimal.class.isAssignableFrom(type)) {
            return (X) BigDecimal.valueOf(value.value());
        }
        if (String.class.isAssignableFrom(type)) {
            return (X) value.toString();
        }
        throw unknownUnwrap(type);
    }

    @Override
    public <X> InternalId wrap(X value, WrapperOptions options) {
        if (value == null) {
            return null;
        }
        if (Long.class.isInstance(value)) {
            return new InternalId((Long) value);
        }
        if (Number.class.isInstance(value)) {
            return new InternalId(((Number) value).longValue());
        } else if (String.class.isInstance(value)) {
            return new InternalId(Long.valueOf(((String) value)));
        }
        throw unknownWrap(value.getClass());
    }
}
