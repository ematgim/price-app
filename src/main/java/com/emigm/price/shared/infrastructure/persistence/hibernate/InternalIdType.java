package com.emigm.price.shared.infrastructure.persistence.hibernate;


import com.emigm.price.shared.domain.value_objects.InternalId;
import org.hibernate.id.ResultSetIdentifierConsumer;
import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.descriptor.sql.BigIntTypeDescriptor;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InternalIdType extends AbstractSingleColumnStandardBasicType<InternalId> implements ResultSetIdentifierConsumer {
    public InternalIdType() {
        super(BigIntTypeDescriptor.INSTANCE, InternalIdTypeDescriptor.INSTANCE);
    }

    @Override
    public String getName() {
        return getJavaTypeDescriptor().getJavaType().getSimpleName();
    }

    @Override
    public Serializable consumeIdentifier(ResultSet resultSet) {
        try {
            long id = resultSet.getLong(1);
            return getJavaTypeDescriptor().wrap(id, null);
        } catch (SQLException ex) {
            throw new IllegalStateException("Could not extract ID from ResultSet", ex);
        }
    }
}
