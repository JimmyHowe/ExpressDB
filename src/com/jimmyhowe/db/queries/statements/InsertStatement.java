package com.jimmyhowe.db.queries.statements;

import com.jimmyhowe.db.connections.Connector;
import com.jimmyhowe.db.processors.PostProcessor;
import com.jimmyhowe.db.queries.QueryBuilder;
import com.jimmyhowe.support.stores.ValueStore;

/**
 * Created by Jimmy on 27/09/2016.
 */
public class InsertStatement extends Statement
{
    private ValueStore columns;

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

//        DB.log(sql);

        return sql;
    }

    public Object values(Object... values)
    {
        this.values = new ValueStore(values);

        return this.toSql();
    }
}
