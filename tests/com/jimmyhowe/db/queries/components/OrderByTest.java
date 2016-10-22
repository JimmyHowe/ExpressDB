package com.jimmyhowe.db.queries.components;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Jimmy on 09/09/2016.
 */
public class OrderByTest
{
    @Test
    public void it_can() throws Exception
    {
        assertEquals("ORDER BY id DESC", new OrderBy("id", "DESC").toString());
    }

}