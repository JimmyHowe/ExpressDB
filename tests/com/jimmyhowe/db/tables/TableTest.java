package com.jimmyhowe.db.tables;

import com.jimmyhowe.db.connections.Connector;
import com.jimmyhowe.db.connections.adapters.Adapter;
import com.jimmyhowe.db.processors.PostProcessor;
import com.jimmyhowe.db.processors.TableProcessor;
import com.jimmyhowe.db.tables.rows.Row;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Jimmy on 09/09/2016.
 */
public class TableTest
{
    @Test
    public void get() throws Exception
    {

    }

    @Test
    public void get1() throws Exception
    {

    }

    @Test
    public void first() throws Exception
    {

    }

    @Test
    public void first1() throws Exception
    {

    }

    @Test
    public void list() throws Exception
    {

    }

    @Test
    public void where() throws Exception
    {

    }

    @Test
    public void whereNot() throws Exception
    {

    }

    @Test
    public void where1() throws Exception
    {

    }

    @Test
    public void orderBy() throws Exception
    {

    }

    @Test
    public void orderBy1() throws Exception
    {

    }

    @Test
    public void orderByDesc() throws Exception
    {

    }

    @Test
    public void first2() throws Exception
    {

    }

    @Test
    public void where2() throws Exception
    {

    }

    @Test
    public void list1() throws Exception
    {

    }

    @Test
    public void list2() throws Exception
    {

    }

    private Connector connector;
    private PostProcessor postProcessor;

    @Before
    public void setUp() throws Exception
    {
        this.connector = new Connector(new Adapter("jimmyhowe_com", "root", null));
        this.postProcessor = new TableProcessor();
    }

    @After
    public void tearDown() throws Exception
    {

    }

    @Test
    public void it_can_build_a_table() throws Exception
    {
        Table usersTable = new Table("users", this.connector, this.postProcessor);

        Row row = usersTable.first();
    }
}