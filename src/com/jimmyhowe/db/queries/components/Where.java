package com.jimmyhowe.db.queries.components;

/**
 * The WHERE Statement Method Object
 */
public class Where
{
    String column;
    String operator = "=";
    Object value;

    public Where(String column, Object value)
    {
        this.column = column;
        this.value = value;
    }

    public Where(String column, String operator, Object value)
    {
        this.column = column;
        this.operator = operator;
        this.value = value;
    }

    @Override
    public String toString()
    {
        return String.format("WHERE %s %s '%s'", this.column, this.operator, this.value.toString());
    }
}