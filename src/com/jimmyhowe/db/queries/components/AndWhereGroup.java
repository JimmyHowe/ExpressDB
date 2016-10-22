package com.jimmyhowe.db.queries.components;

/**
 * Created by Jimmy on 21/10/2016.
 */
public class AndWhereGroup extends WhereGroup
{
    public AndWhereGroup()
    {

    }

    public AndWhereGroup(Where where)
    {
        super(where);
    }

    public AndWhereGroup(Where[] wheres)
    {
        super(wheres);
    }

    @Override
    public String toString()
    {
        return "AND " + super.toString();
    }
}
