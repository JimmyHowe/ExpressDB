package com.jimmyhowe.db.tables.columns;

import com.jimmyhowe.db.contracts.Sqlable;

import java.util.Map;

/**
 * Column
 */
public class Column implements Sqlable
{
    private Object TYPE;

    private String field;

    private String sqlType;

    private Object value;

    private Class castType;

    private boolean isUnsigned;

    private boolean isAutoIncrement;

    private boolean primaryKey;

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

    public void setField(String field)
    {
        this.field = field;
    }

    /**
     * Returns the MySQL Type
     */
    public String getSqlType()
    {
        return sqlType;
    }

    public void setSqlType(String sqlType)
    {
        this.sqlType = sqlType;
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
     * @param value The Column Value
     */
    public void setValue(Object value)
    {
        this.value = value;
    }

    /**
     * Return the Cast Type
     */
    public Class getCastType()
    {
        return castType;
    }

    public void use(Map<String, Class> TYPE)
    {
        this.TYPE = TYPE;
    }

    public void isUnsigned(boolean isUnsigned)
    {
        this.isUnsigned = isUnsigned;
    }

    public void isAutoIncrement(boolean isAutoIncrement)
    {
        this.isAutoIncrement = isAutoIncrement;
    }

    public void isPrimaryKey(boolean primaryKey)
    {
        this.primaryKey = primaryKey;
    }

    public boolean isUnsigned()
    {
        return isUnsigned;
    }

    public boolean isAutoIncrement()
    {
        return isAutoIncrement;
    }

    public boolean isPrimaryKey()
    {
        return primaryKey;
    }

    /**
     * @return SQL Statement / Fragment
     */
    @Override
    public String toSql()
    {
        String sql = this.getField() + " " + this.getSqlType();

        if(this.isUnsigned)
        {
            sql += " " + "UNSIGNED";
        }

        if(this.isAutoIncrement)
        {
            sql += " " + "AUTO_INCREMENT";
        }

        if(this.isUnsigned)
        {
            sql += " " + "PRIMARY KEY";
        }

        return sql;
    }

    @Override
    public String toString()
    {
        return field + " [" + sqlType + "] = " + value;
    }
}
