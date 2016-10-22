package com.jimmyhowe.db.connections;

import com.jimmyhowe.db.Expression;
import com.jimmyhowe.db.connections.adapters.Adapter;
import com.jimmyhowe.support.contracts.Cleanable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Handles the Database Connection
 */
public class Connector implements Cleanable
{
    /**
     * Automatically Connect
     */
    public static boolean autoConnect = true;

    /**
     * Automatically Close Connections
     */
    public static boolean autoClose = true;

    /**
     * Java SQL Connection
     */
    private java.sql.Connection connection = null;

    /**
     * Java SQL Statement
     */
    private java.sql.Statement statement = null;

    /**
     * Java Result Set
     */
    private java.sql.ResultSet resultSet = null;

    /**
     * Connection Adapter
     */
    private Adapter adapter;

    /**
     * Initializes the Connector
     *
     * @param adapter Adapter
     */
    public Connector(Adapter adapter)
    {
        this.adapter = adapter;
    }

    /**
     * Connects with an Adapter
     *
     * @param adapter Adapter
     */
    public Connector connectWithAdapter(Adapter adapter)
    {
        return this.connect(adapter);
    }

    /**
     * Connects to the database using an adapter
     *
     * @param adapter Adapter
     */
    private Connector connect(Adapter adapter)
    {
        try
        {
            // TODO: Null pointer exception when no adapter set

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
     * @param query Query String
     */
    public ResultSet run(String query)
    {
        /*
            If auto-connecting then connect the adapter,
            else return null
         */
        if ( autoConnect )
        {
            this.connect(this.adapter);
        } else if ( this.connection == null )
        {
            return null;
        }

        /*
            Try run the query
         */
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

    /**
     * Returns the Connection
     *
     * @return Returns the Connection
     */
    public Connection getConnection()
    {
        return connection;
    }

    /**
     * Returns the Adapter
     *
     * @return Returns the Adapter
     */
    public Adapter getAdapter()
    {
        return adapter;
    }

    /**
     * Prints the SQL Exception Errors to the console
     *
     * @param e SQL Exception
     */
    private void printErrors(SQLException e)
    {
        System.out.println("SQLException: " + e.getMessage());
        System.out.println("SQLState: " + e.getSQLState());
        System.out.println("VendorError: " + e.getErrorCode());
    }

    /**
     * Clean Up
     */
    public void cleanUp()
    {
        if ( resultSet != null )
        {
            try
            {
                resultSet.close();
            } catch ( SQLException e )
            { /* ignored */}
        }
        if ( statement != null )
        {
            try
            {
                statement.close();
            } catch ( SQLException e )
            { /* ignored */}
        }
        if ( connection != null )
        {
            try
            {
                connection.close();
            } catch ( SQLException e )
            { /* ignored */}
        }
    }

    /**
     * @param sql SQL String
     *
     * @return Raw SQL Expression
     */
    public Expression raw(String sql)
    {
        return new Expression(sql);
    }
}