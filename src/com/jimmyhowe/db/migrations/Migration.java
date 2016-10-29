package com.jimmyhowe.db.migrations;

/**
 * Abstract Migration
 */
public abstract class Migration
{
    /**
     * @return SQL to migrate up
     */
    public abstract String up();

    /**
     * @return SQL to migrate down
     */
    public abstract String down();
}
