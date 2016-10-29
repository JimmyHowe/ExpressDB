package com.jimmyhowe.db.schema;

/**
 * Table Blueprint
 */
public abstract class Blueprint
{
    /**
     * Schema Builder
     */
    protected SchemaBuilder table = new SchemaBuilder();

    /**
     * Abstract Build Method
     *
     * Used in a callback to build the table elements
     */
    public abstract void buildTable();

    /**
     * @return SQL String
     */
    public String toSql()
    {
        buildTable();

        String sql = "";

        sql += table.toSql();

        return String.format("( %s )", sql);
    }
}
