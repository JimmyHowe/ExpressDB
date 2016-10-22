package com.jimmyhowe.db.tables;

import com.jimmyhowe.db.DB;
import com.jimmyhowe.db.connections.Connector;
import com.jimmyhowe.db.processors.PostProcessor;
import com.jimmyhowe.db.queries.QueryBuilder;
import com.jimmyhowe.db.queries.components.OrderBy;
import com.jimmyhowe.db.queries.components.Where;
import com.jimmyhowe.db.queries.statements.SelectStatement;
import com.jimmyhowe.db.tables.rows.RowCollection;

/**
 * Table
 */
public class Table
{
    private final Connector connector;
    private final QueryBuilder queryBuilder;
    private PostProcessor postProcessor;

    /**
     * Construct Table
     *
     * @param table         Table name
     * @param connector     Connector
     * @param postProcessor Post Proccessor
     */
    public Table(String table, Connector connector, PostProcessor postProcessor)
    {
        this.connector = connector;
        this.queryBuilder = new QueryBuilder(table);
        this.postProcessor = postProcessor;
    }

    /**
     * Construct Table with Defaults
     *
     * @param table Table name
     */
    public Table(String table)
    {
        this.connector = DB.getConnector();
        this.queryBuilder = new QueryBuilder(table);
        this.postProcessor = DB.getPostProcessor();
    }

    /**
     * Get
     *
     * @return Returns Results
     */
    public <T> T get()
    {
        return new SelectStatement(this.connector, this.queryBuilder, this.postProcessor).get();
    }

    /**
     * Get with Columns
     *
     * @param columns Columns to get
     */
    public <T> T get(String... columns)
    {
        this.queryBuilder.selects = columns;

        return this.get();
    }

    /**
     * Get the First Record
     */
    public <T> T first()
    {
        this.queryBuilder.limit = 1;

        return new SelectStatement(this.connector, this.queryBuilder, this.postProcessor).first();
    }

    /**
     * First with Column Names
     *
     * @param columns Column names
     */
    public String first(String... columns)
    {
        this.queryBuilder.selects = columns;

        return this.first();
    }

    /**
     * List with primary key
     *
     * @param primaryKey Primary key
     * @param column     Column name
     */
    public <T> T list(String primaryKey, String column)
    {
        return this.get(primaryKey, column);
    }

    /**
     * Return a list of ID, and Column Name
     *
     * @param column Column name
     */
    public <T> T list(String column)
    {
        return this.list("id", column);
    }

    /**
     * Where Statement
     *  @param column Column name
     * @param value  Value
     */
    public Table where(String column, Object value)
    {
        this.queryBuilder.wheres.add(new Where(column, value));

        return this;
    }

    /**
     * Where Not Statement
     *  @param column Column name
     * @param value  Value
     */
    public Table whereNot(String column, Object value)
    {
        this.queryBuilder.wheres.add(new Where(column, "!=", value));

        return this;
    }

    /**
     * Where Statement
     *  @param column Column name
     * @param value  Value
     */
    public Table where(String column, String operator, Object value)
    {
        this.queryBuilder.wheres.add(new Where(column, operator, value));

        return this;
    }

    /**
     * Order By
     *  @param column    Column name
     * @param direction Direction
     */
    public Table orderBy(String column, String direction)
    {
        this.queryBuilder.orderBys.add(new OrderBy(column, direction));

        return this;
    }

    /**
     * Order By Asc
     *
     * @param column Column name
     */
    public Table orderBy(String column)
    {
        return this.orderBy(column, "ASC");
    }

    /**
     * Order By Desc
     *
     * @param column Column name
     */
    public Table orderByDesc(String column)
    {
        return this.orderBy(column, "DESC");
    }
}
