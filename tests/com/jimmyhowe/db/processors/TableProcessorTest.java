package com.jimmyhowe.db.processors;

import com.jimmyhowe.db.connections.Connector;
import com.jimmyhowe.db.connections.adapters.Adapter;
import com.jimmyhowe.db.tables.rows.Row;
import com.jimmyhowe.db.tables.rows.RowCollection;
import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

/**
 * Created by Jimmy on 09/09/2016.
 */
public class TableProcessorTest
{
    private Connector connector;

    private TableProcessor tableProcessor;

    private ResultSet resultSet;

    @Before
    public void setUp() throws Exception
    {
        connector = new Connector(new Adapter("jimmyhowe_com", "root", null));

        tableProcessor = new TableProcessor();
    }

    @Test
    public void single() throws Exception
    {
        resultSet = connector.run("SELECT * FROM users LIMIT 1");

        Row first = tableProcessor.single(resultSet);

        assertThat(first, instanceOf(Row.class));
    }

    @Test
    public void collection() throws Exception
    {
        resultSet = connector.run("SELECT * FROM users");

        RowCollection rowCollection = tableProcessor.collection(resultSet);

        assertThat(rowCollection, instanceOf(RowCollection.class));
    }
}