package com.jimmyhowe.db.queries.components;

import com.jimmyhowe.support.Str;
import com.jimmyhowe.support.stores.ObjectStore;

import java.util.ArrayList;
import java.util.List;

public class WhereGroup extends ObjectStore
{
    public WhereGroup()
    {

    }

    public WhereGroup(Where where)
    {
        this.data.add(where);
    }

    public WhereGroup(Where[] wheres)
    {
        for ( int i = 0; i < wheres.length; i++ )
        {
            this.data.add(wheres[i]);
        }
    }

    @Override
    public String toString()
    {
        List<String> list = new ArrayList<>();

        for ( int i = 0; i < this.data.size(); i++ )
        {
            list.add(this.data.get(i).toString());
        }

        return list.size() == 1 ? Str.toSpaceSeparated(list) : "( " + Str.toSpaceSeparated(list) + " )";
    }
}
