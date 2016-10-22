package com.jimmyhowe.db.tables.columns;

import com.jimmyhowe.support.collections.GeneralisedCollection;

import java.util.List;

/**
 * Collection of Columns
 */
public class Columns extends GeneralisedCollection<Column>
{
    /**
     * Empty Column Collection
     */
    public Columns()
    {

    }

    /**
     * Build with List
     *
     * @param data Columns List Array
     */
    public Columns(List<Column> data)
    {
        this.data().addAll(data);
    }

    /**
     * Checks if Field Exists
     *
     * @param field Field Name
     */
    public boolean hasField(String field)
    {
        for ( Column column : this.data() )
        {
            if ( column.getField().equals(field) ) return true;
        }

        return false;
    }

    /**
     * Updates field with Value
     *
     * @param field Field Name
     * @param value Value
     */
    public void updateField(String field, Object value)
    {
        for ( int i = 0; i < this.data().size(); i++ )
        {
            if ( this.data(i).getField().equals(field) )
            {
                this.data(i).setValue(value);
            }
        }
    }
}
