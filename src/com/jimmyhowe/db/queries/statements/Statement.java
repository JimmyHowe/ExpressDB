package com.jimmyhowe.db.queries.statements;

import com.jimmyhowe.db.connections.Connector;
import com.jimmyhowe.db.processors.PostProcessor;
import com.jimmyhowe.db.queries.QueryBuilder;
import com.jimmyhowe.support.stores.ObjectStore;

/**
 * Abstract MySQL Statement
 */
abstract class Statement
{
    /**
     * Connection
     */
    Connector connector;

    /**
     * Query Builder Object
     */
    QueryBuilder queryBuilder;

    /**
     * Post Processor
     */
    PostProcessor processor;

    /**
     * SQL String
     */
    protected String sql = "";

    /**
     * @param connector    The DB Connection
     * @param queryBuilder The Query Builder
     * @param processor    The Post Processor
     */
    public Statement(Connector connector, QueryBuilder queryBuilder, PostProcessor processor)
    {
        this.connector = connector;
        this.queryBuilder = queryBuilder;
        this.processor = processor;
    }

    /**
     * Returns the SQL
     *
     * @return SQL String
     */
    public String toSql()
    {
        return this.compile();
    }

    /**
     * Compiles the Statement
     *
     * @return SQL String
     */
    abstract String compile();

    protected String buildWheres()
    {
        String whereStatements = "";

        ObjectStore whereGroups = this.queryBuilder.wheres;

        if ( ! whereGroups.isEmpty() )
        {
            whereStatements = "WHERE ";

            for ( int i = 0; i < whereGroups.count(); i++ )
            {
                whereStatements += pad(whereGroups.data(i).toString());
            }
        }

        return whereStatements;
    }

    protected String pad(String statement)
    {
        return statement != "" ? statement.trim() + " " : "";
    }

    protected String buildLimit()
    {
        if ( this.queryBuilder.limit > 0 )
        {
            return "LIMIT " + this.queryBuilder.limit;
        }

        return "";
    }
}
