package com.jimmyhowe.db.queries.components;

/**
 * Container for Where Statement
 */
public class Where
{
    String column;

    String operator = "=";

    Object value;

    /**
     * Construct with all parameters
     *
     * @param column
     * @param operator
     * @param value
     */
    public Where(String column, String operator, Object value)
    {
        this.column = column;
        this.operator = operator;
        this.value = value;
    }

    /**
     * Construct with only column and value
     *
     * @param column
     * @param value
     */
    public Where(String column, Object value)
    {
        this.column = column;
        this.value = value;
    }

    @Override
    public String toString()
    {
        return String.format("%s %s '%s'", this.column, this.operator, this.value.toString());
    }
}