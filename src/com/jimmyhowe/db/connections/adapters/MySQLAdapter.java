package com.jimmyhowe.db.connections.adapters;

/**
 * Created by Jimmy on 10/09/2016.
 */
public class MySQLAdapter extends Adapter
{
    /**
     * Connect using all parameters
     *
     * @param url
     * @param database
     * @param user
     * @param password
     */
    public MySQLAdapter(String url, String database, String user, String password)
    {
        super(url, database, user, password);
    }

    /**
     * Connect using the default driver url
     *
     * @param database
     * @param user
     * @param password
     */
    public MySQLAdapter(String database, String user, String password)
    {
        super(database, user, password);
    }

    /**
     * Connect with Root and Null password
     *
     * @param database
     */
    public MySQLAdapter(String database)
    {
        super(database);
    }
}
