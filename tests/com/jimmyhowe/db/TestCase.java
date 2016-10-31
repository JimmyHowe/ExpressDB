package com.jimmyhowe.db;

import com.jimmyhowe.db.connections.adapters.MySQLAdapter;

/**
 * Created by Jimmy on 31/10/2016.
 */
public class TestCase
{
    static String testDatabase = "express_db";

    public static void ConnectsWithDatabase()
    {
        if ( DB.getConnector() == null )
        {
            DB.connectWith(new MySQLAdapter(testDatabase));
        }
    }

    protected void UsesTransactions()
    {

    }
}
