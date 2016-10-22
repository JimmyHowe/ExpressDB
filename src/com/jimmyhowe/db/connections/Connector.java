package com.jimmyhowe.db.connections;

import com.jimmyhowe.db.connections.adapters.Adapter;
import com.jimmyhowe.support.contracts.Cleanable;

import java.sql.*;

/**
 * Handles the Database Connection
 */
public class Connector implements Cleanable
{
    public static boolean autoConnect = true;
    public static boolean autoClose = true;
    private java.sql.Connection connection = null;
    private java.sql.Statement statement = null;
    private java.sql.ResultSet resultSet = null;
    private Adapter adapter;

    /**
     * Initializes the Connector
     *
     * @param adapter
     */
    public Connector(Adapter adapter)
    {
        this.adapter = adapter;
    }

    /**
     * Connects with an Adapter
     *
     * @param adapter
     */
    public Connector connectWithAdapter(Adapter adapter)
    {
        return this.connect(adapter);
    }

    /**
     * Connects to the database using an adapter
     *
     * @param adapter
     */
    private Connector connect(Adapter adapter)
    {
        try
        {
            connection = DriverManager.getConnection(adapter.buildUrl());

        } catch ( SQLException e )
        {
            printErrors(e);
        }

        return this;
    }

    /**
     * Runs a SQL query against the connection
     *
     * @param query
     */
    public ResultSet run(String query)
    {
        this.closeStatement();
        this.closeResultSet();

        if ( autoConnect )
        {
            this.connect(this.adapter);
        } else if ( this.connection == null )
        {
            return null;
        }

        try
        {
            statement = connection.createStatement();

            if ( statement.execute(query) )
            {
                resultSet = statement.getResultSet();

                return resultSet;
            }
        } catch ( SQLException e )
        {
            printErrors(e);
        }

        return null;
    }

    public Connection getConnection()
    {
        return connection;
    }

    public Statement getStatement()
    {
        return statement;
    }

    public ResultSet getResultSet()
    {
        return resultSet;
    }

    /**
     * Cleans up all the connection data
     */
    public void cleanUp()
    {
        closeResultSet();

        closeStatement();

        closeConnection();
    }

    /**
     * Close the Results Set
     */
    private void closeResultSet()
    {
        if ( resultSet != null )
        {
            try
            {
                resultSet.close();
            } catch ( SQLException e )
            {
                printErrors(e);
            }

            resultSet = null;
        }
    }

    /**
     * Close the Statement
     */
    private void closeStatement()
    {
        if ( statement != null )
        {
            try
            {
                statement.close();
            } catch ( SQLException e )
            {
                printErrors(e);
            }

            statement = null;
        }
    }

    /**
     * Close the Connection
     */
    private void closeConnection()
    {
        if ( connection != null )
        {
            try
            {
                connection.close();
            } catch ( SQLException e )
            {
                printErrors(e);
            }

            connection = null;
        }
    }

    /**
     * Returns the Adapter
     */
    public Adapter getAdapter()
    {
        return adapter;
    }

    /**
     * Prints the SQL Exception Errors to the console
     *
     * @param e
     */
    private void printErrors(SQLException e)
    {
        System.out.println("SQLException: " + e.getMessage());
        System.out.println("SQLState: " + e.getSQLState());
        System.out.println("VendorError: " + e.getErrorCode());
    }
}
