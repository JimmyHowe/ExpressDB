package com.jimmyhowe.db.queries.statements;

import com.jimmyhowe.db.connections.Connector;
import com.jimmyhowe.db.processors.PostProcessor;
import com.jimmyhowe.db.queries.QueryBuilder;
import com.jimmyhowe.support.Str;

/**
 * Update Statement Object
 */
public class UpdateStatement extends Statement
{
    /**
     * @param connector    The DB Connection
     * @param queryBuilder The Query Builder
     * @param processor    The Post Processor
     */
    public UpdateStatement(Connector connector, QueryBuilder queryBuilder, PostProcessor processor)
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
        String sql = "UPDATE ";

        sql += queryBuilder.getTableName() + " ";

        sql += "SET " + pad(Str.toCsv(queryBuilder.sets.getAllWithFormat("$k = '$v'")));

        sql += buildWheres();

        return sql.trim();
    }
}
