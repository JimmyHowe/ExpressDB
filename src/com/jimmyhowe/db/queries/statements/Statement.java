package com.jimmyhowe.db.queries.statements;

import com.jimmyhowe.db.connections.Connector;
import com.jimmyhowe.db.processors.PostProcessor;
import com.jimmyhowe.db.queries.QueryBuilder;

/**
 * Abstract MySQL Statement
 */
abstract class Statement
{
    Connector connector;
    QueryBuilder queryBuilder;
    PostProcessor processor;

    /**
     * @param connector
     * @param queryBuilder
     * @param processor
     */
    public Statement(Connector connector, QueryBuilder queryBuilder, PostProcessor processor)
    {
        this.connector = connector;
        this.queryBuilder = queryBuilder;
        this.processor = processor;
    }

    /**
     * Returns the SQL
     */
    public String toSql()
    {
        return this.compile();
    }

    /**
     * Compiles the Statement
     */
    abstract String compile();
}
