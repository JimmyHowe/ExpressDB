package com.jimmyhowe.db.queries;

import com.jimmyhowe.db.Expression;
import com.jimmyhowe.db.connections.Connector;
import com.jimmyhowe.db.processors.PostProcessor;
import com.jimmyhowe.db.queries.components.*;
import com.jimmyhowe.db.queries.statements.DeleteStatement;
import com.jimmyhowe.db.queries.statements.SelectStatement;
import com.jimmyhowe.db.queries.statements.UpdateStatement;
import com.jimmyhowe.support.stores.KeyValueStore;
import com.jimmyhowe.support.stores.ObjectStore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Query Builder
 */
public class QueryBuilder
{
    /**
     * Selects
     */
    public List<String> selects = new ArrayList<>(Arrays.asList("*"));

    /**
     * Where Statements
     */
    public ObjectStore wheres = new ObjectStore();

    /**
     * Order By Components
     */
    public List<OrderBy> orderBys = new ArrayList<>();

    /**
     * Limit Component
     */
    public int limit;

    /**
     * Distinct Select
     */
    public boolean distinct = false;

    /**
     * Update SET Component
     */
    public KeyValueStore sets = new KeyValueStore();

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
        this.selects = new ArrayList<>(Arrays.asList(columns));

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
        this.selects = new ArrayList<>(Arrays.asList(columns));

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
     * Selects Builder
     *
     * @return
     */
    public QueryBuilder select()
    {
        this.selects = new ArrayList<>(Arrays.asList("*"));

        return this;
    }

    /**
     * Selects Builder
     *
     * @return
     */
    public QueryBuilder select(String... columns)
    {
        this.selects = new ArrayList<>(Arrays.asList(columns));

        return this;
    }

    /**
     * @param raw Raw SQL Statement
     *
     * @return Query Builder
     */
    public QueryBuilder select(Expression... raw)
    {
        this.selects = new ArrayList<>();

        for ( int i = 0; i < raw.length; i++ )
        {
            this.selects.add(raw[i].toString());
        }

        return this;
    }

    /**
     * @param raw Raw Expression
     *
     * @return Query Builder
     */
    public QueryBuilder addSelect(Expression raw)
    {
        this.selects.add(raw.toString());

        return this;
    }

    /**
     * Where Statement
     *
     * @param column Column name
     * @param value  Value
     *
     * @return Query Builder
     */
    public QueryBuilder where(String column, String operator, Object value)
    {
        this.wheres.put(new WhereGroup(new Where(column, operator, value)));

        return this;
    }

    /**
     * Where Statement
     *
     * @param column Column name
     * @param value  Value
     *
     * @return Query Builder
     */
    public QueryBuilder where(String column, Object value)
    {
        this.wheres.put(new WhereGroup(new Where(column, value)));

        return this;
    }

    /**
     * @param wheres Multiple Where Statements
     *
     * @return Query Builder
     */
    public QueryBuilder where(Where... wheres)
    {
        this.wheres.put(new WhereGroup(wheres));

        return this;
    }

    /**
     * Where Not Statement
     *
     * @param column Column name
     * @param value  Value
     *
     * @return Query Builder
     */
    public QueryBuilder whereNot(String column, String value)
    {
        this.wheres.put(new WhereGroup(new Where(column, "!=", value)));

        return this;
    }

    /**
     * @param column   Column Name
     * @param operator Operator
     * @param value    Value
     *
     * @return Query Builder
     */
    public QueryBuilder andWhere(String column, String operator, Object value)
    {
        this.wheres.put(new AndWhereGroup(new Where(column, operator, value)));

        return this;
    }

    /**
     * @param column Column Names
     * @param value  Value
     *
     * @return Query Builder
     */
    public QueryBuilder andWhere(String column, Object value)
    {
        this.wheres.put(new AndWhereGroup(new Where(column, value)));

        return this;
    }

    /**
     * @param wheres Where Statements
     *
     * @return Query Builder
     */
    public QueryBuilder andWhere(Where... wheres)
    {
        this.wheres.put(new AndWhereGroup(wheres));

        return this;
    }

    /**
     * @param column   Column Name
     * @param operator Operator
     * @param value    Value
     *
     * @return
     */
    public QueryBuilder orWhere(String column, String operator, Object value)
    {
        this.wheres.put(new OrWhereGroup(new Where(column, operator, value)));

        return this;
    }

    /**
     * @param column Column Name
     * @param value  Value
     *
     * @return
     */
    public QueryBuilder orWhere(String column, Object value)
    {
        this.wheres.put(new OrWhereGroup(new Where(column, value)));

        return this;
    }

    /**
     * @param wheres Where Statements
     *
     * @return Query Builder
     */
    public QueryBuilder orWhere(Where... wheres)
    {
        this.wheres.put(new OrWhereGroup(wheres));

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

    /**
     * Distinct Select
     *
     * @return QueryBuilder
     */
    public QueryBuilder distinct()
    {
        this.distinct = true;

        return this;
    }

    /**
     * @param column Column Name
     * @param value  Value
     *
     * @return QueryBuilder Instance
     */
    public QueryBuilder set(String column, java.io.Serializable value)
    {
        this.sets.add(column, value);

        return this;
    }

    /**
     * @return UpdateStatement
     */
    public String update()
    {
        return new UpdateStatement(this.connector, this, this.postProcessor).toSql();
    }

    /**
     * @return DeleteStatement
     */
    public String delete()
    {
        return new DeleteStatement(this.connector, this, this.postProcessor).toSql();
    }

    /**
     * @param limit Amount to Limit
     *
     * @return Query Builder
     */
    public QueryBuilder limit(int limit)
    {
        this.limit = limit;

        return this;
    }
}
