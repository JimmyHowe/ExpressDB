package com.jimmyhowe.db.connections.adapters;

/**
 * MySQL Adapter
 */
public class MySQLAdapter extends Adapter
{
    /**
     * Connect using all parameters
     *
     * @param url      Driver URL
     * @param database Database Name
     * @param user     User Name
     * @param password Password
     */
    public MySQLAdapter(String url, String database, String user, String password)
    {
        super(url, database, user, password);
    }

    /**
     * Connect using the default driver url
     *
     * @param database Database Name
     * @param user     User Name
     * @param password Password
     */
    public MySQLAdapter(String database, String user, String password)
    {
        super(database, user, password);
    }

    /**
     * Connect with Root and Null password
     *
     * @param database Database Name
     */
    public MySQLAdapter(String database)
    {
        super(database);
    }
}
