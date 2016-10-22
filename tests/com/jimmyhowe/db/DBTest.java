package com.jimmyhowe.db;

import com.jimmyhowe.db.connections.adapters.MySQLAdapter;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Jimmy on 22/10/2016.
 */
public class DBTest
{
    public DBTest()
    {
        DB.connectWith(new MySQLAdapter("jimmyhowe_com"));
    }

    @Before
    public void setUp() throws Exception
    {

    }

    @Test
    public void connectWith() throws Exception
    {

    }

    @Test
    public void table() throws Exception
    {

    }

    @Test
    public void query() throws Exception
    {

    }

    @Test
    public void getConnector() throws Exception
    {

    }

    @Test
    public void getPostProcessor() throws Exception
    {

    }

    @Test
    public void setPostProcessor() throws Exception
    {

    }

    @Test
    public void log() throws Exception
    {

    }

    @Test
    public void queryLog() throws Exception
    {

    }

    @Test
    public void raw() throws Exception
    {
        Expression expression = DB.raw("count(*) as user_count, status");

        assertEquals("count(*) as user_count, status", expression.toString());
    }
}