package com.jimmyhowe.db.queries.components;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Jimmy on 09/09/2016.
 */
public class WhereTest
{
    @Test
    public void it_can_build_simple_where() throws Exception
    {
        assertEquals("id = '1'", new Where("id", 1).toString());
    }

    @Test
    public void it_can_build_full_where() throws Exception
    {
        assertEquals("id > '1'", new Where("id", ">", 1).toString());
    }
}