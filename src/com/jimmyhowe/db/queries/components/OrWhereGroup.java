package com.jimmyhowe.db.queries.components;

/**
 * Created by Jimmy on 21/10/2016.
 */
public class OrWhereGroup extends WhereGroup
{
    public OrWhereGroup()
    {
    }

    public OrWhereGroup(Where where)
    {
        super(where);
    }

    public OrWhereGroup(Where[] wheres)
    {
        super(wheres);
    }

    @Override
    public String toString()
    {
        return "OR " + super.toString();
    }
}
