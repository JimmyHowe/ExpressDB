package com.jimmyhowe.db;

import com.jimmyhowe.db.connections.Connector;
import com.jimmyhowe.db.connections.adapters.Adapter;
import com.jimmyhowe.db.processors.PostProcessor;
import com.jimmyhowe.db.processors.TableProcessor;
import com.jimmyhowe.db.queries.QueryBuilder;
import com.jimmyhowe.db.tables.Table;

/**
 * DB Facade
 */
public class DB
{
    private static Connector connector;

    private static Adapter adapter;

    private static PostProcessor postProcessor;

    /**
     * Set the Connection Adapter
     *
     * @param adapter
     */
    public static void connectWith(Adapter adapter)
    {
        DB.adapter = adapter;
    }

    /**
     * Table Object
     *
     * @param table
     */
    public static Table table(String table)
    {
        return new Table(table, new Connector(adapter), new TableProcessor());
    }

    /**
     * Returns Query builder object
     *
     * @param table
     */
    public static QueryBuilder query(String table)
    {
        return new QueryBuilder(table);
    }

    /**
     * Return the Connector
     *
     * @return
     */
    public static Connector getConnector()
    {
        return connector;
    }

    /**
     * Get Post Processor
     *
     * @return
     */
    public static PostProcessor getPostProcessor()
    {
        return postProcessor;
    }

    /**
     * Set the Post Processor
     *
     * @param postProcessor
     */
    public static void setPostProcessor(PostProcessor postProcessor)
    {
        DB.postProcessor = postProcessor;
    }
}
