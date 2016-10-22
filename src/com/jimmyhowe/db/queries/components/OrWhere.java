package com.jimmyhowe.db.queries.components;

/**
 * Created by Jimmy on 15/10/2016.
 */
public class OrWhere extends Where
{
    /**
     * Construct with all parameters
     *
     * @param column
     * @param operator
     * @param value
     */
    public OrWhere(String column, String operator, Object value)
    {
        super(column, operator, value);
    }

    /**
     * Construct with only column and value
     *
     * @param column
     * @param value
     */
    public OrWhere(String column, Object value)
    {
        super(column, value);
    }

    @Override
    public String toString()
    {
        return "OR " + super.toString();
    }
}
