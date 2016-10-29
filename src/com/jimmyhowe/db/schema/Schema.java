package com.jimmyhowe.db.schema;

/**
 * Database Schema
 */
public class Schema
{
    public static String create(String table, Blueprint blueprint)
    {
        return "CREATE TABLE " + table + " " + blueprint.toSql();
    }

    public static String drop(String table)
    {
        return "DROP TABLE " + table;
    }
}
