package com.jimmyhowe.db.queries.components;

/**
 * Container for OrderBy statement
 */
public class OrderBy
{
    private final String column;

    private final String direction;

    public OrderBy(String column, String direction)
    {
        this.column = column;
        this.direction = direction;
    }

    @Override
    public String toString()
    {
        return String.format("ORDER BY %s %s", column, direction);
    }
}
