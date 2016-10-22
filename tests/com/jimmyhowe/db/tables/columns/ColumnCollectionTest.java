package com.jimmyhowe.db.tables.columns;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Jimmy on 21/08/2016.
 */
public class ColumnCollectionTest
{
    private ColumnCollection columnCollection;

    @Before
    public void setUp() throws Exception
    {
        this.columnCollection = new ColumnCollection();

        columnCollection.add(new Column("id", "UNSIGNED INT", 1, Integer.class));
        columnCollection.add(new Column("name", "VARCHAR(30)", "Jimmy", String.class));
        columnCollection.add(new Column("email", "VARCHAR(255)", "me@jimmyhowe.com", String.class));
    }

    @Test
    public void hasField() throws Exception
    {
        assertTrue(this.columnCollection.hasField("name"));
        assertFalse(this.columnCollection.hasField("boobs"));
    }

    @Test
    public void updateField() throws Exception
    {
        this.columnCollection.updateField("name", "Jimmy Howe");

        assertEquals("Jimmy Howe", this.columnCollection.data(1).getValue());
    }

}