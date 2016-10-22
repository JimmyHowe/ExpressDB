package com.jimmyhowe.db.processors;

import com.jimmyhowe.db.contracts.MySQLTypes;
import com.jimmyhowe.db.tables.columns.Column;
import com.jimmyhowe.db.tables.columns.Columns;
import com.jimmyhowe.db.tables.rows.Row;
import com.jimmyhowe.db.tables.rows.Rows;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Processes ResultSet Returning Rows and Columns
 */
@SuppressWarnings("Duplicates")
public class TableProcessor extends PostProcessor<Row, Rows> implements MySQLTypes
{
    /**
     * Result Set Meta Data
     */
    private ResultSetMetaData metaData;

    /**
     * No of Columns
     */
    private int columnCount;

    /**
     * @param resultSet Result Set
     *
     * @return Row Instance
     */
    @Override
    public Row single(ResultSet resultSet)
    {
        Columns columns = new Columns();

        try
        {
            this.loadMetaData(resultSet);

            while ( resultSet.next() )
            {
                columns = new Columns();

                for ( int i = 1; i <= columnCount; i++ )
                {
                    Column item = new Column();

                    item.use(TYPES());

                    item.setField(metaData.getColumnName(i));
                    item.setValue(resultSet.getObject(i));
                    item.setSqlType(metaData.getColumnTypeName(i));

                    columns.add(item);
                }
            }

        } catch ( SQLException e )
        {
            e.printStackTrace();
        }

        return new Row(columns);
    }

    /**
     * @param resultSet Result Set
     *
     * @return Rows Instance
     */
    @Override
    public Rows collection(ResultSet resultSet)
    {
        // TODO: result set might be null when SQL error is found

        Rows rows = new Rows();

        try
        {
            this.loadMetaData(resultSet);

            while ( resultSet.next() )
            {
                Columns columns = new Columns();

                for ( int i = 1; i <= columnCount; i++ )
                {
                    String field = metaData.getColumnName(i);
                    Object data = resultSet.getObject(i);
                    String sqlType = metaData.getColumnTypeName(i);
                    Class castType = TYPES().get(sqlType.toUpperCase());
                    columns.add(new Column(field, sqlType, castType.cast(data), castType));
                }

                rows.add(new Row(columns));
            }

        } catch ( SQLException e )
        {
            e.printStackTrace();
        }

        return rows;
    }

    /**
     * Loads Meta Data
     *
     * @param resultSet Connection Result Set
     *
     * @throws SQLException SQL Exception
     */
    private void loadMetaData(ResultSet resultSet) throws SQLException
    {
        metaData = resultSet.getMetaData();
        columnCount = metaData.getColumnCount();
    }
}
