package com.jimmyhowe.db.schema;

/**
 * Table Blueprint
 */
public abstract class Blueprint
{
    protected SchemaBuilder table = new SchemaBuilder();

    public abstract void buildTable();

    public String toSql()
    {
        buildTable();

        String sql = "";

        sql += table.toSql();

        return String.format("( %s )", sql);
    }
}
