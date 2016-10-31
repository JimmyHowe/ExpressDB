package com.jimmyhowe.db.tables;

import com.jimmyhowe.db.DB;
import com.jimmyhowe.db.connections.Connector;
import com.jimmyhowe.db.processors.PostProcessor;
import com.jimmyhowe.db.processors.TableProcessor;
import com.jimmyhowe.db.queries.QueryBuilder;
import com.jimmyhowe.db.queries.components.OrderBy;
import com.jimmyhowe.db.queries.components.Where;
import com.jimmyhowe.db.queries.components.WhereGroup;
import com.jimmyhowe.db.queries.statements.SelectStatement;
import com.jimmyhowe.db.tables.rows.Row;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Table
 */
public class Table
{
    /**
     * Database Connection
     */
    private final Connector connector;

    /**
     * Query Builder Object
     */
    private final QueryBuilder queryBuilder;

    /**
     * Post Processor
     */
    private PostProcessor postProcessor;

    /**
     * Construct Table
     *
     * @param table         Table name
     * @param connector     Connector
     * @param postProcessor Post Processor
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
     * Get Collection
     *
     * @param <T> Return type
     *
     * @return T
     */
    public <T> T get()
    {
        return new SelectStatement(this.connector, this.queryBuilder, this.postProcessor).get();
    }

    /**
     * Get with Columns
     *
     * @param columns Column Names
     * @param <T>     Return Type
     *
     * @return T
     */
    public <T> T get(String... columns)
    {
        this.queryBuilder.selects = new ArrayList<>(Arrays.asList(columns));

        return this.get();
    }

    /**
     * Get the First Record
     *
     * @param <T> Return type
     *
     * @return T
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
     * @param <T>     Return type
     *
     * @return T
     */
    public <T> T first(String... columns)
    {
        this.queryBuilder.selects = new ArrayList<>(Arrays.asList(columns));

        return this.first();
    }

    /**
     * List with primary key
     *
     * @param primaryKey Primary key
     * @param column     Column name
     * @param <T>        Return Type
     *
     * @return T
     */
    public <T> T list(String primaryKey, String column)
    {
        return this.get(primaryKey, column);
    }

    /**
     * List
     *
     * @param column Column Name
     * @param <T>    Return type
     *
     * @return T
     */
    public <T> T list(String column)
    {
        return this.list("id", column);
    }

    /**
     * Where Statement
     *
     * @param column Column name
     * @param value  Value
     *
     * @return Table
     */
    public Table where(String column, Object value)
    {
        this.queryBuilder.wheres.put(new WhereGroup(new Where(column, value)));

        return this;
    }

    /**
     * Where Not Statement
     *
     * @param column Column name
     * @param value  Value
     *
     * @return Table
     */
    public Table whereNot(String column, Object value)
    {
        this.queryBuilder.wheres.put(new WhereGroup(new Where(column, "!=", value)));

        return this;
    }

    /**
     * Where Statement
     *
     * @param column   Column name
     * @param operator Operator
     * @param value    Value
     *
     * @return Table
     */
    public Table where(String column, String operator, Object value)
    {
        this.queryBuilder.wheres.put(new WhereGroup(new Where(column, operator, value)));

        return this;
    }

    /**
     * Order By
     *
     * @param column    Column name
     * @param direction Direction
     *
     * @return Table
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
     *
     * @return Table
     */
    public Table orderBy(String column)
    {
        return this.orderBy(column, "ASC");
    }

    /**
     * Order By Desc
     *
     * @param column Column name
     *
     * @return Table
     */
    public Table orderByDesc(String column)
    {
        return this.orderBy(column, "DESC");
    }

    public boolean exists()
    {
        ResultSet results = DB.getConnector().run(
                DB.query("information_schema.tables")
                        .select("COUNT(*)")
                        .where("table_schema", connector.getAdapter().getDatabase())
                        .andWhere("table_name", queryBuilder.getTableName())
                        .first()
        );

        Row row = new TableProcessor().single(results);

        if( (long) row.first().getValue() > 0 )
        {
            return true;
        }

        return false;
    }
}
