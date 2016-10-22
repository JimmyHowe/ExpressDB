package com.jimmyhowe.db.tables.rows;

import com.jimmyhowe.db.tables.columns.Columns;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Jimmy on 09/09/2016.
 */
public class RowsTest
{
    private Rows collection;

    @Before
    public void setUp() throws Exception
    {
        collection = new Rows();
    }

    @Test
    public void add() throws Exception
    {
        collection.add(new Row(new Columns()));
    }

    @Test
    public void count() throws Exception
    {
        collection.add(new Row(new Columns()));
        assertEquals(1, collection.count());

        collection.add(new Row(new Columns()));
        assertEquals(2, collection.count());
    }

}