package com.jimmyhowe.db.migrations;

import com.jimmyhowe.db.schema.Blueprint;
import com.jimmyhowe.db.schema.Schema;

/**
 * Created by Jimmy on 31/10/2016.
 */
public class MigrationsTable extends Migration
{
    /**
     * @return SQL to migrate up
     */
    @Override
    public String up()
    {
        return Schema.create("migrations", new Blueprint()
        {
            @Override
            public void buildTable()
            {

            }
        });
    }

    /**
     * @return SQL to migrate down
     */
    @Override
    public String down()
    {
        return Schema.drop("migrations");
    }
}
