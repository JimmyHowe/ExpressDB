package com.jimmyhowe.db.migrations;

import com.jimmyhowe.db.DB;
import com.jimmyhowe.db.connections.adapters.MySQLAdapter;
import com.jimmyhowe.db.schema.Blueprint;
import com.jimmyhowe.db.schema.Schema;
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

    /**
     * @return If the migrations table exists
     */
    public static boolean isInstalled()
    {
        return DB.table("migrations").exists();
    }

    public static void uninstall()
    {
        DB.getConnector().run(Schema.drop("migrations"));
    }

    public static void install()
    {
        Migrator.add(new Migration()
        {
            @Override
            public String up()
            {
                return Schema.create("migrations", new Blueprint()
                {
                    @Override
                    public void buildTable()
                    {
                        table.increments("id");
                        table.string("class");
                    }
                });
            }

            @Override
            public String down()
            {
                return Schema.drop("migrations");
            }
        });

        Migrator.up();
    }
}
