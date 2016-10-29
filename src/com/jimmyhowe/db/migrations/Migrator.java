package com.jimmyhowe.db.migrations;

import com.jimmyhowe.db.DB;
import com.jimmyhowe.db.connections.adapters.MySQLAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Migrator
 */
public class Migrator
{
    public List<Migration> migrations = new ArrayList<>();

    public void up()
    {
        migrations.forEach(Migrator::runMigrationUp);
    }

    public void down()
    {
        List<Migration> cachedList = migrations;

        Collections.reverse(cachedList);

        cachedList.forEach(Migrator::runMigrationDown);
    }

    public static void runMigrationUp(Migration migration)
    {
        System.out.println("Running [UP]... " + migration.getClass().getSimpleName());

        DB.connectWith(new MySQLAdapter("express_db")).run(migration.up());
    }

    public static void runMigrationDown(Migration migration)
    {
        System.out.println("Running [DOWN]... " + migration.getClass().getSimpleName());

        DB.connectWith(new MySQLAdapter("express_db")).run(migration.down());
    }
}
