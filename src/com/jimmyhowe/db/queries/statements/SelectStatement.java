package com.jimmyhowe.db.queries.statements;

import com.jimmyhowe.db.DB;
import com.jimmyhowe.db.connections.Connector;
import com.jimmyhowe.db.processors.PostProcessor;
import com.jimmyhowe.db.queries.QueryBuilder;
import com.jimmyhowe.support.stores.ValueStore;

import java.sql.ResultSet;

/**
 * The Select Statement Object
 */
@SuppressWarnings("unchecked")
public class SelectStatement extends Statement
{
    /**
     * Select columns
     */
    private ValueStore columns = new ValueStore();

    /**
     * @param connector    The DB Connection
     * @param queryBuilder The Query Builder
     * @param processor    The Post Processor
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
     * Gets Collection of T
     *
     * @param <T> Return Type
     *
     * @return Returns Collection of Records
     */
    public <T> T get()
    {
        String query = this.toSql();
        ResultSet run = this.connector.run(query);
        return (T) this.processor.collection(run);
    }

    /**
     * Gets Single T
     *
     * @param <T> Return Type
     *
     * @return Returns Single Record
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
        sql += pad(buildDistinct());
        sql += pad(buildSelects());
        sql += pad(buildTableName());
        sql += pad(buildWheres());
        sql += pad(buildOrderBys());
        sql += pad(buildLimit());

        DB.log(sql.trim());

        return sql.trim();
    }

    private String buildDistinct()
    {
        return this.queryBuilder.distinct ? "SELECT DISTINCT" : "SELECT";
    }

    private String buildSelects()
    {
        return this.columns.toCsv();
    }

    private String buildTableName()
    {
        return "FROM " + this.queryBuilder.getTableName();
    }

    private String buildOrderBys()
    {
        String orderBys = "";

        if ( ! this.queryBuilder.orderBys.isEmpty() )
        {
            orderBys += this.queryBuilder.orderBys.get(0).toString();
        }

        return orderBys;
    }

}
