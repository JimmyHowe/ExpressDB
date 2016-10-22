package com.jimmyhowe.db.queries.statements;

import com.jimmyhowe.db.connections.Connector;
import com.jimmyhowe.db.processors.PostProcessor;
import com.jimmyhowe.db.queries.QueryBuilder;

/**
 * Created by Jimmy on 21/10/2016.
 */
public class DeleteStatement extends Statement
{
    /**
     * @param connector    The DB Connection
     * @param queryBuilder The Query Builder
     * @param processor    The Post Processor
     */
    public DeleteStatement(Connector connector, QueryBuilder queryBuilder, PostProcessor processor)
    {
        super(connector, queryBuilder, processor);
    }

    /**
     * Compiles the Statement
     *
     * @return SQL String
     */
    @Override
    String compile()
    {
        sql += "DELETE FROM " + queryBuilder.getTableName() + " ";

        sql += buildWheres();

        sql += pad(buildLimit());

        return sql.trim();
    }

}
