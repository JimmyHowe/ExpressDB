package com.jimmyhowe.db.schema;

import com.jimmyhowe.db.contracts.Sqlable;
import com.jimmyhowe.db.tables.columns.Column;
import com.jimmyhowe.support.Str;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jimmy on 29/10/2016.
 */
public class SchemaBuilder implements Sqlable
{
    private List<Column> columns = new ArrayList<>();

    private Column currentColumn;

    /**
     * Create a new auto-incrementing integer (4-byte) column on the table.
     *
     * @param name Name of the column
     */
    public void increments(String name)
    {
        this.unsignedInteger(name, true);
    }

    private void unsignedInteger(String name, boolean autoIncrements)
    {
        this.integer(name, autoIncrements, true);
    }

    private void integer(String name, boolean autoIncrements, boolean unsigned)
    {
        Column column = new Column(name, "INT", null, Integer.class);

        column.isAutoIncrement(autoIncrements);
        column.isUnsigned(unsigned);

        this.columns.add(column);
    }

    public void string(String name, int length)
    {
        this.columns.add(new Column(name, "VARCHAR(" + length + ")", null, String.class));
    }

    public void string(String name)
    {
        this.string(name, 255);
    }

    /**
     * @return SQL Statement / Fragment
     */
    @Override
    public String toSql()
    {
        List<String> columns = this.getColumnsAsString(this.columns);

        return Str.toCsv(columns);
    }

    private List<String> getColumnsAsString(List<Column> columns)
    {
        List<String> strings = new ArrayList<>();

        for ( int i = 0; i < columns.size(); i++ )
        {
            strings.add(columns.get(i).toSql());
        }

        return strings;
    }
}
