package com.jimmyhowe.db.tables.columns;

import java.util.Map;

/**
 * Column
 */
public class Column
{
    private Object TYPE;

    private String field;

    private String sqlType;

    private Object value;

    private Class castType;

    public Column()
    {

    }

    public Column(String field, String sqlType, Object value, Class castType)
    {
        this.field = field;
        this.sqlType = sqlType;
        this.value = value;
        this.castType = castType;
    }

    /**
     * Returns the Field Name
     */
    public String getField()
    {
        return field;
    }

    /**
     * Returns the MySQL Type
     */
    public String getSqlType()
    {
        return sqlType;
    }

    /**
     * Returns the Value
     */
    public Object getValue()
    {
        return value;
    }

    /**
     * Sets the Value
     *
     * @param value
     */
    public void setValue(Object value)
    {
        this.value = value;
    }

    /**
     * Return the Cast Type
     *
     * @return
     */
    public Class getCastType()
    {
        return castType;
    }

    public void setField(String field)
    {
        this.field = field;
    }

    public void setSqlType(String sqlType)
    {
        this.sqlType = sqlType;
    }

    public void use(Map<String, Class> TYPE)
    {
        this.TYPE = TYPE;
    }

    @Override
    public String toString()
    {
        return field + " [" + sqlType + "] = " + value;
    }
}
