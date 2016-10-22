package com.jimmyhowe.db.queries.components;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Jimmy on 21/10/2016.
 */
public class WhereGroupTest
{
    private WhereGroup whereGroup;

    @Before
    public void setUp() throws Exception
    {
        this.whereGroup = new WhereGroup();
    }

    @Test
    public void it_can_handle_one_where() throws Exception
    {
        this.whereGroup.put(new Where("id", 1));

        assertEquals("id = '1'", whereGroup.toString());
    }

    @Test
    public void it_can_handle_multiple_wheres() throws Exception
    {
        this.whereGroup.put(new Where("id", 1));
        this.whereGroup.put(new AndWhere("name", "Jimmy"));

        assertEquals("( id = '1' AND name = 'Jimmy' )", whereGroup.toString());
    }
}