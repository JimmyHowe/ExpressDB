package com.jimmyhowe.db.processors;

import com.jimmyhowe.db.contracts.MySQLTypes;
import com.jimmyhowe.db.tables.columns.Column;
import com.jimmyhowe.db.tables.columns.ColumnCollection;
import com.jimmyhowe.db.tables.rows.Row;
import com.jimmyhowe.db.tables.rows.RowCollection;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Processes ResultSet Returning Rows and Columns
 */
@SuppressWarnings("Duplicates")
public class TableProcessor extends PostProcessor<Row, RowCollection> implements MySQLTypes
{
    private ResultSetMetaData metaData;

    private int columnCount;

    /**
     * Get Single
     *
     * @param resultSet
     */
    @Override
    public Row single(ResultSet resultSet)
    {
        ColumnCollection columnCollection = new ColumnCollection();

        try
        {
            this.loadMetaData(resultSet);

            while ( resultSet.next() )
            {
                columnCollection = new ColumnCollection();

                for ( int i = 1; i <= columnCount; i++ )
                {
                    Column item = new Column();

                    item.use(TYPES());

                    item.setField(metaData.getColumnName(i));
                    item.setValue(resultSet.getObject(i));
                    item.setSqlType(metaData.getColumnTypeName(i));

                    columnCollection.add(item);
                }
            }

        } catch ( SQLException e )
        {
            e.printStackTrace();
        }

        return new Row(columnCollection);
    }

    /**
     * Get Collection
     *
     * @param resultSet
     */
    @Override
    public RowCollection collection(ResultSet resultSet)
    {
        // TODO: result set might be null when SQL error is found

        RowCollection rowCollection = new RowCollection();

        try
        {
            this.loadMetaData(resultSet);

            while ( resultSet.next() )
            {
                ColumnCollection columnCollection = new ColumnCollection();

                for ( int i = 1; i <= columnCount; i++ )
                {
                    String field = metaData.getColumnName(i);
                    Object data = resultSet.getObject(i);
                    String sqlType = metaData.getColumnTypeName(i);
                    Class castType = TYPES().get(sqlType.toUpperCase());
                    columnCollection.add(new Column(field, sqlType, castType.cast(data), castType));
                }

                rowCollection.add(new Row(columnCollection));
            }

        } catch ( SQLException e )
        {
            e.printStackTrace();
        }

        return rowCollection;
    }

    /**
     * Loads Meta Data
     *
     * @param resultSet
     *
     * @throws SQLException
     */
    private void loadMetaData(ResultSet resultSet) throws SQLException
    {
        metaData = resultSet.getMetaData();
        columnCount = metaData.getColumnCount();
    }
}
