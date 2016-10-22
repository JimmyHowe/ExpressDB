package com.jimmyhowe.db.tables.rows;

import com.jimmyhowe.db.tables.columns.Column;
import com.jimmyhowe.db.tables.columns.Columns;
import com.jimmyhowe.support.collections.GeneralisedCollection;

/**
 * A Table Row
 */
public class Row extends GeneralisedCollection<Column>
{
    /**
     * Build with Column Collection
     *
     * @param columns Columns
     */
    public Row(Columns columns)
    {
        this.data.addAll(columns.data());
    }

    /**
     * Return Column by Field
     *
     * @param field Field Name
     */
    public Column column(String field)
    {
        return this.findByField(field);
    }

    /**
     * Find by Field
     *
     * @param field Field Name
     */
    private Column findByField(String field)
    {
        for ( int i = 0; i < this.data().size(); i++ )
        {
            if ( this.data(i).getField().equals(field) )
            {
                return this.data(i);
            }
        }

        return null;
    }

    /**
     * Returns data as Column Collection
     */
    public Columns columns()
    {
        return new Columns(this.data());
    }

    /**
     * @return Returns Row as String
     */
    @Override
    public String toString()
    {
        return data.toString();
    }
}
