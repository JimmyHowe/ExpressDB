package com.jimmyhowe.db.queries.statements;

import com.jimmyhowe.db.connections.Connector;
import com.jimmyhowe.db.connections.adapters.Adapter;
import com.jimmyhowe.db.processors.TableProcessor;
import com.jimmyhowe.db.queries.QueryBuilder;
import com.jimmyhowe.db.tables.rows.Row;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Jimmy on 23/08/2016.
 */
public class SelectStatementTest
{
    private SelectStatement selectStatement;

    @Before
    public void setUp() throws Exception
    {
        this.selectStatement = new SelectStatement(
                new Connector(new Adapter("jimmyhowe_com", "root", "")),
                new QueryBuilder("users"),
                new TableProcessor()
        );
    }

    @Test
    public void get() throws Exception
    {
        Row firstRow = this.selectStatement.first();
    }

    @Test
    public void first() throws Exception
    {

    }

    @Test
    public void toSql() throws Exception
    {

    }
}