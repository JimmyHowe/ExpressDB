package com.jimmyhowe.db.migrations;

import com.jimmyhowe.db.DB;
import com.jimmyhowe.db.connections.adapters.MySQLAdapter;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Jimmy on 31/10/2016.
 */
public class MigratorTest
{
    @Before
    public void setUp() throws Exception
    {

    }

    @Test
    public void add() throws Exception
    {

    }

    @Test
    public void up() throws Exception
    {

    }

    @Test
    public void down() throws Exception
    {

    }

    @Test
    public void runMigrationUp() throws Exception
    {

    }

    @Test
    public void runMigrationDown() throws Exception
    {

    }

    @Test
    public void install() throws Exception
    {
        DB.connectWith(new MySQLAdapter("express_db"));

        if ( Migrator.isInstalled() )
        {
            Migrator.uninstall();
        }

        Migrator.install();
    }

}