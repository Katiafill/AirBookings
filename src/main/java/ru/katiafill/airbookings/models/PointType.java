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

/* Пользовательский тип для преобразования
* PostgresSQL point type в PGpoint, а его в
* уже в свой Point.
*/
public class PointType implements UserType {
    @Override
    public int[] sqlTypes() {
        return new int[]
                {
                        Types.OTHER
                };
    }

    @Override
    public Class<Point> returnedClass() {
        return Point.class;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        if (x == null && y == null)
            return true;
        else if (x == null || y == null)
            return false;
        return x.equals(y);
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

        if (value == null) {
            return null;
        } else {
            return new Point(value.x, value.y);
        }
    }


    @Override
    public void nullSafeSet(PreparedStatement statement, Object value, int index, SharedSessionContractImplementor sharedSessionContractImplementor) throws SQLException {
        if (value == null) {
            statement.setNull(index, java.sql.Types.OTHER);
        } else {
            statement.setObject(index, new PGpoint(((Point) value).getX(), ((Point) value).getY()));
        }
    }

    @Override
    public Object deepCopy(Object obj) {
        if (obj == null)
            return null;

        Point point = (Point) obj;
        return new Point(point.getX(), point.getY());
    }

    @Override
    public boolean isMutable() {
        return false;
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