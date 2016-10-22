package com.jimmyhowe.db.queries.statements;

import com.jimmyhowe.db.connections.Connector;
import com.jimmyhowe.db.connections.adapters.Adapter;
import com.jimmyhowe.db.processors.TableProcessor;
import com.jimmyhowe.db.queries.QueryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Jimmy on 27/09/2016.
 */
public class InsertStatementTest
{
    private InsertStatement insertStatement;

    @Before
    public void setUp() throws Exception
    {
        this.insertStatement = new InsertStatement(
                new Connector(new Adapter("jimmyhowe_com")),
                new QueryBuilder("users"),
                new TableProcessor()
        );
    }

    @After
    public void tearDown() throws Exception
    {

    }

    @Test
    public void compile() throws Exception
    {
//        this.insertStatement.insert().values();
    }

    @Test
    public void toSql() throws Exception
    {

    }
}