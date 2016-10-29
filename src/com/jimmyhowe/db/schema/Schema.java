package com.jimmyhowe.db.schema;

/**
 * Database Schema
 */
public class Schema
{
    /**
     * @param table
     * @param blueprint
     *
     * @return
     */
    public static String create(String table, Blueprint blueprint)
    {
        return "CREATE TABLE " + table + " " + blueprint.toSql();
    }

    /**
     * @param table
     *
     * @return
     */
    public static String drop(String table)
    {
        return "DROP TABLE " + table;
    }
}
