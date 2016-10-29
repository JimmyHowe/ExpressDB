package com.jimmyhowe.db.migrations;

import com.jimmyhowe.db.DB;
import com.jimmyhowe.db.connections.adapters.MySQLAdapter;
import com.jimmyhowe.support.Str;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Migrator
 */
public class Migrator
{
    /**
     * List of Migrations
     */
    public static List<Migration> migrations = new ArrayList<>();

    private static List<String> log = new ArrayList<>();

    /**
     * @param migration Migration
     */
    public static void add(Migration migration)
    {
        migrations.add(migration);
    }

    /**
     * Runs the Migrations Forwards
     */
    public static void up()
    {
        migrations.forEach(Migrator::runMigrationUp);
    }

    /**
     * Runs the Migrations Backwards
     */
    public static void down()
    {
        List<Migration> cachedList = migrations;

        Collections.reverse(cachedList);

        cachedList.forEach(Migrator::runMigrationDown);
    }

    /**
     * Runs a single Migration
     *
     * @param migration Migration
     */
    public static void runMigrationUp(Migration migration)
    {
        log("Migrating..." + getSimpleName(migration));

        DB.connectWith(new MySQLAdapter("express_db")).run(migration.up());
    }

    /**
     * Runs a single Migration in reverse
     *
     * @param migration Migration
     */
    public static void runMigrationDown(Migration migration)
    {
        log("Reversing..." + getSimpleName(migration));

        DB.connectWith(new MySQLAdapter("express_db")).run(migration.down());
    }

    /**
     * @param migration Name of Migration
     *
     * @return Simple Name of Migration
     */
    private static String getSimpleName(Migration migration)
    {
        return Str.CamelCaseToWords(migration.getClass().getSimpleName());
    }

    /**
     * @param message Message to log
     */
    private static void log(String message)
    {
        log.add(message);
    }
}
