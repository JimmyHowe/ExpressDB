package com.jimmyhowe.db.queries.statements;

import com.jimmyhowe.db.connections.Connector;
import com.jimmyhowe.db.processors.PostProcessor;
import com.jimmyhowe.db.queries.QueryBuilder;
import com.jimmyhowe.support.stores.ValueStore;

/**
 * The Select Statement Object
 */
@SuppressWarnings("unchecked")
public class SelectStatement extends Statement
{
    private ValueStore columns = new ValueStore();

    /**
     * @param connector
     * @param queryBuilder
     * @param processor
     */
    public SelectStatement(Connector connector, QueryBuilder queryBuilder, PostProcessor processor)
    {
        super(connector, queryBuilder, processor);

        for ( String column : this.queryBuilder.selects )
        {
            this.columns.add(column);
        }
    }

    /**
     * Gets all records from the select statement
     */
    public <T> T get()
    {
        return (T) this.processor.collection(this.connector.run(this.toSql()));
    }

    /**
     * Gets the single row of the select statement
     *
     * @param <T>
     */
    public <T> T first()
    {
        return (T) this.processor.single(this.connector.run(this.toSql()));
    }

    /**
     * Compiles the SQL
     */
    String compile()
    {
        String sql = "SELECT ";

        sql = sql + this.columns.toCsv();

        sql = sql + " FROM " + this.queryBuilder.getTableName();

        if ( ! this.queryBuilder.wheres.isEmpty() )
        {
            sql += " " + this.queryBuilder.wheres.get(0).toString();
        }

        if ( ! this.queryBuilder.orderBys.isEmpty() )
        {
            sql += " " + this.queryBuilder.orderBys.get(0).toString();
        }

        if ( this.queryBuilder.limit > 0 )
        {
            sql += " LIMIT " + this.queryBuilder.limit;
        }

//        DB.log(sql);

        return sql;
    }
}
