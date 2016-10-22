package com.jimmyhowe.db.queries;

import com.jimmyhowe.db.connections.Connector;
import com.jimmyhowe.db.processors.PostProcessor;
import com.jimmyhowe.db.queries.components.OrderBy;
import com.jimmyhowe.db.queries.components.Where;
import com.jimmyhowe.db.queries.statements.SelectStatement;

import java.util.ArrayList;
import java.util.List;

/**
 * Query Builder
 */
public class QueryBuilder
{
    /**
     * Selects
     */
    public String[] selects = { "*" };
    /**
     * Where Components
     */
    public List<Where> wheres = new ArrayList<>();
    /**
     * Order By Components
     */
    public List<OrderBy> orderBys = new ArrayList<>();
    /**
     * Limit Component
     */
    public int limit;
    /**
     * Database Connector
     */
    private Connector connector;
    /**
     * Post Processor
     */
    private PostProcessor postProcessor;
    /**
     * Table Name
     */
    private String table;

    /**
     * Query Builder
     *
     * @param table Name of the table to build queries around
     */
    public QueryBuilder(String table)
    {
        this.table = table;
    }

    /**
     * Get Table Name
     *
     * @return returns the table name
     */
    public String getTableName()
    {
        return this.table;
    }

    /**
     * Get
     *
     * @return Returns SQL String
     */
    public String get()
    {
        return new SelectStatement(this.connector, this, this.postProcessor).toSql();
    }

    /**
     * Get with Columns
     *
     * @param columns Columns to get
     */
    public String get(String... columns)
    {
        this.selects = columns;

        return this.get();
    }

    /**
     * Get the First Record
     */
    public String first()
    {
        this.limit = 1;

        return this.get();
    }

    /**
     * First with Column Names
     *
     * @param columns Column names
     */
    public String first(String... columns)
    {
        this.selects = columns;

        return this.first();
    }

    /**
     * List with primary key
     *
     * @param primaryKey Primary key
     * @param column     Column name
     */
    public String list(String primaryKey, String column)
    {
        return this.get(primaryKey, column);
    }

    /**
     * Return a list of ID, and Column Name
     *
     * @param column Column name
     */
    public String list(String column)
    {
        return this.list("id", column);
    }

    /**
     * Where Statement
     *
     * @param column Column name
     * @param value  Value
     */
    public QueryBuilder where(String column, Object value)
    {
        this.wheres.add(new Where(column, value));

        return this;
    }

    /**
     * Where Not Statement
     *
     * @param column    Column name
     * @param value     Value
     */
    public QueryBuilder whereNot(String column, Object value)
    {
        this.wheres.add(new Where(column, "!=", value));

        return this;
    }

    /**
     * Where Statement
     *
     * @param column Column name
     * @param value  Value
     */
    public QueryBuilder where(String column, String operator, Object value)
    {
        this.wheres.add(new Where(column, operator, value));

        return this;
    }

    /**
     * Order By
     *
     * @param column    Column name
     * @param direction Direction
     */
    public QueryBuilder orderBy(String column, String direction)
    {
        this.orderBys.add(new OrderBy(column, direction));

        return this;
    }

    /**
     * Order By Asc
     *
     * @param column Column name
     */
    public QueryBuilder orderBy(String column)
    {
        return this.orderBy(column, "ASC");
    }

    /**
     * Order By Desc
     *
     * @param column Column name
     */
    public QueryBuilder orderByDesc(String column)
    {
        return this.orderBy(column, "DESC");
    }
}
