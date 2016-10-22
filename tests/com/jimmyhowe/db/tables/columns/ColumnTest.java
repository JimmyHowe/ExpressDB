package com.jimmyhowe.db.tables.columns;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Jimmy on 19/08/2016.
 */
public class ColumnTest
{
    private final Column column;

    public ColumnTest()
    {
        this.column = new Column("test_field", "INT", 1, Integer.class);
    }

    @Test
    public void getField() throws Exception
    {
        assertEquals("test_field", this.column.getField());
    }

    @Test
    public void getType() throws Exception
    {
        assertEquals("INT", this.column.getSqlType());

    }

    @Test
    public void getValue() throws Exception
    {
        assertEquals(1, this.column.getValue());
    }
}