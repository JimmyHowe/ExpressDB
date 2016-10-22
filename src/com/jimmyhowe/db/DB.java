package com.jimmyhowe.db;

import com.jimmyhowe.db.connections.Connector;
import com.jimmyhowe.db.connections.adapters.Adapter;
import com.jimmyhowe.db.processors.PostProcessor;
import com.jimmyhowe.db.processors.TableProcessor;
import com.jimmyhowe.db.queries.QueryBuilder;
import com.jimmyhowe.db.tables.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * DB Facade
 */
public class DB
{
    /**
     * DB Connection
     */
    private static Connector connector;

    /**
     * SQL Adapter
     */
    private static Adapter adapter;

    /**
     * Post Processor
     */
    private static PostProcessor postProcessor = new TableProcessor();

    /**
     * SQL Log
     */
    private static List<String> log = new ArrayList<>();

    /**
     * Set the Connection Adapter
     *
     * @param adapter Adapter
     */
    public static void connectWith(Adapter adapter)
    {
        DB.adapter = adapter;

        connector = new Connector(DB.adapter);
    }

    /**
     * Table Object
     *
     * @param table Table name
     *
     * @return Table
     */
    public static Table table(String table)
    {
        return new Table(table, new Connector(adapter), postProcessor);
    }

    /**
     * Returns Query builder object
     *
     * @param table Table name
     *
     * @return Query Builder
     */
    public static QueryBuilder query(String table)
    {
        return new QueryBuilder(table);
    }

    /**
     * Return the Connector
     *
     * @return Connection
     */
    public static Connector getConnector()
    {
        return connector;
    }

    /**
     * Return Post Processor
     *
     * @return Post Processor
     */
    public static PostProcessor getPostProcessor()
    {
        return postProcessor;
    }

    /**
     * Set the Post Processor
     *
     * @param postProcessor Post Processor
     */
    public static void setPostProcessor(PostProcessor postProcessor)
    {
        DB.postProcessor = postProcessor;
    }

    /**
     * Log a query
     *
     * @param message Log Message
     */
    public static void log(String message)
    {
        log.add(message);
    }

    /**
     * Return the query log
     *
     * @return The query log
     */
    public static List<String> queryLog()
    {
        return log;
    }

    /**
     * @param sql SQL String
     *
     * @return Raw SQL Expression
     */
    public static Expression raw(String sql)
    {
        return connector.raw(sql);
    }
}
