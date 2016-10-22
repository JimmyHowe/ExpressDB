package com.jimmyhowe.db.tables.rows;

import com.jimmyhowe.db.tables.columns.Column;
import com.jimmyhowe.db.tables.columns.ColumnCollection;
import com.jimmyhowe.support.collections.GeneralisedCollection;

/**
 * A Table Row
 */
public class Row extends GeneralisedCollection<Column>
{
    /**
     * Build with Column Collection
     *
     * @param columnCollection
     */
    public Row(ColumnCollection columnCollection)
    {
        this.data.addAll(columnCollection.data());
    }

    /**
     * Return Column by Field
     *
     * @param field
     */
    public Column column(String field)
    {
        return this.findByField(field);
    }

    /**
     * Find by Field
     *
     * @param field
     */
    private Column findByField(String field)
    {
        for ( int i = 0; i < this.data().size(); i++ )
        {
            if( this.data(i).getField().equals(field) )
            {
                return this.data(i);
            }
        }

        return null;
    }

    /**
     * Returns data as Column Collection
     *
     * @return
     */
    public ColumnCollection columns()
    {
        return new ColumnCollection(this.data());
    }

    @Override
    public String toString()
    {
        return "" + data;
    }
}
