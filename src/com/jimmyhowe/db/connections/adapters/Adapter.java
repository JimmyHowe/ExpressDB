package com.jimmyhowe.db.connections.adapters;

/**
 * MySQL Adapter
 *
 * Used to connect to MySQL drivers
 */
public class Adapter
{
    protected String url = "jdbc:mysql://localhost/";

    protected String database = "";

    protected String user = "root";

    protected String password = null;

    /**
     * Connect using all parameters
     *
     * @param url      Adapter URL
     * @param database Database Name
     * @param user     User Name
     * @param password Password
     */
    public Adapter(String url, String database, String user, String password)
    {
        this.url = url;
        this.database = database;
        this.user = user;
        this.password = password;
    }

    /**
     * Connect using the default driver url
     *
     * @param database Database Name
     * @param user     User Name
     * @param password Password
     */
    public Adapter(String database, String user, String password)
    {
        this.database = database;
        this.user = user;
        this.password = password;
    }

    /**
     * Connect with Root and Null password
     *
     * @param database Database Name
     */
    public Adapter(String database)
    {
        this.database = database;
        this.user = "root";
        this.password = null;
    }

    /**
     * @return Returns the URL
     */
    public String getUrl()
    {
        return url;
    }

    /**
     * @param url The URL
     */
    public void setUrl(String url)
    {
        this.url = url;
    }

    /**
     * @return Returns the Database Name
     */
    public String getDatabase()
    {
        return database;
    }

    /**
     * @param database The Database Name
     */
    public void setDatabase(String database)
    {
        this.database = database;
    }

    /**
     * @return Returns the User Name
     */
    public String getUser()
    {
        return user;
    }

    /**
     * @param user The User Name
     */
    public void setUser(String user)
    {
        this.user = user;
    }

    /**
     * @return Returns the Password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * @param password The Password
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * Builds the URL String
     */
    public String buildUrl()
    {
        return String.format("%s%s?user=%s&password=%s", this.url, this.database, this.user, this.password != null ? this.password : "");
    }
}
