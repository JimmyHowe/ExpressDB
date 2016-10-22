package com.jimmyhowe.db.tables.columns;

import com.jimmyhowe.support.collections.GeneralisedCollection;

import java.util.List;

/**
 * Column Collection
 */
public class ColumnCollection extends GeneralisedCollection<Column>
{
    /**
     * Empty Column Collection
     */
    public ColumnCollection()
    {

    }

    /**
     * Build with List
     *
     * @param data
     */
    public ColumnCollection(List<Column> data)
    {
        this.data().addAll(data);
    }

    /**
     * Checks if Field Exists
     *
     * @param field
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
     * @param field
     * @param value
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
