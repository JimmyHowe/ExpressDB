package com.jimmyhowe.db.tables.columns;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Jimmy on 21/08/2016.
 */
public class ColumnsTest
{
    private Columns columns;

    @Before
    public void setUp() throws Exception
    {
        this.columns = new Columns();

        columns.add(new Column("id", "UNSIGNED INT", 1, Integer.class));
        columns.add(new Column("name", "VARCHAR(30)", "Jimmy", String.class));
        columns.add(new Column("email", "VARCHAR(255)", "me@jimmyhowe.com", String.class));
    }

    @Test
    public void hasField() throws Exception
    {
        assertTrue(this.columns.hasField("name"));
        assertFalse(this.columns.hasField("boobs"));
    }

    @Test
    public void updateField() throws Exception
    {
        this.columns.updateField("name", "Jimmy Howe");

        assertEquals("Jimmy Howe", this.columns.data(1).getValue());
    }

}