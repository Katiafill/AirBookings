package ru.katiafill.airbookings.models;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;
import org.postgresql.geometric.PGpoint;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Objects;

public class PGpointType implements UserType {
    @Override
    public int[] sqlTypes() {
        return new int[]
                {
                        Types.OTHER
                };
    }

    @Override
    public Class<PGpoint> returnedClass() {
        return PGpoint.class;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        return Objects.equals(x, y);
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        return x.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] names, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws SQLException {
        if (names.length != 1)
            throw new IllegalArgumentException("names.length != 1, names = " + names);

        PGpoint value = (PGpoint) resultSet.getObject(names[0]);

        return value;
    }


    @Override
    public void nullSafeSet(PreparedStatement statement, Object value, int index, SharedSessionContractImplementor sharedSessionContractImplementor) throws SQLException {
        if (value == null) {
            statement.setNull(index, Types.OTHER);
        } else {
            statement.setObject(index, value, Types.OTHER);
        }
    }

    @Override
    public Object deepCopy(Object obj) {
        if (obj == null)
            return null;

        try {
            return ((PGpoint) obj).clone();
        } catch (CloneNotSupportedException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public boolean isMutable() {
        return Boolean.FALSE;
    }

    @Override
    public Serializable disassemble(Object obj) {
        return (Serializable) obj;
    }

    @Override
    public Object assemble(Serializable serializable, Object obj) {
        return serializable;
    }

    @Override
    public Object replace(Object obj, Object obj1, Object obj2) {
        return obj;
    }

}