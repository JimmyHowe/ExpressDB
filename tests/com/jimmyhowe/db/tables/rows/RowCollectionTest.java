package com.jimmyhowe.db.tables.rows;

import com.jimmyhowe.db.tables.columns.ColumnCollection;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Jimmy on 09/09/2016.
 */
public class RowCollectionTest
{
    private RowCollection collection;

    @Before
    public void setUp() throws Exception
    {
        collection = new RowCollection();
    }

    @Test
    public void add() throws Exception
    {
        collection.add(new Row(new ColumnCollection()));
    }

    @Test
    public void count() throws Exception
    {
        collection.add(new Row(new ColumnCollection()));
        assertEquals(1, collection.count());

        collection.add(new Row(new ColumnCollection()));
        assertEquals(2, collection.count());
    }

}