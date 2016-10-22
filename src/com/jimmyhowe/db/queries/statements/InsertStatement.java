package com.jimmyhowe.db.queries.statements;

import com.jimmyhowe.db.DB;
import com.jimmyhowe.db.connections.Connector;
import com.jimmyhowe.db.processors.PostProcessor;
import com.jimmyhowe.db.queries.QueryBuilder;
import com.jimmyhowe.support.stores.ValueStore;

/**
 * Insert Statement Object
 */
public class InsertStatement extends Statement
{
    /**
     * Insert Columns
     */
    private ValueStore columns;

    /**
     * Insert Values
     */
    private ValueStore values;

    /**
     * @param connector    Connection
     * @param queryBuilder Query builder Instance
     * @param processor    Post Processor
     */
    public InsertStatement(Connector connector, QueryBuilder queryBuilder, PostProcessor processor)
    {
        super(connector, queryBuilder, processor);
    }


    /**
     * Compiles the Statement
     */
    @Override
    String compile()
    {
        ValueStore columns = new ValueStore(this.queryBuilder.selects);

        String sql = "INSERT INTO ";

        sql = sql + this.queryBuilder.getTableName() + " ";

        sql = sql + "(" + columns.toCsv() + ") VALUES ";

        sql = sql + values.addQuotes().toWrappedInBraces();

        DB.log(sql);

        return sql;
    }

    /**
     * Add the values
     *
     * @param values Values
     *
     * @return SQL Statement
     */
    public String values(Object... values)
    {
        this.values = new ValueStore(values);

        return this.toSql();
    }
}
