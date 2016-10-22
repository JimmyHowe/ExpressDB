package com.jimmyhowe.db;

/**
 * Expression
 */
public class Expression
{
    private String value;

    public Expression(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }

    @Override
    public String toString()
    {
        return value;
    }
}
