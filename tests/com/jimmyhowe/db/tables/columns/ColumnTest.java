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
        this.column = new Column("id", "INT", 1, Integer.class);
        this.column.isUnsigned(true);
        this.column.isAutoIncrement(true);
        this.column.isPrimaryKey(true);
    }

    @Test
    public void getField() throws Exception
    {
        assertEquals("id", this.column.getField());
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

    @Test
    public void it_can_return_to_sql_string() throws Exception
    {
        assertEquals("id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY", this.column.toSql());
    }
}